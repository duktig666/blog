package com.blog.module.business.domain;

import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Value;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
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
@ApiModel
public class Blogger implements Serializable {

    /** 博主id */
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @ApiModelProperty(value = "博主id")
    @NotNull(message = "博客id不能为空")
    private Long id;

    /** 博主昵称 */
    @Column(name = "nickname")
    @ApiModelProperty(value = "博主昵称")
    @NotBlank(message = "博主昵称不能为空")
    private String nickname;

    /** 年龄 */
    @Column(name = "age")
    @ApiModelProperty(value = "年龄")
    private Integer age;

    /** 手机号 */
    @Column(name = "phone")
    @ApiModelProperty(value = "手机号")
    private String phone;

    /** 邮箱 */
    @Column(name = "email")
    @ApiModelProperty(value = "邮箱")
    @Email
    private String email;

    /** 职业 */
    @Column(name = "profession")
    @ApiModelProperty(value = "职业")
    private String profession;

    /** 个人简介 */
    @Column(name = "intro")
    @ApiModelProperty(value = "个人简介")
    private String intro;

    /** 博主头像 */
    @Column(name = "picture")
    @ApiModelProperty(value = "博主头像")
    private String picture;

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