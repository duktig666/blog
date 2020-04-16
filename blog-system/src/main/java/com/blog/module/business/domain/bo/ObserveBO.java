package com.blog.module.business.domain.bo;

import com.blog.module.business.domain.Observe;
import com.blog.module.business.domain.User;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Transient;
import java.sql.Timestamp;
import java.util.List;


/**
 * 功能描述：封装一条评论，可能用到的信息
 *
 * @author RenShiWei
 * Date: 2020/4/15 10:50
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ObserveBO extends Observe{

    /**
     * 评论者是游客，返回游客信息
     */
    private User user;

    /**
     * 一级评论信息的二级回复
     */
    @Transient
    private List<ObserveBO> secondObserveList;

    /* ======================================  */


    /** 评论id */
    private Long id;

    /** 所属博客id */
    private Long blogId;

    /** 评论者id */
    private Long observerId;

    /** 评论者类型 */
    private Integer observerType;

    /** 评论内容 */
    private String observeContent;

    /** 评论上一级的id */
    private Long lastId;

    /** 是否删除 */
    private Boolean delete;

    /** 创建时间（评论时间） */
    private Timestamp createDate;

    /** 修改删除 */
    private Timestamp updateDate;



}