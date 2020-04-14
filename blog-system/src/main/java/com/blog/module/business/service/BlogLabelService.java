package com.blog.module.business.service;

import com.blog.module.business.domain.BlogLabel;
import com.blog.page.dto.PageResultDTO;
import com.blog.page.vo.PageVO;

import java.util.List;

/**
 * Description：博客标签的业务处理
 *
 * @author jiaoqianjin
 * Date: 2020/4/11 11:13
 **/
public interface BlogLabelService {

    /**
     * 功能描述： 新增博客标签
     *
     * @param blogLabel 博客标签实体
     * @author RenShiWei
     * Date: 2020/4/13 8:45
     */
    void saveBlogLabel ( BlogLabel blogLabel );

    /**
     * 功能描述： 删除单个博客标签
     *
     * @param blogLabelId 博客标签id
     * @author RenShiWei
     * Date: 2020/4/13 9:00
     */
    void deleteBlogLabel ( Long blogLabelId );

    /**
     * 功能描述： 批量删除博客标签
     *
     * @param blogLabelIds 博客标签集合
     * @author RenShiWei
     * Date: 2020/4/13 9:00
     */
    void deleteBlogLabels ( List<Long> blogLabelIds );

    /**
     * 功能描述：修改博客标签信息
     *
     * @param blogLabel 博客标签实体
     * @author RenShiWei
     * Date: 2020/4/13 9:00
     */
    void updateBlogLabel ( BlogLabel blogLabel );

    /**
     * 功能描述：根据博客标签id，查询一条博客标签信息
     *
     * @param blogLabelId 博客标签id
     * @return 一条博客标签信息
     * @author RenShiWei
     * Date: 2020/4/13 9:16
     */
    BlogLabel queryBlogLabelById ( Long blogLabelId );

    /**
     * 功能描述：查询所有的博客标签（可以分页和排序）
     *
     * @param pageVo 前台分页（非必要参数）
     * @return 博客标签集合（可分页和排序）
     * @author RenShiWei
     * Date: 2020/4/13 9:16
     */
    PageResultDTO<BlogLabel> queryBlogLabelAll ( PageVO pageVo );

}
