package com.blog.module.business.mapper;

import com.blog.module.business.domain.Observe;
import com.blog.mapper.CommentMapper;
import com.blog.module.business.domain.bo.ObserveBO;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
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

    @Select("SELECT * FROM observe o LEFT JOIN visitor v " +
            "ON o.observer_id=v.id " +
            "WHERE o.blog_id=#{blogId} AND o.last_id is null")
    @Results({
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "blog_id", property = "blogId"),
            @Result(column = "observer_id", property = "observerId"),
            @Result(column = "observe_content", property = "observeContent"),
            @Result(column = "observer_id", property = "visitor",
                    one = @One(select = "com.blog.module.business.mapper.VisitorMapper.queryVisitorForObserve",
                            fetchType = FetchType.EAGER)),
            @Result(column = "create_date", property = "createDate"),
            @Result(column = "last_id", property = "lastId"),
            @Result(column = "is_delete", property = "delete"),
            @Result(column = "update_date", property = "updateDate")
    })
    List<ObserveBO> queryFristObserveList ( Long blogId );


    @Select("SELECT * FROM observe o LEFT JOIN visitor v " +
            "ON o.observer_id=v.id " +
            "WHERE o.blog_id=#{blogId} AND o.last_id is not null")
    @Results({
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "blog_id", property = "blogId"),
            @Result(column = "observer_id", property = "observerId"),
            @Result(column = "observe_content", property = "observeContent"),
            @Result(column = "observer_id", property = "visitor",
                    one = @One(select = "com.blog.module.business.mapper.VisitorMapper.queryVisitorForObserve",
                            fetchType = FetchType.EAGER)),
            @Result(column = "create_date", property = "createDate"),
            @Result(column = "last_id", property = "lastId"),
            @Result(column = "is_delete", property = "delete"),
            @Result(column = "update_date", property = "updateDate")
    })
    List<ObserveBO> querySecondObserveList ( Long blogId );


}
