package com.blog.module.business.domain;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

/**
 * 功能描述：申请链接表
 *
 * @author RenShiWei
 * Date: 2020/4/10 21:32
 **/
@Entity
@Data
@Table(name="visitor")
public class Visitor implements Serializable {

    /** 游客id */
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    /** 游客昵称 */
    @Column(name="nickname")
    private String nickname;

    /** 邮箱 */
    @Column(name="email")
    private String email;

    /** 游客头像 */
    @Column(name="picture")
    private String picture;

    /** 是否删除 */
    @Column(name="is_delete")
    private Boolean delete;

    /** 创建时间 */
    @Column(name="create_date")
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Timestamp createDate;

    /** 修改时间 */
    @Column(name="update_date")
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Timestamp updateDate;


}