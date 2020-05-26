package com.blog.module.websocket.vo;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * class description：聊天信息类
 *
 * @author rsw
 * Date: 2020/2/5
 * Time: 21:27
 **/
@Data
public class ChatVO implements Serializable {

    /** 消息id */
    private Integer questionId;
    /**聊天信息类型*/
    private String chatMessageType;
    /**聊天内容*/
    private String content;
    /**发送方ID*/
    private Integer fromUserId;
    /**接收方ID*/
    private Integer toUserId;
    /**消息时间*/
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Date dateTime;

}

