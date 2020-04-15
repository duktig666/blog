package com.blog.module.business.mapper;

import com.blog.module.business.domain.Visitor;
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
 * 功能描述：Visitor访问数据库类
 *
 * @author RenShiWei
 * Date: 2020/4/11 9:40
 **/
@Component
public interface VisitorMapper extends CommentMapper<Visitor> {

    @Select("SELECT * FROM visitor WHERE id=#{id}")
    @Results({
            @Result(id=true,column="id",property="id"),
            @Result(column="create_date",property="createDate"),
            @Result(column="last_id",property="lastId"),
            @Result(column="is_delete",property="delete"),
            @Result(column="update_date",property="updateDate")
    })
    Visitor queryVisitorForObserve(Long id);

}
