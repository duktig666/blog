package com.blog.module.business.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.blog.exception.BaseException;
import com.blog.module.business.domain.BlogType;
import com.blog.module.business.mapper.BlogTypeMapper;
import com.blog.module.business.service.BlogTypeService;
import com.blog.page.dto.PageResultDTO;
import com.blog.page.vo.PageVO;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
public class BlogTypeServiceImpl implements BlogTypeService {

    @Autowired
    private BlogTypeMapper blogTypeMapper;

    /**
     * 功能描述： 新增博客类型
     *
     * @param blogType 博客类型实体
     * @author RenShiWei
     * Date: 2020/4/13 8:45
     */
    @Override
    public void saveBlogType ( BlogType blogType ) {
        int count = blogTypeMapper.insertSelective(blogType);
        if (count == 0) {
            throw new BaseException("新增博客类型失败！");
        }
    }

    /**
     * 功能描述： 删除单个博客类型
     *
     * @param blogTypeId 博客类型id
     * @author RenShiWei
     * Date: 2020/4/13 9:00
     */
    @Override
    public void deleteBlogType ( Long blogTypeId ) {
        int count = blogTypeMapper.deleteByPrimaryKey(blogTypeId);
        if (count == 0) {
            throw new BaseException("删除博客类型失败！");
        }
    }

    /**
     * 功能描述： 批量删除博客类型
     *
     * @param blogTypeIds 博客类型集合
     * @author RenShiWei
     * Date: 2020/4/13 9:00
     */
    @Override
    public void deleteBlogTypes ( List<Long> blogTypeIds ) {
        if (CollectionUtil.isEmpty(blogTypeIds)) {
            throw new BaseException(HttpStatus.NOT_FOUND, "传入所需要删除的ID集合不能为空");
        }
        int count = blogTypeMapper.deleteByIdList(blogTypeIds);
        if (count == 0) {
            throw new BaseException("删除博客类型失败！");
        }
    }

    /**
     * 功能描述：
     *
     * @param blogType 博客类型实体
     * @author RenShiWei
     * Date: 2020/4/13 9:00
     */
    @Override
    public void updateBlogType ( BlogType blogType ) {
        int count = blogTypeMapper.updateByPrimaryKeySelective(blogType);
        if (count == 0) {
            throw new BaseException("修改博客类型失败！");
        }
    }

    /**
     * 功能描述：根据博客类型id，查询一条博客类型信息
     *
     * @param blogTypeId 博客类型id
     * @return 一条博客类型信息
     * @author RenShiWei
     * Date: 2020/4/13 9:16
     */
    @Override
    public BlogType queryBlogTypeById ( Long blogTypeId ) {
        BlogType blogType = blogTypeMapper.selectByPrimaryKey(blogTypeId);
        if (blogType == null) {
            throw new BaseException("暂无此博客类型！");
        }
        return blogType;
    }

    /**
     * 功能描述：查询所有的博客类型
     *
     * @param pageVo 前台分页（非必要参数）
     * @return 博客类型集合（可分页和排序）
     * @author RenShiWei
     * Date: 2020/4/13 9:16
     */
    @Override
    public PageResultDTO<BlogType> queryBlogTypeAll ( PageVO pageVo ) {
        if (pageVo != null) {
            PageHelper.startPage(pageVo.getCurrentPage(), pageVo.getRows(), pageVo.getSort());
        }
        //查询结果转换为Page对象
        Page<BlogType> blogTypePage = (Page<BlogType>) blogTypeMapper.selectAll();
        if (CollectionUtils.isEmpty(blogTypePage)) {
            throw new BaseException("暂无博客类型！");
        }
        return new PageResultDTO<>(blogTypePage.getTotal(), blogTypePage.getPageSize(), blogTypePage);
    }
}
