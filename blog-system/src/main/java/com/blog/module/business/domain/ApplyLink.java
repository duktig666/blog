package com.blog.module.business.domain;

import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel
public class ApplyLink implements Serializable {

    /** 申请链接id */
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @ApiModelProperty(value = "申请链接id")
    private Long id;

    /** 申请网站标题 */
    @Column(name="title")
    @ApiModelProperty(value = "申请网站标题")
    private String title;

    /** 申请网站地址 */
    @Column(name="website")
    @ApiModelProperty(value = "申请网站地址")
    private String website;

    /** 申请理由 */
    @Column(name="apply_reason")
    @ApiModelProperty(value = "申请理由")
    private String applyReason;

    /** 申请人邮箱 */
    @Column(name="email")
    @ApiModelProperty(value = "申请人邮箱")
    private String email;

    /** 申请状态（0为申请中，1为申请成功，2为申请失败） */
    @Column(name="state")
    @ApiModelProperty(value = "申请状态（0为申请中，1为申请成功，2为申请失败）")
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


}