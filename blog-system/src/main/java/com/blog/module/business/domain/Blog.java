package com.blog.module.business.domain;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 功能描述：博客表
 *
 * @author RenShiWei
 * Date: 2020/4/10 21:32
 **/
@Entity
@Data
@Table(name="blog")
public class Blog  implements Serializable {

    /** 博客id */
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    /** 所属博客类型id */
    @Column(name="type_id")
    private Long typeId;

    /** 博客标题 */
    @Column(name="title")
    private String title;

    /** 博客摘要 */
    @Column(name="summary")
    private String summary;

    /** 博客正文 */
    @Column(name="content")
    private String content;

    /** 浏览量 */
    @Column(name="visit_number")
    private Integer visitNumber;

    /** 点赞量 */
    @Column(name="like_number")
    private Integer likeNumber;

    /** 评论量 */
    @Column(name="observe_number")
    private Integer observeNumber;

    /** 创建时间 */
    @Column(name="create_date")
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Timestamp createDate;

    /** 修改时间 */
    @Column(name="update_date")
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Timestamp updateDate;

    /** 是否删除 */
    @Column(name="is_delete")
    private Boolean delete;




}