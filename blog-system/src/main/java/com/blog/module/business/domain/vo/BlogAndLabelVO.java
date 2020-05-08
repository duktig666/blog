package com.blog.module.business.domain.vo;

import com.blog.module.business.domain.Blog;
import lombok.Data;

import java.util.List;

/**
 * 功能描述：
 *
 * @author RenShiWei
 * Date: 2020/5/7 18:20
 **/
@Data
public class BlogAndLabelVO {

    private Blog blog;

    private List<Long> blogLabelIds;

}

