package com.blog.module.business.domain;

import lombok.Data;

import javax.persistence.*;
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
public class BlogLabel implements Serializable {

    /** 博客标签id */
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    /** 标签 */
    @Column(name="label")
    private String label;

    /** 创建时间 */
    @Column(name="creat_date")
    private Timestamp createDate;

    /** 修改时间 */
    @Column(name="update_date")
    private Timestamp updateDate;


}