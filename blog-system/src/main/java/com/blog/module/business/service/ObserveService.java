package com.blog.module.business.service;

import com.blog.module.business.domain.Observe;
import com.blog.module.business.domain.User;
import com.blog.module.business.domain.bo.ObserveBO;

import java.util.List;

/**
 * Description：评论的业务处理
 *
 * @author jiaoqianjin
 * Date: 2020/4/11 11:13
 **/
public interface ObserveService {

    /**
     * 功能描述：新增博客评论
     *
     * @param user 游客信息
     * @param observe 评论信息
     * @author RenShiWei
     * Date: 2020/4/15 8:24
     */
    void saveObserve (User user, Observe observe );

    /**
     * 删除评论
     *
     * @param observeId 评论id
     * @author RenShiWei
     * Date: 2020/4/15 8:24
     */
    void deleteObserve ( Long observeId );

    /**
     * 功能描述：修改评论
     *
     * @param observe 修改的评论信息
     * @author RenShiWei
     * Date: 2020/4/15 8:27
     */
    void updateObserve ( Observe observe );

    /**
     * 功能描述：根据博客id，查询此博客的所有评论信息
     * @param blogId 博客id
     * @return 博客的评论信息
     * @author RenShiWei
     * Date: 2020/4/15 8:29
     */
    List<ObserveBO> queryObserveByBlogId( Long blogId);
}
