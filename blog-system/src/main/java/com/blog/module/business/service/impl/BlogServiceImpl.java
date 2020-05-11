package com.blog.module.business.service.impl;

import com.blog.exception.BaseException;
import com.blog.module.business.domain.Blog;
import com.blog.module.business.domain.BlogLabel;
import com.blog.module.business.domain.BlogType;
import com.blog.module.business.domain.Observe;
import com.blog.module.business.domain.bo.BlogBO;
import com.blog.module.business.mapper.ObserveMapper;
import com.blog.module.business.service.dto.BlogCountDTO;
import com.blog.module.business.mapper.BlogLabelMapper;
import com.blog.module.business.mapper.BlogMapper;
import com.blog.module.business.mapper.BlogTypeMapper;
import com.blog.module.business.service.BlogService;
import com.blog.page.dto.PageResultDTO;
import com.blog.page.vo.PageVO;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.List;

/**
 * Description：
 *
 * @author jiaoqianjin
 * Date: 2020/4/11 11:13
 **/
@Service
public class BlogServiceImpl implements BlogService {

    @Autowired
    private BlogMapper blogMapper;

    @Autowired
    private BlogTypeMapper blogTypeMapper;

    @Autowired
    private BlogLabelMapper blogLabelMapper;

    @Autowired
    private ObserveMapper observeMapper;

