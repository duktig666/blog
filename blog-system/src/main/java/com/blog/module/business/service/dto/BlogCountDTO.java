package com.blog.module.business.service.dto;

import lombok.Data;

/**
 * 功能描述：
 *
 * @author RenShiWei
 * Date: 2020/5/5 11:50
 **/
@Data
public class BlogCountDTO {

    /** 博客总浏览量 */
    private Integer visitNumbers;

    /** 博客总点赞量 */
    private Integer likeNumber;

    /** 博客总评论量 */
    private Integer observeNumber;

}

