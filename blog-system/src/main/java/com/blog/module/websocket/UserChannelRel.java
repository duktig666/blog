package com.blog.module.websocket;

import io.netty.channel.Channel;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ConcurrentHashMap;

/**
 * 功能描述：用户id和channel的关联关系的处理类
 *
 * @author RenShiWei
 * Date: 2020/5/26 20:57
 **/
@Slf4j
public class UserChannelRel {

    /** 用户id为键，channel为值 */
    private static ConcurrentHashMap<Integer, Channel> manager = new ConcurrentHashMap<>();

    /** 添加客户端与channel绑定 */
    public static void put(Integer senderId,Channel channel) {
        manager.put(senderId,channel);
    }

    /** 根据用户id查询 */
    public static Channel get(Integer senderId) {
        return manager.get(senderId);
    }

    /** 根据用户id，判断是否存在此客户端（即客户端是否在线） */
    public static boolean isContainsKey(Integer userId){
        return manager.containsKey(userId);
    }

   /** 输出 */
    public static void output() {
        manager.forEach(( key, value ) -> log.info("UserId:" + key + ",ChannelId:" +
                value.id().asLongText()));
    }

}

