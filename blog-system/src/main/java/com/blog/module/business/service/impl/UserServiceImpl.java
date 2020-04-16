package com.blog.module.business.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.blog.exception.BaseException;
import com.blog.module.business.domain.ApplyLink;
import com.blog.module.business.domain.LeaveWord;
import com.blog.module.business.domain.User;
import com.blog.module.business.mapper.UserMapper;
import com.blog.module.business.service.UserService;
import com.blog.page.dto.PageResultDTO;
import com.blog.page.vo.PageVO;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;


/**
 * Description：
 *
 * @author jiaoqianjin
 * Date: 2020/4/11 11:13
 **/
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    /**
     * 功能描述：增加一位用户的信息
     *
     * @param user
     * @author jiaoqianjin
     * Date: 2020/4/13 20:42
     */
    @Override
    public void saveVisitor(User user) {
        int res = this.userMapper.insertSelective(user);
        if (res == 0) {
            throw new BaseException("新增用户信息失败！");
        }
    }

    /**
     * 功能描述：根据用户id ,删除对应的用户信息
     *
     * @param visitorId
     * @author jiaoqianjin
     * Date: 2020/4/13 20:43
     */
    @Override
    public void deleteVisitor(Long visitorId) {
        int res = this.userMapper.deleteByPrimaryKey(visitorId);
        if (res == 0) {
            throw new BaseException("删除用户信息失败！");
        }
    }

    /**
     * 功能描述：批量删除用户信息
     *
     * @param visitorIds
     * @author jiaoqianjin
     * Date: 2020/4/13 20:43
     */
    @Override
    public void deleteVisitors(List<Long> visitorIds) {
        if (CollectionUtil.isEmpty(visitorIds)) {
            throw new BaseException(HttpStatus.NOT_FOUND, "传入所需要删除的ID集合不能为空");
        }
        int res = this.userMapper.deleteByIdList(visitorIds);
        if (res == 0) {
            throw new BaseException("删除用户信息失败！");
        }
    }

    /**
     * 功能描述：根据传入的用户信息,进行更新操作
     *
     * @param user
     * @author jiaoqianjin
     * Date: 2020/4/13 20:44
     */
    @Override
    public void updateVisitor(User user) {
        int res = this.userMapper.updateByPrimaryKeySelective(user);
        if (res == 0) {
            throw new BaseException("修改用户信息失败！");
        }
    }

    /**
     * 功能描述：根据用户id,查询对应的用户信息
     *
     * @param visitorId
     * @return 返回对应id的用户信息
     * @author jiaoqianjin
     * Date: 2020/4/13 20:45
     */
    @Override
    public User queryVisitor(Long visitorId) {
        User user = this.userMapper.selectByPrimaryKey(visitorId);
        if (user == null) {
            throw new BaseException("暂无此用户信息！");
        }
        return user;
    }

    /**
     * 功能描述：根据传入的分页排序信息，查询所用户
     *
     * @param pageVo
     * @return 返回符合条件的用户集合
     * @author jiaoqianjin
     * Date: 2020/4/13 20:46
     */
    @Override
    public PageResultDTO<User> queryVisitorAll(int identity,PageVO pageVo) {
        // 分页 排序 查询条件
        PageHelper.startPage(pageVo.getCurrentPage(), pageVo.getRows(), pageVo.getSort());
        // 查询所有的友情链接信息
        Example example = new Example(User.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("identity", identity);

        // 查询所有的游客信息
        List<User> users = this.userMapper.selectByExample(example);
        if (users == null) {
            throw new BaseException("暂无此用户信息！");
        }
        //分页处理
        PageInfo<User> pageInfo = new PageInfo<>(users);
        //返回封装的分页结果集
        return new PageResultDTO<>(pageInfo.getTotal(),pageInfo.getPageSize(),pageInfo.getList());
    }

}
