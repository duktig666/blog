package com.blog.module.websocket;

import cn.hutool.core.date.DateTime;
import cn.hutool.json.JSONException;
import cn.hutool.json.JSONUtil;
import com.blog.exception.BaseException;
import com.blog.module.websocket.enums.MsgActionEnum;
import com.blog.module.websocket.util.ChatRedisUtil;
import com.blog.module.websocket.util.ChatTypeVerificationUtil;
import com.blog.module.websocket.vo.ChatMsgVO;
import com.blog.module.websocket.vo.ChatVO;
import com.blog.util.SpringContextHolder;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.codec.http.websocketx.BinaryWebSocketFrame;
import io.netty.handler.codec.http.websocketx.CloseWebSocketFrame;
import io.netty.handler.codec.http.websocketx.PongWebSocketFrame;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.util.concurrent.GlobalEventExecutor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * 功能描述：TextWebSocketFrame: 在netty中，用于为websocket专门处理文本的对象，frame是消息的载体
 *
 * @author RenShiWei
 * Date: 2020/5/26 20:57
 **/
@Slf4j
public class WebSocketHandler extends SimpleChannelInboundHandler<Object> {

    /**
     * 客户端组
     * 用于记录和管理所有客户端的channel
     */
    public static ChannelGroup channelGroup;

    static {
        channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
    }

