package com.blog.module.business;

import com.blog.AppRun;
import com.blog.module.business.mapper.BlogMapper;
import com.blog.module.business.service.BlogService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * 功能描述：博客测试
 *
 * @author RenShiWei
 * Date: 2020/4/14 12:57
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = AppRun.class)
public class BlogTest {

    @Autowired
    private BlogMapper blogMapper;

    @Autowired
    private BlogService blogService;

    /**
     * 功能描述：测试批量新增博客和博客标签中间表数据
     *
     *  问题：组合主键重复会报错（说明：只能在新增的时候调用这个接口）
     * @author RenShiWei
     * Date: 2020/4/14 13:27
     */
    @Test
    public void testInsertBlogs(){
        Long blogId=1L;
        List<Long> blogLabelList=new ArrayList<>();
        blogLabelList.add(1L);
        blogLabelList.add(2L);
        blogLabelList.add(3L);
        int count=blogMapper.saveBlogLabelMiddle(blogId,blogLabelList);
        System.out.println(count);
    }

    /**
     * 功能描述：测试查询一条博客信息
     * @author RenShiWei
     * Date: 2020/4/14 20:48
     */
    @Test
    public void queryBlog(){
        System.out.println(blogService.queryBlogByBlogId(1L));
    }

    @Test
    public void testBlogCount(){
        System.out.println(blogService.queryBlogCount());
    }
}

