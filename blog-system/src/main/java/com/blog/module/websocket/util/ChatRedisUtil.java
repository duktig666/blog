package com.blog.module.websocket.util;

import com.blog.module.websocket.enums.MsgSignFlagEnum;
import com.blog.module.websocket.vo.ChatVO;
import com.blog.util.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 功能描述： 测试redis的连接
 *
 * @author RenShiWei
 * Date: 2020/2/7 14:13
 **/
@Component
@Slf4j
public class ChatRedisUtil {

    @Autowired
    private RedisUtil redisUtil;

    /**
     * 功能描述：将JavaBean对象的信息缓存进Redis
     *
     * @param chatVO 聊天信息JavaBean
     * @return 是否保存成功
     * @author RenShiWei
     * Date: 2020/2/6 21:42
     */
    public boolean saveCacheChatMessage ( String key, ChatVO chatVO ) {
        //判断key是否存在
        if (redisUtil.hasKey(key)) {
            //将javabean对象添加到缓存的list中
            long redisSize = redisUtil.lGetListSize(key);
            System.out.println("redis当前数据条数" + redisSize);
            Long index = redisUtil.rightPushValue(key, chatVO);
            System.out.println("redis执行rightPushList返回值：" + index);
            return redisSize < index;
        } else {
            //不存在key时，将chatVO存进缓存，并设置过期时间
            boolean isCache = redisUtil.lSet(key, chatVO);
            //保存成功，设置过期时间
            if (isCache) {
                redisUtil.expire(key, 3L, TimeUnit.DAYS);
            }
            return isCache;
        }
    }

    /**
     * 功能描述：从缓存中读取聊天信息
     *
     * @param key 缓存聊天信息的键
     * @return 缓存中聊天信息list
     * @author RenShiWei
     * Date: 2020/2/7 15:24
     */
    public List<Object> getCacheChatMessage ( String key ) {
        List<Object> chatList = null;
        //判断key是否存在
        if (redisUtil.hasKey(key)) {
            //读取缓存中的聊天内容
            chatList = redisUtil.lGet(key, 0, redisUtil.lGetListSize(key));
        } else {
            System.out.println("此次解答无聊天信息");
            log.info("redis缓存中无此键值:" + key);
        }
        return chatList;
    }

    /**
     * 功能描述： 在缓存中删除聊天信息
     *
     * @param key 缓存聊天信息的键
     * @author RenShiWei
     * Date: 2020/2/7 15:39
     */
    public void deleteCacheChatMessage ( String key ) {
        //判断key是否存在
        if (redisUtil.hasKey(key)) {
            redisUtil.del(key);
        }
    }


    /**
     * 功能描述： 创建已发送消息房间号
     * 根据ChatVO中的questionId、fromUserId和toUserId生成聊天房间号：问题id-小号用户id-大号用户id
     * 例如“1-2”： 小号在前，大号在后；保证房间号唯一
     *
     * @param fromUserId 发送方id
     * @param toUserId   接收方id
     * @return 两端通信的唯一标识
     * @author RenShiWei
     * Date: 2020/2/7 15:58
     */
    public String createChatNumber (Integer questionId, Integer fromUserId, Integer toUserId) {
        StringBuilder key = new StringBuilder();
        key.append(questionId).append("-");
        if (fromUserId < toUserId) {
            key.append(fromUserId).append("-").append(toUserId);
        } else {
            key.append(toUserId).append("-").append(fromUserId);
        }
        return key.toString();
    }

    /**
     * 功能描述：创建离线聊天记录的房间号（redis的键）
     *      拼接方式：发送方用户id-签证标识
     * @param toUserId 发送方用户id
     * @return 用户离线消息房间号
     * @author RenShiWei
     * Date: 2020/3/14 20:27
     */
    public String createOffLineNumber(Integer toUserId){
        return toUserId + "-" + MsgSignFlagEnum.unsign.type;
    }

    /**
     * 功能描述：从redis读取缓存信息集合(List<Object>),并且存储到新的键中  oldKey——>newKey
     * @author RenShiWei
     * Date: 2020/3/13 17:40
     */
    public void signQuestionMessageList(String oldKey,String newKey){
        redisUtil.rightPushList(newKey,getCacheChatMessage(oldKey));
    }

    /**
     * 功能描述：从redis读取每一条缓存信息,并且存储到新的键中  oldKey——>newKey
     * @author RenShiWei
     * Date: 2020/3/14 20:42
     */
    public void signQuestionMessage(String oldKey,String newKey){
        redisUtil.rightPushValue(newKey,getCacheChatMessage(oldKey));
    }

}

