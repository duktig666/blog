package com.blog.module.redis;

import com.blog.AppRun;
import com.blog.util.RedisUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 功能描述：
 *
 * @author RenShiWei
 * Date: 2020/4/11 21:42
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = AppRun.class)
public class RedisTest {

    @Autowired
    RedisUtil redisUtil;

    @Test
    public void testGet2() {
        redisUtil.set("test","测试2");
    }

    @Test
    public void testGet3() {
        System.out.println(redisUtil.get("test"));

    }

}

