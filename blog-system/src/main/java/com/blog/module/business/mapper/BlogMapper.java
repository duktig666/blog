package com.blog.module.business.mapper;

import com.blog.module.business.domain.Blog;
import com.blog.mapper.CommentMapper;
import com.blog.module.business.domain.bo.BlogBO;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;


/**
 * 功能描述：Blog访问数据库类
 *
 * @author RenShiWei
 * Date: 2020/4/11 9:40
 **/
@Component
public interface BlogMapper extends CommentMapper<Blog> {

    /**
     * 功能描述：批量往博客和博客标签中间表新增数据
     *
     * @param blogId       博客id
     * @param blogLabelIds 博客标签id集合
     * @return 插入记录数
     * @author RenShiWei
     * Date: 2020/4/14 12:53
     */
    @Insert({
            "<script>",
            "INSERT INTO blog_label_middle(blog_id, label_id) values ",
            "<foreach collection='blogLabelIds' item='item' index='index' separator=','>",
            "(#{blogId}, #{item})",
            "</foreach>",
            "</script>"
    })
    int saveBlogLabelMiddle (@Param("blogId") Long blogId,@Param("blogLabelIds") List<Long> blogLabelIds );

    /**
     * 功能描述：根据博客id，删除博客和博客标签中间表的与这篇博客相关的所有博客标签
     *
     * @param blogId 博客id
     * @return 删除的记录数
     * @author RenShiWei
     * Date: 2020/4/14 19:40
     */
    @Delete("DELETE FROM blog_label_middle WHERE blog_id=#{blogId}")
    int deleteBlogLabelMiddleByBlogId (@Param("blogId") Long blogId );

    /**
     * 功能描述：根据博客id集合，批量删除博客和博客标签中间表的与这篇博客相关的所有博客标签
     *
     * @param blogIds 博客id集合
     * @return 删除的记录数
     * @author RenShiWei
     * Date: 2020/4/14 19:46
     */
    @Delete("<script>" +
            "DELETE FROM blog_label_middle WHERE blog_id IN" +
            "<foreach collection='blogIds' item='id' open='(' separator=',' close=')' >" +
            "#{id}" +
            "</foreach>" +
            "</script>")
    int deleteBlogLabelMiddleByBlogIds (@Param("blogIds")  List<Long> blogIds );

    /**
     * 功能描述：根据博客id，查询博客标签id集合
     * @param blogId 博客id
     * @return 博客标签id集合
     * @author RenShiWei
     * Date: 2020/4/14 20:26
     */
    @Select("SELECT label_id FROM blog_label_middle WHERE blog_id=#{blogId}")
    List<Long> queryBlogLabelsByBlogId (@Param("blogId") Long blogId );
}
