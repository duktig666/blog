package com.blog.module.business;

import com.blog.AppRun;
import com.blog.module.business.domain.Blog;
import com.blog.module.business.domain.Observe;
import com.blog.module.business.domain.bo.BlogObserveBO;
import com.blog.module.business.domain.bo.ObserveBO;
import com.blog.module.business.domain.bo.ObserveNodeBO;
import com.blog.module.business.mapper.ObserveMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.List;

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
    private ObserveMapper observeMapper;

    @Test
    public void testObserveByNode () {
        Long id = 1L;
        // 初始化example对象
        Example example = new Example(Observe.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("blogId", id).andIsNull("lastId");
        // 查询id为1且lastId为null的一级评论
        List<Observe> firstList = observeMapper.selectByExample(example);

        example.clear();
        Example.Criteria criteria2 = example.createCriteria();
        criteria2.andEqualTo("blogId", id).andIsNotNull("lastId");
        // 查询id为1且lastId不为null的一级评论
        List<Observe> thenList = observeMapper.selectByExample(example);

        //使用链表结构处理评论
        List<ObserveNodeBO> observeNodeBOList = new ArrayList<>();
        for (Object obj : firstList) {
            observeNodeBOList.add(new ObserveNodeBO((Observe) obj));
        }

        List<ObserveNodeBO> list=ObserveNodeBO.addAllNode(observeNodeBOList,thenList);
        System.out.println("---");
        //打印回复链表
        ObserveNodeBO.show(list);
        System.out.println("======");
        System.out.println(list);


    }

    @Test
    public void testObserveByList () {
        // 初始化example对象
        Example example = new Example(Observe.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("blog_id", 1L).andIsNull("lastId");

        // 查询id为1且lastId为null的一级评论
        List<Observe> firstObserveList = observeMapper.selectByExample(example);

        List<ObserveBO> firstObserveBOList=new ArrayList<>();


        example.clear();
        Example.Criteria criteria2 = example.createCriteria();
        criteria2.andEqualTo("blogId", 1L).andIsNotNull("lastId");
        // 查询id为1且lastId不为null的一级评论
        List<Observe> secondObserveList=observeMapper.selectByExample(example);
        List<ObserveBO> secondObserveBOList=new ArrayList<>();


    }

    public void test(){


    }




}

