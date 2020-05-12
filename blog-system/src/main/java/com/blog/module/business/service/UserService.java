package com.blog.module.business.service;

import com.blog.module.business.domain.User;
import com.blog.page.dto.PageResultDTO;
import com.blog.page.vo.PageVO;

import java.util.List;

/**
 * Description：
 *
 * @author jiaoqianjin
 * Date: 2020/4/11 11:13
 **/
public interface UserService {
    /**
     * 功能描述：增加一位用户的信息
     *
     * @param user 用户信息
     * @author jiaoqianjin
     * Date: 2020/4/13 20:42
     */
    void saveVisitor ( User user );

    /**
     * 功能描述：根据用户id ,删除对应的用户信息
     *
     * @param visitorId
     * @author jiaoqianjin
     * Date: 2020/4/13 20:43
     */
    void deleteVisitor ( Long visitorId );

    /**
     * 功能描述：批量删除用户信息
     *
     * @param visitorIds
     * @author jiaoqianjin
     * Date: 2020/4/13 20:43
     */
    void deleteVisitors ( List<Long> visitorIds );

    /**
     * 功能描述：根据传入的用户信息,进行更新操作
     *
     * @param user
     * @author jiaoqianjin
     * Date: 2020/4/13 20:44
     */
    void updateVisitor ( User user );

    /**
     * 功能描述：根据用户id,查询对应的用户信息
     *
     * @param visitorId
     * @return 返回对应id的用户信息
     * @author jiaoqianjin
     * Date: 2020/4/13 20:45
     */
    User queryVisitor ( Long visitorId );

    /**
     * 功能描述：根据传入的分页排序信息，查询对应游客
     *
     * @param pageVo
     * @return 返回符合条件的用户集合
     * @author jiaoqianjin
     * Date: 2020/4/13 20:46
     */
    PageResultDTO<User> queryVisitorAll ( int identity, PageVO pageVo );

    /**
     * 功能描述：用户登录的账号、密码验证
     *
     * @param account  账号
     * @param password 密码
     * @return 是否登录成功
     * @author RenShiWei
     * Date: 2020/5/12 8:55
     */
    boolean login ( String account, String password );
}
