package com.blog.module.business.domain;

import lombok.Data;

import javax.persistence.*;
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
public class LeaveWord implements Serializable {

    /** 留言id */
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    /** 留言者id */
    @Column(name="visitor_id")
    private Long visitorId;

    /** 留言内容 */
    @Column(name="leave_content")
    private String leaveContent;

    /** 创建时间 */
    @Column(name="creat_date")
    private Timestamp createDate;

    /** 博主回复内容 */
    @Column(name="reply_content")
    private String replyContent;

    /** 修改时间 */
    @Column(name="update_date")
    private Timestamp updateDate;


}