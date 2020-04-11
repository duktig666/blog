package com.blog.module.business.domain;

import com.alibaba.fastjson.annotation.JSONField;
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
public class Observe implements Serializable {

    /** 评论id */
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    /** 所属博客id */
    @Column(name="blog_id")
    private Long blogId;

    /** 评论者id */
    @Column(name="observer_id")
    private Long observerId;

    /** 评论者类型 */
    @Column(name="observer_type")
    private Integer observerType;

    /** 评论内容 */
    @Column(name="observe_content")
    private String observeContent;

    /** 评论上一级的id */
    @Column(name="last_id")
    private Long lastId;

    /** 是否删除 */
    @Column(name="is_delete")
    private Boolean delete;

    /** 创建时间（评论时间） */
    @Column(name="create_date")
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Timestamp createDate;

    /** 修改删除 */
    @Column(name="update_date")
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Timestamp updateDate;

}