    /**
     * 功能描述：新增博客（需要维护中间表）
     *
     * @param blog         博客信息
     * @param blogLabelIds 博客标签id集合
     * @author RenShiWei
     * Date: 2020/4/14 11:13
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveBlog ( Blog blog, List<Long> blogLabelIds ) {
        //保存博客信息到博客表
        int count = blogMapper.insertSelective(blog);
        if (count == 0) {
            throw new BaseException("新增博客失败");
        }
        //维护中间表
        count = blogMapper.saveBlogLabelMiddle(blog.getId(), blogLabelIds);
        if (count != blogLabelIds.size()) {
            throw new BaseException("为博客批量新增博客标签失败");
        }
    }

    /**
     * 功能描述：根据博客id，删除一条博客（并维护中间表）
     *
     * @param blogId 博客id
     * @author RenShiWei
     * Date: 2020/4/14 11:33
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteBlog ( Long blogId ) {
        //删除一条博客
        int count = blogMapper.deleteByPrimaryKey(blogId);
        if (count == 0) {
            throw new BaseException("删除博客失败");
        }
        //维护中间表
        count = blogMapper.deleteBlogLabelMiddleByBlogId(blogId);
        if (count == 0) {
            throw new BaseException("删除博客的博客标签失败，导致博客删除失败");
        }
        //删除博客的时候也删除这篇博客的评论信息
        deleteObserveByBlogId(blogId);
    }

    /**
     * 功能描述：根据博客id，删除此篇博客的所有评论信息
     * 主要用于删除博客时，删除对应的评论信息
     *
     * @param blogId 博客id
     * @author RenShiWei
     * Date: 2020/5/11 10:00
     */
    private void deleteObserveByBlogId ( Long blogId ) {
        Example example = new Example(Observe.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("blogId", blogId);
        observeMapper.deleteByExample(example);
    }

    /**
     * 功能描述：根据博客id集合，批量删除博客（并维护中间表）
     *
     * @param blogIds 博客id集合
     * @author RenShiWei
     * Date: 2020/4/14 11:34
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteBlogs ( List<Long> blogIds ) {
        //批量删除博客
        int count = blogMapper.deleteByIdList(blogIds);
        if (count == 0) {
            throw new BaseException("批量删除博客失败");
        }
        //维护中间表
        count = blogMapper.deleteBlogLabelMiddleByBlogIds(blogIds);
        if (count == 0) {
            throw new BaseException("批量删除博客的博客标签失败，导致博客删除失败");
        }
        //批量删除博客的时候也删除这篇博客的评论信息
        for (Long blogId : blogIds) {
            deleteObserveByBlogId(blogId);
        }
    }

    /**
     * 功能描述：修改博客信息
     *
     * @param blog         修改的博客信息
     * @param blogLabelIds 博客标签id集合（维护中间表）
     * @author RenShiWei
     * Date: 2020/4/14 19:49
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateBlog ( Blog blog, List<Long> blogLabelIds ) {
        //修改博客
        int count = blogMapper.updateByPrimaryKeySelective(blog);
        if (count == 0) {
            throw new BaseException("修改博客失败");
        }
        //维护中间表
        //根据博客id，先删除此博客的所有标签信息
        count = blogMapper.deleteBlogLabelMiddleByBlogId(blog.getId());
        if (count == 0) {
            throw new BaseException("修改博客标签失败（删除博客标签失败）");
        }
        //在中间表中新增标签信息
        count = blogMapper.saveBlogLabelMiddle(blog.getId(), blogLabelIds);
        if (count == 0) {
            throw new BaseException("修改博客标签失败（重新新增博客标签失败）");
        }
    }

    /**
     * 功能描述：根据博客id，查询一条博客的详细信息
     *
     * @param blogId 博客id
     * @return 封装的博客信息（包含博客类型和博客标签）
     * @author RenShiWei
     * Date: 2020/4/14 19:58
     */
    @Override
    public BlogBO queryBlogByBlogId ( Long blogId ) {
        //根据博客id，查询博客信息
        Blog blog = blogMapper.selectByPrimaryKey(blogId);
        //根据博客类型id，查询博客类型信息
        BlogType blogType = blogTypeMapper.selectByPrimaryKey(blog.getTypeId());
        //根据博客id，查询博客标签id集合
        List<Long> blogLabelIds = blogMapper.queryBlogLabelsByBlogId(blogId);
        //根据博客id集合，查询博客标签信息
        List<BlogLabel> blogLabelList = blogLabelMapper.selectByIdList(blogLabelIds);
        //通过构造方法返回封装的博客信息
        return new BlogBO(blog, blogType, blogLabelList);
    }

    /**
     * 功能描述：查询所有博客的集合（可分页、排序查询）
     *
     * @param pageVo 分页、排序信息
     * @return 博客信息的集合
     * @author RenShiWei
     * Date: 2020/4/14 21:08
     */
    @Override
    public PageResultDTO<BlogBO> queryBlogList ( PageVO pageVo, String blogDimSearchStr ) {
        //判断是否需要分页和排序
        if (pageVo != null) {
            PageHelper.startPage(pageVo.getCurrentPage(), pageVo.getRows(), pageVo.getSort());
        }
        /*
           模糊查询
         */
        // 初始化example对象
        Example example = new Example(Blog.class);
        Example.Criteria criteria = example.createCriteria();
        //判断是否需要模糊查询
        if (StringUtils.isNotEmpty(blogDimSearchStr)) {
            criteria.orLike("title", "%" + blogDimSearchStr + "%")
                    .orLike("summary", "%" + blogDimSearchStr + "%")
                    .orLike("content", "%" + blogDimSearchStr + "%");
        }
        // 封装BlogBO
        List<BlogBO> blogBOS = new ArrayList<>();
        List<Blog> blogs = this.blogMapper.selectByExample(example);
        if (CollectionUtils.isEmpty(blogs)) {
            throw new BaseException("暂无博客信息！");
        }
        blogs.forEach(blog -> {
            //根据博客类型id，查询博客类型信息
            BlogType blogType = blogTypeMapper.selectByPrimaryKey(blog.getTypeId());
            //根据博客id，查询博客标签id集合
            List<Long> blogLabelIds = blogMapper.queryBlogLabelsByBlogId(blog.getId());
            System.out.println(blogLabelIds);
            //根据博客id集合，查询博客标签信息
            List<BlogLabel> blogLabelList = blogLabelMapper.selectByIdList(blogLabelIds);
            //将 博客，博客标签，博客类型 封装在 BlogBo中
            BlogBO blogBO = new BlogBO(blog, blogType, blogLabelList);
            blogBOS.add(blogBO);
        });
        //查询结果转换为PageInfo对象
        PageInfo<BlogBO> boPageInfo = new PageInfo<>(blogBOS);
        //返回封装的分页结果集
        return new PageResultDTO<>(boPageInfo.getTotal(), boPageInfo.getPageSize(), boPageInfo.getList());
    }

    /**
     * 功能描述：查询所有的博客信息(不包含博客类型和博客标签（可以分页和排序,可以根据博客标题、博客正文、博客摘要进行模糊查询))
     *
     * @param pageVo           分页、排序信息
     * @param blogDimSearchStr 模糊查询所需字段（可以根据博客标题、博客正文、博客摘要进行模糊查询）
     * @return 博客信息的集合
     * @author RenShiWei
     * Date: 2020/5/5 20:40
     */
    @Override
    public PageResultDTO<Blog> queryBlogAll ( PageVO pageVo, String blogDimSearchStr ) {
        //判断是否需要分页和排序
        if (pageVo != null) {
            PageHelper.startPage(pageVo.getCurrentPage(), pageVo.getRows(), pageVo.getSort());
        }
        /*
           模糊查询
         */
        // 初始化example对象
        Example example = new Example(Blog.class);
        Example.Criteria criteria = example.createCriteria();
        //判断是否需要模糊查询
        if (StringUtils.isNotEmpty(blogDimSearchStr)) {
            criteria.orLike("title", "%" + blogDimSearchStr + "%")
                    .orLike("summary", "%" + blogDimSearchStr + "%")
                    .orLike("content", "%" + blogDimSearchStr + "%");
        }
        List<Blog> blogs = this.blogMapper.selectByExample(example);
        if (CollectionUtils.isEmpty(blogs)) {
            throw new BaseException("暂无博客信息！");
        }
        //查询结果转换为PageInfo对象
        PageInfo<Blog> boPageInfo = new PageInfo<>(blogs);
        //返回封装的分页结果集
        return new PageResultDTO<>(boPageInfo.getTotal(), boPageInfo.getPageSize(), boPageInfo.getList());
    }

    /**
     * 功能描述：查询博客的总访问量、点赞量、评论量
     *
     * @return 博客的总访问量、点赞量、评论量的DTO
     * @author RenShiWei
     * Date: 2020/5/5 12:14
     */
    @Override
    public BlogCountDTO queryBlogCount () {
        BlogCountDTO blogCountDTO = new BlogCountDTO();
        blogCountDTO.setVisitNumbers(blogMapper.queryBlogVisitCount());
        blogCountDTO.setLikeNumber(blogMapper.queryBlogLikeCount());
        blogCountDTO.setObserveNumber(blogMapper.queryBlogObserveCount());
        return blogCountDTO;
    }

    /**
     * 功能描述：根据博客类型ID，查询对应类型博客
     *
     * @param pageVo 分页 排序信息
     * @param typeId 博客类型id
     * @return 博客信息集合
     * @author jiaoqianjin
     * Date: 2020/5/6 9:34
     */
    @Override
    public PageResultDTO<Blog> queryBlogByType ( PageVO pageVo, Integer typeId ) {
        //判断是否需要分页和排序
        if (pageVo != null) {
            PageHelper.startPage(pageVo.getCurrentPage(), pageVo.getRows(), pageVo.getSort());
        }
        // 初始化example对象
        Example example = new Example(Blog.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("typeId", typeId);
        List<Blog> blogs = this.blogMapper.selectByExample(example);
        System.out.println(blogs);
        if (CollectionUtils.isEmpty(blogs)) {
            throw new BaseException("暂无类型博客信息！");
        }
        //查询结果转换为PageInfo对象
        PageInfo<Blog> boPageInfo = new PageInfo<>(blogs);
        //返回封装的分页结果集
        return new PageResultDTO<>(boPageInfo.getTotal(), boPageInfo.getPageSize(), boPageInfo.getList());
    }

    /**
     * 功能描述：根据博客标签ID，查询对应类型博客
     *
     * @param pageVo  分页 排序信息
     * @param labelId 博客标签id集合
     * @return
     * @author jiaoqianjin
     * Date: 2020/5/8 16:30
     */
    @Override
    public PageResultDTO<Blog> queryBlogByLabelId ( PageVO pageVo, Long labelId ) {
        //判断是否需要分页和排序
        if (pageVo != null) {
            PageHelper.startPage(pageVo.getCurrentPage(), pageVo.getRows(), pageVo.getSort());
        }
//        // 根据博客标签id 集合 查询 博客id集合
//        List<Long> blogIds = this.blogMapper.queryBlogIdsByBlogLabelIds(labelIds);
//        // 根据博客id 集合 查询 对应博客集合
//        List<Blog> blogs = this.blogMapper.queryByBlogIds(blogIds);
        List<Blog> blogs = this.blogMapper.queryByLabelId(labelId);
        if (CollectionUtils.isEmpty(blogs)) {
            throw new BaseException("暂无类型博客信息！");
        }
        //查询结果转换为PageInfo对象
        PageInfo<Blog> boPageInfo = new PageInfo<>(blogs);
        //返回封装的分页结果集
        return new PageResultDTO<>(boPageInfo.getTotal(), boPageInfo.getPageSize(), boPageInfo.getList());
    }

}
