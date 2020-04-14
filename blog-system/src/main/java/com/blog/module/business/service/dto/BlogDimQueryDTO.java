package com.blog.module.business.service.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 功能描述：博客模糊查询DTO
 *
 * @author RenShiWei
 * Date: 2020/4/14 22:07
 **/
@ApiModel("博客模糊查询DTO")
@Data
public class BlogDimQueryDTO {

    /** 博客标题 */
    @ApiModelProperty(value = "博客标题")
    private String title;

    /** 博客摘要 */
    @ApiModelProperty(value = "博客摘要")
    private String summary;

    /** 博客正文 */
    @ApiModelProperty(value = "博客正文")
    private String content;

}

