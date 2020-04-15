package com.blog.module.business.service.impl;

import com.blog.exception.BaseException;
import com.blog.module.business.domain.Observe;
import com.blog.module.business.domain.Visitor;
import com.blog.module.business.mapper.ObserveMapper;
import com.blog.module.business.mapper.VisitorMapper;
import com.blog.module.business.service.ObserveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Description：
 *
 * @author jiaoqianjin
 * Date: 2020/4/11 11:13
 **/
@Service
public class ObserveServiceImpl implements ObserveService {

    @Autowired
    private ObserveMapper observeMapper;

    @Autowired
    private VisitorMapper visitorMapper;

    /**
     * 功能描述：新增博客评论
     *
     * @param visitor 游客信息
     * @param observe 评论信息
     * @author RenShiWei
     * Date: 2020/4/15 8:24
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveObserve ( Visitor visitor, Observe observe ) {
        //新增评论信息
        int count=observeMapper.insertSelective(observe);
        if (count == 0) {
            throw new BaseException("新增评论信息失败！");
        }
        //新增游客信息
        count=visitorMapper.insertSelective(visitor);
        if (count == 0) {
            throw new BaseException("新增评论信息失败(游客信息增加失败)！");
        }
    }

    /**
     * 删除评论
     *
     * @param observeId 评论id
     * @author RenShiWei
     * Date: 2020/4/15 8:24
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteObserve ( Long observeId ) {
        int count = observeMapper.deleteByPrimaryKey(observeId);
        if (count == 0) {
            throw new BaseException("删除评论信息失败！");
        }
    }

    /**
     * 功能描述：修改评论
     *
     * @param observe 修改的评论信息
     * @author RenShiWei
     * Date: 2020/4/15 8:27
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateObserve ( Observe observe ) {
        int count = observeMapper.updateByPrimaryKeySelective(observe);
        if (count == 0) {
            throw new BaseException("修改评论信息失败！");
        }
    }

    /**
     * 功能描述：根据博客id，查询此博客的所有评论信息
     *
     * @param blogId 博客id
     * @return 博客的评论信息
     * @author RenShiWei
     * Date: 2020/4/15 8:29
     */
    @Override
    public void queryObserveByBlogId ( Long blogId ) {

    }

}
