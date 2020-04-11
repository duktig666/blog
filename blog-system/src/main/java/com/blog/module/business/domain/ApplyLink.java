package com.blog.module.business.domain;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 功能描述：申请链接表
 *
 * @author RenShiWei
 * Date: 2020/4/10 21:32
 **/
@Entity
@Data
@Table(name="apply_link")
public class ApplyLink implements Serializable {

    /** 申请链接id */
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    /** 申请网站标题 */
    @Column(name="title")
    private String title;

    /** 申请网站地址 */
    @Column(name="website")
    private String website;

    /** 申请理由 */
    @Column(name="apply_reason")
    private String applyReason;

    /** 申请人邮箱 */
    @Column(name="email")
    private String email;

    /** 申请状态 */
    @Column(name="state")
    private Integer state;

    /** 创建时间 */
    @Column(name="create_date")
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Timestamp createDate;

    /** 修改时间 */
    @Column(name="update_date")
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Timestamp updateDate;


}