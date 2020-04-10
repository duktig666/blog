package com.blog.module.business.domain;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

/**
 * 功能描述：博主信息表
 *
 * @author RenShiWei
 * Date: 2020/4/10 21:32
 **/
@Entity
@Data
@Table(name="blogger")
public class Blogger implements Serializable {

    /** 博主id */
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    /** 博主昵称 */
    @Column(name = "nickname")
    private String nickname;

    /** 年龄 */
    @Column(name = "age")
    private Integer age;

    /** 手机号 */
    @Column(name = "phone")
    private String phone;

    /** 邮箱 */
    @Column(name = "email")
    private String email;

    /** 职业 */
    @Column(name = "profession")
    private String profession;

    /** 个人简介 */
    @Column(name = "intro")
    private String intro;

    /** 博主头像 */
    @Column(name = "picture")
    private String picture;

    /** 创建时间 */
    @Column(name="creat_date")
    private Timestamp createDate;

    /** 修改时间 */
    @Column(name="update_date")
    private Timestamp updateDate;


}