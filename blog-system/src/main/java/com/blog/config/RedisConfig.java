package com.blog.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import redis.clients.jedis.JedisPoolConfig;

/**
 * 功能描述：SpringData Redis 的配置类
 *
 * @author RenShiWei
 * Date: 2020/4/11 21:07
 **/
@Configuration
public class RedisConfig {

    @Bean
    public RedisTemplate<String,Object> redisTemplate( RedisConnectionFactory factory){

        RedisTemplate<String,Object> template = new RedisTemplate<String, Object>();

        template.setConnectionFactory(factory);

        template.setKeySerializer(new StringRedisSerializer());
        //实例化字符串
        template.setValueSerializer(new StringRedisSerializer());
        //实例化对象
        //template.setValueSerializer(new JdkSerializationRedisSerializer());

        return template;
    }


}

