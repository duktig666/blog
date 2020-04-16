package com.blog.module.business.mapper;

import com.blog.module.business.domain.User;
import com.blog.mapper.CommentMapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

/**
 * 功能描述：Visitor访问数据库类
 *
 * @author RenShiWei
 * Date: 2020/4/11 9:40
 **/
@Component
public interface UserMapper extends CommentMapper<User> {

    /**
     * 功能描述：根据主键id查询用户信息
     * （在observe中一对一关系使用）
     *
     * @param id 用户id
     * @return 用户信息
     * @author RenShiWei
     * Date: 2020/4/16 10:43
     */
    @Select("SELECT * FROM user WHERE id=#{id}")
    @Results({
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "create_date", property = "createDate"),
            @Result(column = "last_id", property = "lastId"),
            @Result(column = "is_delete", property = "delete"),
            @Result(column = "update_date", property = "updateDate")
    })
    User queryUserForObserve ( Long id );

}
