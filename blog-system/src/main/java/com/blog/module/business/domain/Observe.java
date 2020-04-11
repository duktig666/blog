package com.blog.module.business.domain;

import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

/**
 * 功能描述：评论表
 *
 * @author RenShiWei
 * Date: 2020/4/10 21:32
 **/
@Entity
@Data
@Table(name="observe")
@ApiModel
public class Observe implements Serializable {

    /** 评论id */
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @ApiModelProperty(value = "评论id")
    private Long id;

    /** 所属博客id */
    @Column(name="blog_id")
    @ApiModelProperty(value = "所属博客id")
    private Long blogId;

    /** 评论者id */
    @Column(name="observer_id")
    @ApiModelProperty(value = "评论者id")
    private Long observerId;

    /** 评论者类型 */
    @Column(name="observer_type")
    @ApiModelProperty(value = "评论者类型")
    private Integer observerType;

    /** 评论内容 */
    @Column(name="observe_content")
    @ApiModelProperty(value = "评论内容")
    private String observeContent;

    /** 评论上一级的id */
    @Column(name="last_id")
    @ApiModelProperty(value = "评论上一级的id")
    private Long lastId;

    /** 是否删除 */
    @Column(name="is_delete")
    @ApiModelProperty(value = "是否删除")
    private Boolean delete;

    /** 创建时间（评论时间） */
    @Column(name="create_date")
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "创建时间（评论时间）")
    private Timestamp createDate;

    /** 修改删除 */
    @Column(name="update_date")
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "修改删除")
    private Timestamp updateDate;

}