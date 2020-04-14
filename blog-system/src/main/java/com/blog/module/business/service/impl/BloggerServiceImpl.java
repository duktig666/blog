package com.blog.module.business.service.impl;

import com.blog.exception.BaseException;
import com.blog.module.business.domain.Blogger;
import com.blog.module.business.mapper.BloggerMapper;
import com.blog.module.business.service.BloggerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

/**
 * Description：
 *
 * @author jiaoqianjin
 * Date: 2020/4/11 11:13
 **/
@Service
public class BloggerServiceImpl implements BloggerService {
    @Autowired
    private BloggerMapper bloggerMapper;

    /**
     * 功能描述：新增博主信息
     * @param
     * @return
     * @author jiaoqianjin
     * Date: 2020/4/13 8:57
     */
    @Override
    @Transactional
    public void saveBlogger(Blogger blogger) {
        int res = this.bloggerMapper.insertSelective(blogger);
        if (res == 0) {
            throw new BaseException("新增博主信息失败！");
        }
        System.out.println(blogger.getId());
    }

    /**
     * 功能描述：根据博主id查询博主信息
     *
     * @param bloggerId
     * @return Blogger
     * @author jiaoqianjin
     * Date: 2020/4/13 9:49
     */
    @Override
    public Blogger queryBlogger(Long bloggerId) {
        Blogger blogger = this.bloggerMapper.selectByPrimaryKey(bloggerId);
        if (blogger == null) {
            throw new BaseException("暂无此博主信息！");
        }
        return blogger;
    }

    /**
     * 功能描述：根据博主id 修改对应博主信息
     *
     * @param blogger
     * @return
     * @author jiaoqianjin
     * Date: 2020/4/13 10:09
     */
    @Override
    public void updateBlogger(Blogger blogger) {
        int res = this.bloggerMapper.updateByPrimaryKey(blogger);
        if (res == 0) {
            throw new BaseException("修改博主信息失败！");
        }
    }

    /**
     * 功能描述：根据博主id 删除对应博主信息
     *
     * @param bloggerId
     * @return
     * @author jiaoqianjin
     * Date: 2020/4/13 10:11
     */
    @Override
    public void deleteBlogger(Long bloggerId) {
        int res = this.bloggerMapper.deleteByPrimaryKey(bloggerId);
        if (res == 0) {
            throw new BaseException("删除博主信息失败！");
        }
    }


}
