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
import java.util.Date;

/**
 * 功能描述：留言信息表
 *
 * @author RenShiWei
 * Date: 2020/4/10 21:32
 **/
@Entity
@Data
@Table(name="leave_word")
@ApiModel("留言")
public class LeaveWord implements Serializable {

    /** 留言id */
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @ApiModelProperty(value = "留言id")
    @NotNull(groups = UpdateGroup.class,message = "修改留言信息，id不可以空")
    private Long id;

    /** 留言者id */
    @Column(name="visitor_id")
    @ApiModelProperty(value = "留言者id")
    @NotNull(message = "留言者id不能为空")
    private Long visitorId;

    /** 留言内容 */
    @Column(name="leave_content")
    @ApiModelProperty(value = "留言内容")
    @NotBlank(message = "留言内容不能为空")
    private String leaveContent;

    /** 创建时间 */
    @Column(name="create_date")
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "创建时间")
    private Timestamp createDate;

    /** 博主回复内容 */
    @Column(name="reply_content")
    @ApiModelProperty(value = "博主回复内容")
    private String replyContent;

    /** 修改时间 */
    @Column(name="update_date")
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "修改时间")
    private Timestamp updateDate;

    public interface UpdateGroup {
    }
}