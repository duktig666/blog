package com.blog.module.websocket;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.stream.ChunkedWriteHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * 功能描述：netty整合websocket的服务端
 *
 * @author RenShiWei
 * Date: 2020/5/26 20:57
 **/
@Slf4j
@Configuration
public class WebSocketNettyServer {

    /** netty整合websocket的端口 */
    @Value("${netty.port}")
    private int port;

    public void run() throws InterruptedException {
        EventLoopGroup boss = new NioEventLoopGroup();
        EventLoopGroup worker = new NioEventLoopGroup();

        try {
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(boss,worker)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG,1024)
                    .childOption(ChannelOption.TCP_NODELAY,true)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            //web基于http协议的解码器
                            ch.pipeline().addLast(new HttpServerCodec());
                            //对大数据流的支持
                            ch.pipeline().addLast(new ChunkedWriteHandler());
                            //对http message进行聚合，聚合成FullHttpRequest或FullHttpResponse
                            ch.pipeline().addLast(new HttpObjectAggregator(1024 * 64));
                            //websocket服务器处理对协议，用于指定给客户端连接访问的路径
                            //该handler会帮你处理一些繁重的复杂的事
                            //会帮你处理握手动作：handshaking(close,ping,pong) ping + pong = 心跳
                            //对于websocket来讲，都是以frames进行传输的，不同的数据类型对应的frames也不同
                            ch.pipeline().addLast(new WebSocketServerProtocolHandler("/ws"));
                            //添加我们的自定义channel处理器
                            ch.pipeline().addLast(new WebSocketHandler());
                        }
                    });
            log.info("服务器启动中,websocket的端口为："+port);
            ChannelFuture future = bootstrap.bind(port).sync();
            future.channel().closeFuture().sync();
        } finally {
            //关闭主从线程池
            worker.shutdownGracefully();
            boss.shutdownGracefully();
        }

    }
}


