package com.blog.module.business;

import cn.hutool.json.JSONUtil;
import com.blog.AppRun;
import com.blog.module.business.service.ObserveService;
import com.blog.module.business.service.impl.ObserveServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


/**
 * 功能描述：评论测试
 *
 * @author RenShiWei
 * Date: 2020/4/15 9:12
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = AppRun.class)
public class ObserveTest {

    @Autowired
    private ObserveService observeService;

    /**
     * 功能描述：测试查询评论功能
     *
     * @author RenShiWei
     * Date: 2020/4/16 10:32
     */
    @Test
    public void testQueryObserveByBlogId(){
        System.out.println(JSONUtil.toJsonStr(observeService.queryObserveByBlogId(1L)));
    }





}

