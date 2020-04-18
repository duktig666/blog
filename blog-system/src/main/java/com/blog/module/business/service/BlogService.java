package com.blog.module.business.service;

import com.blog.module.business.domain.Blog;
import com.blog.module.business.domain.bo.BlogBO;
import com.blog.page.dto.PageResultDTO;
import com.blog.page.vo.PageVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Description：博客相关业务处理
 *
 * @author jiaoqianjin
 * Date: 2020/4/11 11:13
 **/
public interface BlogService {

    /**
     * 功能描述：新增博客（需要维护中间表）
     *
     * @param blog         博客信息
     * @param blogLabelIds 博客标签id集合
     * @author RenShiWei
     * Date: 2020/4/14 11:13
     */
    void saveBlog ( Blog blog, List<Long> blogLabelIds );

    /**
     * 功能描述：根据博客id，删除一条博客（并维护中间表）
     *
     * @param blogId 博客id
     * @author RenShiWei
     * Date: 2020/4/14 11:33
     */
    void deleteBlog ( Long blogId );

    /**
     * 功能描述：根据博客id集合，批量删除博客（并维护中间表）
     *
     * @param blogIds 博客id集合
     * @author RenShiWei
     * Date: 2020/4/14 11:34
     */
    void deleteBlogs (List<Long> blogIds );

    /**
     * 功能描述：修改博客信息（并维护中间表，先删除，在新增）
     *
     * @param blog         修改的博客信息
     * @param blogLabelIds 博客标签id集合（维护中间表）
     * @author RenShiWei
     * Date: 2020/4/14 19:49
     */
    void updateBlog ( Blog blog, List<Long> blogLabelIds );

    /**
     * 功能描述：根据博客id，查询一条博客的详细信息（包含博客类型和博客标签）
     *
     * @param blogId 博客id
     * @return 封装的博客信息（包含博客类型和博客标签）
     * @author RenShiWei
     * Date: 2020/4/14 19:58
     */
    BlogBO queryBlogByBlogId ( Long blogId );

    /**
     * 功能描述：查询所有博客的集合（可分页、排序查询；可以根据博客标题、博客正文、博客摘要进行模糊查询）
     *
     * @param pageVo 分页、排序信息
     * @param blogDimSearchStr 模糊查询所需字段（可以根据博客标题、博客正文、博客摘要进行模糊查询）
     * @return 博客信息的集合
     * @author RenShiWei
     * Date: 2020/4/14 21:08
     */
    PageResultDTO<Blog> queryBlogList ( PageVO pageVo, String blogDimSearchStr );
}
