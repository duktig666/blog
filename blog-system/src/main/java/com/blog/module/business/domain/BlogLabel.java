package com.blog.module.business.domain;

import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

/**
 * 功能描述：博客标签表
 *
 * @author RenShiWei
 * Date: 2020/4/10 21:32
 **/
@Entity
@Data
@Table(name="blog_label")
@ApiModel
public class BlogLabel implements Serializable {

    /** 博客标签id */
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @ApiModelProperty(value = "博客标签id")
    @NotNull(message = "博客标签id不能为空")
    private Long id;

    /** 标签 */
    @Column(name="label")
    @ApiModelProperty(value = "标签")
    @NotBlank(message = "标签不能为空")
    private String label;

    /** 创建时间 */
    @Column(name="create_date")
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "创建时间")
    private Timestamp createDate;

    /** 修改时间 */
    @Column(name="update_date")
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "修改时间")
    private Timestamp updateDate;


}