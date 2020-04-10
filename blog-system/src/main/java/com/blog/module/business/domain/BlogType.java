package com.blog.module.business.domain;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

/**
 * 功能描述：博客类型表
 *
 * @author RenShiWei
 * Date: 2020/4/10 21:32
 **/
@Entity
@Data
@Table(name="blog_type")
public class BlogType implements Serializable {

    /** 博客类型id */
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    /** 博客类型 */
    @Column(name = "type")
    private String type;

    /** 创建时间 */
    @Column(name="creat_date")
    private Timestamp createDate;

    /** 修改时间 */
    @Column(name="update_date")
    private Timestamp updateDate;

}