package com.blog.module.business.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 功能描述：博客和博客标签中间表
 *
 * @author RenShiWei
 * Date: 2020/4/10 21:32
 **/
@Entity
@Data
@Table(name="blog_label_middle")
@ApiModel
public class BlogLabelMiddle implements Serializable {

    /** 所属博客id */
    @Column(name="blog_id")
    @ApiModelProperty(value = "所属博客id")
    @NotNull(message = "所属博客id不能为空")
    private Long blogId;

    /** 所属博客标签 */
    @Column(name="label_id")
    @ApiModelProperty(value = "所属博客标签")
    @NotNull(message = "所属博客标签不能为空")
    private Long labelId;

}