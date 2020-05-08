package com.blog.module.business.mapper;

import com.blog.module.business.domain.Blog;
import com.blog.mapper.CommentMapper;
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
    int saveBlogLabelMiddle ( @Param("blogId") Long blogId, @Param("blogLabelIds") List<Long> blogLabelIds );

    /**
     * 功能描述：根据博客id，删除博客和博客标签中间表的与这篇博客相关的所有博客标签
     *
     * @param blogId 博客id
     * @return 删除的记录数
     * @author RenShiWei
     * Date: 2020/4/14 19:40
     */
    @Delete("DELETE FROM blog_label_middle WHERE blog_id=#{blogId}")
    int deleteBlogLabelMiddleByBlogId ( @Param("blogId") Long blogId );

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
    int deleteBlogLabelMiddleByBlogIds ( @Param("blogIds") List<Long> blogIds );

    /**
     * 功能描述：根据博客id，查询博客标签id集合
     *
     * @param blogId 博客id
     * @return 博客标签id集合
     * @author RenShiWei
     * Date: 2020/4/14 20:26
     */
    @Select("SELECT label_id FROM blog_label_middle WHERE blog_id=#{blogId}")
    List<Long> queryBlogLabelsByBlogId ( @Param("blogId") Long blogId );

    /**
     * 功能描述：查询博客的浏览量
     *
     * @return 浏览量
     * @author RenShiWei
     * Date: 2020/5/5 11:52
     */
    @Select("SELECT SUM(visit_number)FROM blog")
    Integer queryBlogVisitCount ();

    /**
     * 功能描述：查询博客的点赞量
     *
     * @return 点赞量
     * @author RenShiWei
     * Date: 2020/5/5 11:52
     */
    @Select("SELECT SUM(like_number) FROM blog")
    Integer queryBlogLikeCount ();

    /**
     * 功能描述：查询博客的回复量
     *
     * @return 回复量
     * @author RenShiWei
     * Date: 2020/5/5 11:52
     */
    @Select("SELECT SUM(observe_number) FROM blog")
    Integer queryBlogObserveCount ();

    /**
     * 功能描述：根据博客标签id查询博客集合
     *
     * @param
     * @return
     * @author jiaoqianjin
     * Date: 2020/5/8 20:27
     */
    @Select("SELECT l.*,b.* FROM blog_label_middle bl " +
            "LEFT JOIN blog_label l ON l.id=bl.label_id " +
            "LEFT JOIN blog b ON b.id=bl.blog_id " +
            "WHERE bl.label_id = #{labelId}")
    List<Blog> queryByLabelId(@Param("labelId")Long labelId);
//    /**
//     * 功能描述：根据博客标签id集合查询博客id集合
//     *
//     * @param labelIds 博客标签id集合
//     * @return 博客id集合
//     * @author jiaoqianjin
//     * Date: 2020/5/8 16:46
//     */
//    List<Long> queryBlogIdsByBlogLabelIds(List<Long> labelIds);
//
//    List<Blog> queryByBlogIds(List<Long> blogIds);
}
