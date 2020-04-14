package com.blog.module.business.domain.bo;

import com.blog.module.business.domain.Blog;
import com.blog.module.business.domain.BlogLabel;
import com.blog.module.business.domain.BlogType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * 功能描述：封装博客、博客类型和博客标签的BO类
 *
 * @author RenShiWei
 * Date: 2020/4/14 11:04
 **/
@Data
@AllArgsConstructor
public class BlogBO{

    /** 博客信息 */
    private Blog blog;

    /** 文章所属的博客类型 */
    private BlogType blogType;

    /** 文章所属的博客标签集合 */
    private List<BlogLabel> blogLabelList;


}

