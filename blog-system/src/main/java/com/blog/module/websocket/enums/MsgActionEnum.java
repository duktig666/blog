package com.blog.module.websocket.enums;

/**
 * 功能描述：客户端发送消息的动作类型
 *
 * @author RenShiWei
 * Date: 2020/5/26 20:57
 **/
public enum MsgActionEnum {
    /** 第一次(或重连)初始化连接 */
    CONNECT(1,"第一次(或重连)初始化连接"),
    /** 聊天消息 */
    CHAT(2,"聊天消息"),

    /** 客户端保持心跳 */
    KEEPALIVE(3,"客户端保持心跳");

    public final Integer type;
    public final String content;

    private MsgActionEnum(Integer type,String content) {
        this.type = type;
        this.content = content;
    }
}