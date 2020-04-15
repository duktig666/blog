package com.blog.module.business.domain;

import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
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
@ApiModel("游客")
public class Visitor implements Serializable {

    /** 游客id */
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @ApiModelProperty(value = "游客id")
    @NotNull(groups = UpdateGroup.class,message = "修改游客信息，id不可以空")
    private Long id;

    /** 游客昵称 */
    @Column(name="nickname")
    @ApiModelProperty(value = "游客昵称")
    @NotBlank(message = "游客昵称不能为空")
    private String nickname;

    /** 邮箱 */
    @Column(name="email")
    @ApiModelProperty(value = "邮箱")
    @Email
    private String email;

    /** 游客头像 */
    @Column(name="picture")
    @ApiModelProperty(value = "游客头像")
    private String picture;

    /** 身份标识，0代表游客，1代表博主 */
    @Column(name="identity")
    @ApiModelProperty(value = "身份标识，0代表游客，1代表博主")
    private Integer identity;

    /** 是否删除 */
    @Column(name="is_delete")
    @ApiModelProperty(value = "是否删除")
    private Boolean delete;

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

    public interface UpdateGroup {
    }
}