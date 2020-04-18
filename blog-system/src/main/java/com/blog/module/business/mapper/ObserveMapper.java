package com.blog.module.business.mapper;

import com.blog.module.business.domain.Observe;
import com.blog.mapper.CommentMapper;
import com.blog.module.business.domain.bo.ObserveNodeBO;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 功能描述：Observe访问数据库类
 *
 * @author RenShiWei
 * Date: 2020/4/11 9:40
 **/
@Component
public interface ObserveMapper extends CommentMapper<Observe> {

    /**
     * 功能描述：根据博客id和lastId为空，查询所有的一级评论信息集合
     * @param blogId 博客id
     * @return 一级评论信息集合
     * @author RenShiWei
     * Date: 2020/4/16 10:37
     */
    @Select("SELECT * FROM observe o LEFT JOIN user u " +
            "ON o.observer_id=u.id " +
            "WHERE o.blog_id=#{blogId} AND o.last_id is null")
    @Results({
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "blog_id", property = "blogId"),
            @Result(column = "observer_id", property = "observerId"),
            @Result(column = "observe_content", property = "observeContent"),
            @Result(column = "observer_id", property = "user",
                    one = @One(select = "com.blog.module.business.mapper.UserMapper.queryUserForObserve",
                            fetchType = FetchType.EAGER)),
            @Result(column = "last_id", property = "lastId"),
            @Result(column = "is_delete", property = "delete"),
            @Result(column = "create_date", property = "createDate"),
            @Result(column = "update_date", property = "updateDate")
    })
    List<ObserveNodeBO> queryFirstObserveList (@Param("blogId") Long blogId );


    /**
     * 功能描述：根据博客id和lastId不为空，查询所有的二级评论信息集合
     * @param blogId 博客id
     * @return 二级评论信息集合
     * @author RenShiWei
     * Date: 2020/4/16 10:37
     */
    @Select("SELECT * FROM observe o LEFT JOIN user u " +
            "ON o.observer_id=u.id " +
            "WHERE o.blog_id=#{blogId} AND o.last_id is not null")
    @Results({
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "blog_id", property = "blogId"),
            @Result(column = "observer_id", property = "observerId"),
            @Result(column = "observe_content", property = "observeContent"),
            @Result(column = "observer_id", property = "user",
                    one = @One(select = "com.blog.module.business.mapper.UserMapper.queryUserForObserve",
                            fetchType = FetchType.EAGER)),
            @Result(column = "last_id", property = "lastId"),
            @Result(column = "is_delete", property = "delete"),
            @Result(column = "create_date", property = "createDate"),
            @Result(column = "update_date", property = "updateDate")
    })
    List<ObserveNodeBO> querySecondObserveList (@Param("blogId") Long blogId );


}
