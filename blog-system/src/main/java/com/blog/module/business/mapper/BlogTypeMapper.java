package com.blog.module.business.mapper;

import com.blog.module.business.domain.BlogType;
import com.blog.mapper.CommentMapper;
import org.springframework.stereotype.Component;

/**
 * 功能描述：BlogType访问数据库类
 *
 * @author RenShiWei
 * Date: 2020/4/11 9:40
 **/
@Component
public interface BlogTypeMapper extends CommentMapper<BlogType> {
}
