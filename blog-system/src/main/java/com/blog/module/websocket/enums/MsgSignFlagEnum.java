package com.blog.module.websocket.enums;

import lombok.Getter;

/**
 * 功能描述：消息签收的枚举类
 *
 * @author RenShiWei
 * Date: Date: 2020/5/26 20:57
 **/
public enum MsgSignFlagEnum {
    /** 消息是否签收 */
    unsign(0,"未签收"),
    signed(1,"已签收");

    @Getter
    public final int type;
    @Getter
    public final String value;

    private MsgSignFlagEnum(int type,String value) {
        this.type = type;
        this.value = value;
    }

}
