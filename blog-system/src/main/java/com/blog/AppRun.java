package com.blog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * 功能描述：启动类
 *
 * @author RenShiWei
 * Date: 2020/4/11 9:40
 **/
@MapperScan("com.blog.module.business.mapper")
@SpringBootApplication
public class AppRun {

    public static void main ( String[] args ) {
        SpringApplication.run(AppRun.class, args);
    }
}

