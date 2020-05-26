package com.blog;

import com.blog.module.websocket.WebSocketNettyServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * 功能描述：启动类
 *
 * @author RenShiWei
 * Date: 2020/4/11 9:40
 **/
@MapperScan("com.blog.module.business.mapper")
@SpringBootApplication
public class AppRun implements CommandLineRunner{

    /** 注入netty整合websocket的服务  CommandLineRunner */
    @Autowired
    private WebSocketNettyServer webSocketNettyServer;

    public static void main ( String[] args ) {
        SpringApplication.run(AppRun.class, args);
    }

    /**
     *声明CommandLineRunner接口，实现run方法，就能给启动项目同时启动netty服务
     */
    @Override
    public void run ( String... args ) throws Exception {
        webSocketNettyServer.run();
    }

}

