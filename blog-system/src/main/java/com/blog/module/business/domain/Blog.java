package com.blog.module.business.domain;

import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
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
@ApiModel("博客")
public class Blog  implements Serializable {

    /** 博客id */
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @ApiModelProperty(value = "博客id")
    @NotNull(groups = UpdateGroup.class,message = "修改博客信息，id不可以空")
    private Long id;

    /** 所属博客类型id */
    @Column(name="type_id")
    @ApiModelProperty(value = "所属博客类型id")
//    @NotNull(message = "所属博客类型id不能为空")
    private Long typeId;

    /** 博客标题 */
    @Column(name="title")
    @ApiModelProperty(value = "博客标题")
//    @NotBlank(message = "博客标题不能为空")
    private String title;

    /** 博客摘要 */
    @Column(name="summary")
    @ApiModelProperty(value = "博客摘要")
    //    @NotBlank(message = "博客摘要不能为空")
    private String summary;

    /** 博客正文 */
    @Column(name="content")
    @ApiModelProperty(value = "博客正文")
//    @NotBlank(message = "博客正文不能为空")
    private String content;

    /** 浏览量 */
    @Column(name="visit_number")
    @ApiModelProperty(value = "浏览量")
    private Integer visitNumber;

    /** 点赞量 */
    @Column(name="like_number")
    @ApiModelProperty(value = "点赞量")
    private Integer likeNumber;

    /** 评论量 */
    @Column(name="observe_number")
    @ApiModelProperty(value = "评论量")
    private Integer observeNumber;

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

    /** 是否删除 */
    @Column(name="is_delete")
    @ApiModelProperty(value = "是否删除")
    private Boolean delete;

    public interface UpdateGroup {
    }
}