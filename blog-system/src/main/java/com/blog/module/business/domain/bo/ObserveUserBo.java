package com.blog.module.business.domain.bo;

import com.blog.module.business.domain.Observe;
import com.blog.module.business.domain.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 功能描述：评论信息，携带用户信息
 *
 * @author RenShiWei
 * Date: 2020/5/9 18:39
 **/
@Data
public class ObserveUserBo {

    /** 评论信息 */
    private Observe observe;

    /** 评论的用户信息 */
    private User user;
}

