package com.blog.module.business.service;

import com.blog.module.business.domain.BlogType;
import com.blog.page.dto.PageResultDto;
import com.blog.page.vo.PageVo;

import java.util.List;

/**
 * Description：博客类型相关业务处理
 *
 * @author jiaoqianjin
 * Date: 2020/4/11 11:13
 **/
public interface BlogTypeService {

    /**
     * 功能描述： 新增博客类型
     *
     * @param blogType 博客类型实体
     * @author RenShiWei
     * Date: 2020/4/13 8:45
     */
    void saveBlogType ( BlogType blogType );

    /**
     * 功能描述： 删除单个博客类型
     *
     * @param blogTypeId 博客类型id
     * @author RenShiWei
     * Date: 2020/4/13 9:00
     */
    void deleteBlogType ( Long blogTypeId );

    /**
     * 功能描述： 批量删除博客类型
     *
     * @param blogTypeIds 博客类型集合
     * @author RenShiWei
     * Date: 2020/4/13 9:00
     */
    void deleteBlogTypes ( List<Long> blogTypeIds );

    /**
     * 功能描述：修改博客信息
     *
     * @param blogType 博客类型实体
     * @author RenShiWei
     * Date: 2020/4/13 9:00
     */
    void updateBlogType ( BlogType blogType );

    /**
     * 功能描述：根据博客类型id，查询一条博客类型信息
     *
     * @param blogTypeId 博客类型id
     * @return 一条博客类型信息
     * @author RenShiWei
     * Date: 2020/4/13 9:16
     */
    BlogType queryBlogTypeById ( Long blogTypeId );

    /**
     * 功能描述：查询所有的博客类型（可以分页和排序）
     *
     * @param pageVo 前台分页（非必要参数）
     * @return 博客类型集合（可分页和排序）
     * @author RenShiWei
     * Date: 2020/4/13 9:16
     */
    PageResultDto<BlogType> queryBlogTypeAll ( PageVo pageVo );

}
