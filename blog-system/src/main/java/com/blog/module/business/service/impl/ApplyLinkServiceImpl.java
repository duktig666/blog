package com.blog.module.business.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.blog.exception.BaseException;
import com.blog.module.business.domain.ApplyLink;
import com.blog.module.business.domain.LeaveWord;
import com.blog.module.business.mapper.ApplyLinkMapper;
import com.blog.module.business.service.ApplyLinkService;
import com.blog.page.dto.PageResultDto;
import com.blog.page.vo.PageVo;
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
public class ApplyLinkServiceImpl implements ApplyLinkService {
    @Autowired
    private ApplyLinkMapper applyLinkMapper;
    /**
     * 功能描述：增加一条友情链接信息
     *
     * @param applyLink
     * @author jiaoqianjin
     * Date: 2020/4/14 8:17
     */
    @Override
    public void saveApplyLink(ApplyLink applyLink) {
        int res = this.applyLinkMapper.insertSelective(applyLink);
        if (res == 0) {
            throw new BaseException("新增友情链接信息失败！");
        }
    }

    /**
     * 功能描述：根据友情链接id,删除对应友情链接信息
     *
     * @param applyLinkId
     * @author jiaoqianjin
     * Date: 2020/4/14 8:32
     */
    @Override
    public void deleteApplyLink(Long applyLinkId) {
        int res = this.applyLinkMapper.deleteByPrimaryKey(applyLinkId);
        if (res == 0) {
            throw new BaseException("删除友情链接信息失败！");
        }
    }

    /**
     * 功能描述：批量删除友情链接
     *
     * @param applyLinkIds
     * @author jiaoqianjin
     * Date: 2020/4/14 8:34
     */
    @Override
    public void deleteApplyLinks(List<Long> applyLinkIds) {
        if (CollectionUtil.isEmpty(applyLinkIds)) {
            throw new BaseException(HttpStatus.NOT_FOUND, "传入所需要删除的ID集合不能为空");
        }
        int res = this.applyLinkMapper.deleteByIdList(applyLinkIds);
        if (res == 0) {
            throw new BaseException("批量删除友情链接信息失败！");
        }
    }

    /**
     * 功能描述：博主处理友情链接，更新状态
     *
     * @param applyLink
     * @author jiaoqianjin
     * Date: 2020/4/14 8:35
     */
    @Override
    public void updateApplyLink (ApplyLink applyLink) {
        int res = this.applyLinkMapper.updateByPrimaryKeySelective(applyLink);
        if (res == 0) {
            throw new BaseException("修改访客信息失败！");
        }
    }

    /**
     * 功能描述：根据友情链接id,查询对应的友情链接信息
     *
     * @param applyLinkId
     * @return 对应的友情链接信息
     * @author jiaoqianjin
     * Date: 2020/4/14 8:37
     */
    @Override
    public ApplyLink queryApplyLinkById (Long applyLinkId) {
        ApplyLink applyLink = this.applyLinkMapper.selectByPrimaryKey(applyLinkId);
        if (applyLink == null) {
            throw new BaseException(HttpStatus.NOT_FOUND,"暂无此友情链接信息！");
        }
        return applyLink;
    }

    /**
     * 功能描述：根据传入的分页排序信息，查询对应友情链接信息
     *
     * @param pageVo
     * @return 返回符合条件的友情链接集合
     * @author jiaoqianjin
     * Date: 2020/4/14 8:39
     */
    @Override
    public PageResultDto<ApplyLink> queryApplyLinkAll(PageVo pageVo) {
        // 分页 排序 查询条件
        if (pageVo != null) {
            PageHelper.startPage(pageVo.getCurrentPage(), pageVo.getRows(), pageVo.getSort());
        }
        // 查询所有的友情链接信息
        List<ApplyLink> applyLinks = this.applyLinkMapper.selectAll();
        if (applyLinks == null) {
            throw new BaseException(HttpStatus.NOT_FOUND,"查询友情链接信息失败！");
        }
        //分页处理
        PageInfo<ApplyLink> pageInfo = new PageInfo<>(applyLinks);
        //返回封装的分页结果集
        return new PageResultDto<>(pageInfo.getTotal(), pageInfo.getPageSize(), pageInfo.getList());
    }

    /**
     * 功能描述：根据状态筛选出符合合适的友情链接信息，并分页排序
     *
     * @param state  友情链接处理状态
     * @param pageVo 分页排序条件
     * @return 返回符合筛选添加的分页集合
     * @author jiaoqianjin
     * Date: 2020/4/14 9:55
     */
    @Override
    public PageResultDto<ApplyLink> queryApplyLinksByState(Integer state, PageVo pageVo) {
        // 分页 排序 查询条件
        if (pageVo != null) {
            PageHelper.startPage(pageVo.getCurrentPage(), pageVo.getRows(), pageVo.getSort());
        }
        // 查询所有的友情链接信息
        Example example = new Example(ApplyLink.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("state", state);

        List<ApplyLink> applyLinks = this.applyLinkMapper.selectByExample(example);
        if (applyLinks == null) {
            throw new BaseException(HttpStatus.NOT_FOUND,"查询友情链接信息失败！");
        }
        //分页处理
        PageInfo<ApplyLink> pageInfo = new PageInfo<>(applyLinks);
        //返回封装的分页结果集
        return new PageResultDto<>(pageInfo.getTotal(), pageInfo.getPageSize(), pageInfo.getList());
    }
}
