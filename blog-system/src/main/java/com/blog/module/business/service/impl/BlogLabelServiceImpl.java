package com.blog.module.business.service.impl;

import com.blog.exception.BaseException;
import com.blog.module.business.domain.BlogLabel;
import com.blog.module.business.mapper.BlogLabelMapper;
import com.blog.module.business.service.BlogLabelService;
import com.blog.page.dto.PageResultDTO;
import com.blog.page.vo.PageVO;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * Description：
 *
 * @author jiaoqianjin
 * Date: 2020/4/11 11:13
 **/
@Service
public class BlogLabelServiceImpl implements BlogLabelService {

    @Autowired
    private BlogLabelMapper blogLabelMapper;

    /**
     * 功能描述： 新增博客标签
     *
     * @param blogLabel 博客标签实体
     * @author RenShiWei
     * Date: 2020/4/13 8:45
     */
    @Override
    public void saveBlogLabel ( BlogLabel blogLabel ) {
        int count = blogLabelMapper.insertSelective(blogLabel);
        if (count == 0) {
            throw new BaseException("新增博客标签失败！");
        }
    }

    /**
     * 功能描述： 删除单个博客标签
     *
     * @param blogLabelId 博客标签id
     * @author RenShiWei
     * Date: 2020/4/13 9:00
     */
    @Override
    public void deleteBlogLabel ( Long blogLabelId ) {
        int count = blogLabelMapper.deleteByPrimaryKey(blogLabelId);
        if (count == 0) {
            throw new BaseException("删除博客标签失败！");
        }
    }

    /**
     * 功能描述： 批量删除博客标签
     *
     * @param blogLabelIds 博客标签集合
     * @author RenShiWei
     * Date: 2020/4/13 9:00
     */
    @Override
    public void deleteBlogLabels ( List<Long> blogLabelIds ) {
        int count = blogLabelMapper.deleteByIdList(blogLabelIds);
        if (count == 0) {
            throw new BaseException("删除博客标签失败！");
        }
    }

    /**
     * 功能描述：修改博客标签信息
     *
     * @param blogLabel 博客标签实体
     * @author RenShiWei
     * Date: 2020/4/13 9:00
     */
    @Override
    public void updateBlogLabel ( BlogLabel blogLabel ) {
        int count = blogLabelMapper.updateByPrimaryKeySelective(blogLabel);
        if (count == 0) {
            throw new BaseException("修改博客标签失败！");
        }
    }

    /**
     * 功能描述：根据博客标签id，查询一条博客标签信息
     *
     * @param blogLabelId 博客标签id
     * @return 一条博客标签信息
     * @author RenShiWei
     * Date: 2020/4/13 9:16
     */
    @Override
    public BlogLabel queryBlogLabelById ( Long blogLabelId ) {
        BlogLabel blogLabel=blogLabelMapper.selectByPrimaryKey(blogLabelId);
        if (blogLabel==null){
            throw new BaseException("暂无此博客标签！");
        }
        return blogLabel;
    }

    /**
     * 功能描述：查询所有的博客标签（可以分页和排序）
     *
     * @param pageVo 前台分页（非必要参数）
     * @return 博客标签集合（可分页和排序）
     * @author RenShiWei
     * Date: 2020/4/13 9:16
     */
    @Override
    public PageResultDTO<BlogLabel> queryBlogLabelAll ( PageVO pageVo ) {
        if (pageVo != null) {
            PageHelper.startPage(pageVo.getCurrentPage(), pageVo.getRows(), pageVo.getSort());
        }
        //查询结果转换为Page对象
        Page<BlogLabel> blogTypePage = (Page<BlogLabel>) blogLabelMapper.selectAll();
        if (CollectionUtils.isEmpty(blogTypePage)) {
            throw new BaseException("暂无博客标签！");
        }
        return new PageResultDTO<>(blogTypePage.getTotal(), blogTypePage.getPageSize(), blogTypePage);
    }
}