    /**
     * 接收客户端传来的消息
     *
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    public void channelRead0 ( ChannelHandlerContext ctx, Object msg ) throws Exception {
        Channel currentChannel = ctx.channel();
        //实例化redis对象  采用框架自身使用的redis的方式，还不理解
        ChatRedisUtil chatRedisUtil = SpringContextHolder.getBean(ChatRedisUtil.class);
        //文本消息
        if (msg instanceof TextWebSocketFrame) {
            String message = ((TextWebSocketFrame) msg).text();
            System.out.println("收到客户端消息：" + message);
            //json消息转换为Javabean对象
            ChatMsgVO chatMsgVO = null;
            try {
                chatMsgVO = JSONUtil.toBean(message, ChatMsgVO.class, true);
            } catch (JSONException e) {
                e.printStackTrace();
                System.out.println("json解析异常，发送的消息应该为json格式");
                return;
            }
            Integer action = chatMsgVO.getAction();
            if (action.equals(MsgActionEnum.CONNECT.type)) {
                //当websocket第一次open的时候，初始化channel,把用的channel和userId关联起来
                Integer fromUserId = chatMsgVO.getFromUserId();
                UserChannelRel.put(fromUserId, currentChannel);
                //测试
                channelGroup.forEach(channel -> log.info(channel.id().asLongText()));
                UserChannelRel.output();
                /* 第一次或者断线重连，查询redis此用户的离线消息，并处理 */
                //查询此用户的离线消息
                String unsignKey=chatRedisUtil.createOffLineNumber(fromUserId);
                List<Object> offLineMessageList = chatRedisUtil.getCacheChatMessage(unsignKey);
                //若有离线消息
                if (offLineMessageList!=null){
                    //遍历当前用户的所有离线消息
                    for (int i=0;i<offLineMessageList.size();i++){
                        //离线消息json转javabean
                        ChatVO chatVO= (ChatVO) offLineMessageList.get(i);
                        //将离线消息发送给当前用户
                        sendMessage(fromUserId, JSONUtil.toJsonStr(chatVO));
                        //每条消息对应的已读消息的房间号
                        String signKey = chatRedisUtil.createChatNumber(chatVO.getQuestionId(), chatVO.getFromUserId(), chatVO.getToUserId());
                        //每条消息的签证
                        chatRedisUtil.saveCacheChatMessage(signKey,chatVO);
                    }
                    //签证完成后，在redis中删除离线消息
                    chatRedisUtil.deleteCacheChatMessage(unsignKey);
                }
            } else if (action.equals(MsgActionEnum.CHAT.type)) {
                //聊天类型的消息，把聊天记录保存到redis，同时标记消息的签收状态[是否签收]
                Integer toUserId = chatMsgVO.getToUserId();
                Channel receiverChannel = UserChannelRel.get(toUserId);
                //将消息转化为需要保存在redis中的消息
                ChatVO chatVO = JSONUtil.toBean(message, ChatVO.class, true);
                //消息保存至redis的键
                String key = "";
                //设置发送消息的时间
                chatVO.setDateTime(new DateTime());
                if (receiverChannel == null) {
                    //接收方离线状态,将消息保存到redis，并设置[未签收]状态
                    //设置redis键为未接收房间号。拼接发送方和接收方，成为房间号；MsgSignFlagEnum.signed.type为0，代表未签收
                    key = chatRedisUtil.createOffLineNumber(chatVO.getToUserId());
                } else {
                    //接受方在线，服务端直接转发聊天消息给用户，并将消息存储到redis，并设置[签收]状态
                    //设置redis键为已接收房间号。拼接发送方和接收方，成为房间号；MsgSignFlagEnum.signed.type为1，代表已经签收
                    key = chatRedisUtil.createChatNumber(chatVO.getQuestionId(), chatVO.getFromUserId(), chatVO.getToUserId());

                    /* 发送消息给指定用户 */
                    //判断消息是否符合定义的类型
                    if (ChatTypeVerificationUtil.verifyChatType(chatVO.getChatMessageType())) {
                        //发送消息给指定用户
                        if (toUserId > 0 && UserChannelRel.isContainsKey(toUserId)) {
                            sendMessage(toUserId, JSONUtil.toJsonStr(chatVO));
                        }
                    } else {
                        //消息不符合定义的类型的处理
                    }
                }
                /* 消息保存到redis中 */
                boolean isCache = chatRedisUtil.saveCacheChatMessage(key, chatVO);
                //缓存失败，抛出异常
                if (!isCache) {
                    throw new BaseException("聊天内容缓存失败,聊天信息内容：" + message);
                }
            } else if (action.equals(MsgActionEnum.KEEPALIVE.type)) {
                //心跳类型的消息
                log.info("收到来自channel为[" + currentChannel + "]的心跳包");
            }

        }
        //二进制消息
        if (msg instanceof BinaryWebSocketFrame) {
            System.out.println("收到二进制消息：" + ((BinaryWebSocketFrame) msg).content().readableBytes());
            BinaryWebSocketFrame binaryWebSocketFrame = new BinaryWebSocketFrame(Unpooled.buffer().writeBytes("hello".getBytes()));
            //给客户端发送的消息
            ctx.channel().writeAndFlush(binaryWebSocketFrame);
        }
        //ping消息
        if (msg instanceof PongWebSocketFrame) {
            System.out.println("客户端ping成功");
        }
        //关闭消息
        if (msg instanceof CloseWebSocketFrame) {
            System.out.println("客户端关闭，通道关闭");
            Channel channel = ctx.channel();
            channel.close();
        }

    }

    /**
     * Handler活跃状态，表示连接成功
     * 当客户端连接服务端之后(打开连接)
     * 获取客户端的channel,并且放到ChannelGroup中去进行管理
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void handlerAdded ( ChannelHandlerContext ctx ) throws Exception {
        System.out.println("与客户端连接成功");
        channelGroup.add(ctx.channel());
    }

    /**
     * @param ctx
     * @throws Exception
     */
    @Override
    public void handlerRemoved ( ChannelHandlerContext ctx ) throws Exception {
        //当触发handlerRemoved,ChannelGroup会自动移除对应的客户端的channel
        //所以下面这条语句可不写
//        clients.remove(ctx.channel());
        log.info("客户端断开，channel对应的长id为:" + ctx.channel().id().asLongText());
        log.info("客户端断开，channel对应的短id为:" + ctx.channel().id().asShortText());
    }

    /**
     * 异常处理
     *
     * @param ctx
     * @param cause
     * @throws Exception
     */
    @Override
    public void exceptionCaught ( ChannelHandlerContext ctx, Throwable cause ) throws Exception {
        System.out.println("连接异常：" + cause.getMessage());
        cause.printStackTrace();
        ctx.channel().close();
        channelGroup.remove(ctx.channel());
    }

    @Override
    public void userEventTriggered ( ChannelHandlerContext ctx, Object evt ) throws Exception {
        //IdleStateEvent是一个用户事件，包含读空闲/写空闲/读写空闲
        if (evt instanceof IdleStateEvent) {
            IdleStateEvent event = (IdleStateEvent) evt;
            if (event.state() == IdleState.READER_IDLE) {
                log.info("进入读空闲");
            } else if (event.state() == IdleState.WRITER_IDLE) {
                log.info("进入写空闲");
            } else if (event.state() == IdleState.ALL_IDLE) {
                log.info("channel关闭前，用户数量为:" + channelGroup.size());
                //关闭无用的channel，以防资源浪费
                ctx.channel().close();
                log.info("channel关闭后，用户数量为:" + channelGroup.size());
            }

        }
    }

    /**
     * 给指定用户发内容
     * 后续可以掉这个方法推送消息给客户端
     */
    public void sendMessage ( Integer toUserId, String message ) {
        Channel channel = UserChannelRel.get(toUserId);
        channel.writeAndFlush(new TextWebSocketFrame(message));
    }

    /**
     * 群发消息
     */
    public void sendMessageAll ( String message ) {
        channelGroup.writeAndFlush(new TextWebSocketFrame(message));
    }


}