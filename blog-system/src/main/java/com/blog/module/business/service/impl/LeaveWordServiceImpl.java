package com.blog.module.business.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.blog.exception.BaseException;
import com.blog.module.business.domain.LeaveWord;
import com.blog.module.business.domain.User;
import com.blog.module.business.domain.bo.LeaveWordBO;
import com.blog.module.business.mapper.LeaveWordMapper;
import com.blog.module.business.mapper.UserMapper;
import com.blog.module.business.service.LeaveWordService;
import com.blog.page.dto.PageResultDTO;
import com.blog.page.vo.PageVO;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Description：
 *
 * @author jiaoqianjin
 * Date: 2020/4/11 11:13
 **/
@Service
public class LeaveWordServiceImpl implements LeaveWordService {
    @Autowired
    private LeaveWordMapper leaveWordMapper;

    @Autowired
    private UserMapper userMapper;
    /**
     * 功能描述：增加一条留言信息
     * 问题：如何获得留言游客的id
     * @param leaveWord
     * @author jiaoqianjin
     * Date: 2020/4/14 8:17
     */
    @Override
    @Transactional
    public void saveLeaveWord(User user, LeaveWord leaveWord) {
        // 新增留言信息的时候，需要先将 该游客的信息添加 游客表，然后将该游客 id 返回
        int res = this.userMapper.insertSelective(user);
        if(res == 0) {
            throw new BaseException("增加留言信息失败，未能正确的添加游客信息");
        }
        // 添加游客信息成功后，将 游客id 添加到游客信息中
        leaveWord.setVisitorId(user.getId());
        // 增加一条留言信息
        int res1 = this.leaveWordMapper.insertSelective(leaveWord);
        if(res1 == 0) {
            throw new BaseException("增加留言信息失败");
        }

    }

    /**
     * 功能描述：根据留言id,删除对应留言信息
     *
     * @param leaveWordId
     * @author jiaoqianjin
     * Date: 2020/4/14 8:32
     */
    @Override
    public void deleteLeaveWord(Long leaveWordId) {
        int res = this.leaveWordMapper.deleteByPrimaryKey(leaveWordId);
        if(res == 0) {
            throw new BaseException("删除留言信息失败");
        }
    }

    /**
     * 功能描述：批量删除留言
     *
     * @param LeaveWordIds
     * @author jiaoqianjin
     * Date: 2020/4/14 8:34
     */
    @Override
    public void deleteLeaveWords(List<Long> LeaveWordIds) {
        if(CollectionUtil.isEmpty(LeaveWordIds)) {
            throw new BaseException(HttpStatus.NOT_FOUND, "传入所需要删除的ID集合不能为空");
        }
        int res = this.leaveWordMapper.deleteByIdList(LeaveWordIds);
        if (res == 0) {
            throw new BaseException("删除留言信息失败！");
        }
    }

    /**
     * 功能描述：博主处理留言，更新状态
     *
     * @param leaveWord
     * @author jiaoqianjin
     * Date: 2020/4/14 8:35
     */
    @Override
    public void updateLeaveWord(LeaveWord leaveWord) {
        int res = this.leaveWordMapper.updateByPrimaryKeySelective(leaveWord);
        if (res == 0) {
            throw new BaseException("修改访客信息失败！");
        }
    }

    /**
     * 功能描述：根据留言id,查询对应的留言信息
     *
     * @param LeaveWordId
     * @return 对应的留言信息
     * @author jiaoqianjin
     * Date: 2020/4/14 8:37
     */
//    @Override
//    public LeaveWord queryLeaveWordById(Long LeaveWordId) {
//        LeaveWord leaveWord = this.leaveWordMapper.selectByPrimaryKey(LeaveWordId);
//        if (leaveWord == null) {
//            throw new BaseException(HttpStatus.NOT_FOUND,"暂无此留言信息！");
//        }
//        return leaveWord;
//    }

    /**
     * 功能描述：根据传入的分页排序信息，查询对应留言信息
     *
     * @param pageVO
     * @return 返回符合条件的留言分页集合
     * @author jiaoqianjin
     * Date: 2020/4/14 8:39
     */
    @Override
    public PageResultDTO<LeaveWordBO> queryLeaveWordAll(PageVO pageVO) {
        // 分页 排序 查询条件
        if (pageVO != null) {
            PageHelper.startPage(pageVO.getCurrentPage(), pageVO.getRows(), pageVO.getSort());
        }
        List<LeaveWordBO> leaveWordBOS = new ArrayList<>();
        // 查询所有的留言信息
        List<LeaveWord> leaveWords = this.leaveWordMapper.selectAll();
        // 根据留言信息中游客id,查找游客信息
        leaveWords.forEach(leaveWord -> {
                    User user = this.userMapper.selectByPrimaryKey(leaveWord.getVisitorId());
                    LeaveWordBO leaveWordBO = new LeaveWordBO(user,leaveWord);
                    leaveWordBOS.add(leaveWordBO);
        }

        );
        if (leaveWordBOS == null) {
            throw new BaseException(HttpStatus.NOT_FOUND,"查询留言信息失败！");
        }
        //分页处理
        PageInfo<LeaveWord> pageInfo = new PageInfo<>(leaveWords);
        //返回封装的分页结果集
        return new PageResultDTO<>(pageInfo.getTotal(), pageInfo.getPageSize(), leaveWordBOS);
    }

    /**
     * 功能描述：根据筛选出未回复的留言信息，并分页排序
     *
     * @param replyContent 留言处理状态
     * @param pageVO       分页排序条件
     * @return 返回符合筛选添加的分页集合
     * @author jiaoqianjin
     * Date: 2020/4/14 9:55
     */
//    @Override
//    public PageResultDTO<LeaveWordBO> queryLeaveWordsByState(String replyContent, PageVO pageVO) {
//        // 分页 排序 查询条件
//        if (pageVO != null) {
//            PageHelper.startPage(pageVO.getCurrentPage(), pageVO.getRows(), pageVO.getSort());
//        }
//        // 查询所有的友情链接信息
//        Example example = new Example(ApplyLink.class);
//        Example.Criteria criteria = example.createCriteria();
//        criteria.andEqualTo("replyContent", replyContent);
//
//        List<LeaveWord> leaveWords = this.leaveWordMapper.selectByExample(example);
//        if (leaveWords == null) {
//            throw new BaseException(HttpStatus.NOT_FOUND,"查询留言信息失败！");
//        }
//        //分页处理
//        PageInfo<LeaveWord> pageInfo = new PageInfo<>(leaveWords);
//        //返回封装的分页结果集
//        return new PageResultDTO<>(pageInfo.getTotal(), pageInfo.getPageSize(), pageInfo.getList());
//    }
}
