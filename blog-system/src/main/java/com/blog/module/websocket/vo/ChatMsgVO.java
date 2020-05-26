package com.blog.module.websocket.vo;

import com.blog.module.websocket.enums.MsgSignFlagEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 功能描述：消息实体的扩展类
 *
 * @author RenShiWei
 * Date: 2020/3/13 12:35
 **/
@EqualsAndHashCode(callSuper = true)
@Data
public class ChatMsgVO extends ChatVO {

    /** 动作类型 */
    private Integer action;

    /** 消息签收状态 */
    private MsgSignFlagEnum signed;

}

