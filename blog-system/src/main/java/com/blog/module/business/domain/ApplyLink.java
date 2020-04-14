package com.blog.module.business.domain;

import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
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
@ApiModel
public class ApplyLink implements Serializable {

    /** 申请链接id */
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @ApiModelProperty(value = "申请链接id")
    @NotNull(groups = UpdateGroup.class,message = "修改申请链接信息，id不可以空")
    private Long id;

    /** 申请网站标题 */
    @Column(name="title")
    @ApiModelProperty(value = "申请网站标题")
//    @Size(min = 20, max = 30, message = "字符串长度要求20到30之间。")
    @NotBlank(message = "网站标题不能为空")
    private String title;

    /** 申请网站地址 */
    @Column(name="website")
    @ApiModelProperty(value = "申请网站地址")
    @NotBlank(message = "网站地址不能为空")
    private String website;

    /** 申请理由 */
    @Column(name="apply_reason")
    @ApiModelProperty(value = "申请理由")
    private String applyReason;

    /** 申请人邮箱 */
    @Column(name="email")
    @ApiModelProperty(value = "申请人邮箱")
    @Email
    private String email;

    /** 申请状态（0为申请中，1为申请成功，2为申请失败） */
    @Column(name="state")
    @ApiModelProperty(value = "申请状态（0为申请中，1为申请成功，2为申请失败）")
    @NotNull(message = "申请状态不能为空")
    private Integer state;

    /** 创建时间 */
    @Column(name="create_date")
    @ApiModelProperty(value = "创建时间")
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Timestamp createDate;

    /** 修改时间 */
    @Column(name="update_date")
    @ApiModelProperty(value = "修改时间")
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Timestamp updateDate;

    public interface UpdateGroup {
    }
}