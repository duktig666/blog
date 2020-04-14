package com.blog.module.business.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.blog.exception.BaseException;
import com.blog.module.business.domain.Visitor;
import com.blog.module.business.mapper.VisitorMapper;
import com.blog.module.business.service.VisitorService;
import com.blog.page.dto.PageResultDTO;
import com.blog.page.vo.PageVO;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * Description：
 *
 * @author jiaoqianjin
 * Date: 2020/4/11 11:13
 **/
@Service
public class VisitorServiceImpl implements VisitorService {
    @Autowired
    private VisitorMapper visitorMapper;

    /**
     * 功能描述：增加一位访客的信息
     *
     * @param visitor
     * @author jiaoqianjin
     * Date: 2020/4/13 20:42
     */
    @Override
    public void saveVisitor(Visitor visitor) {
        int res = this.visitorMapper.insertSelective(visitor);
        if (res == 0) {
            throw new BaseException("新增访客信息失败！");
        }
    }

    /**
     * 功能描述：根据访客id ,删除对应的访客信息
     *
     * @param visitorId
     * @author jiaoqianjin
     * Date: 2020/4/13 20:43
     */
    @Override
    public void deleteVisitor(Long visitorId) {
        int res = this.visitorMapper.deleteByPrimaryKey(visitorId);
        if (res == 0) {
            throw new BaseException("删除访客信息失败！");
        }
    }

    /**
     * 功能描述：批量删除访客信息
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
        int res = this.visitorMapper.deleteByIdList(visitorIds);
        if (res == 0) {
            throw new BaseException("删除访客信息失败！");
        }
    }

    /**
     * 功能描述：根据传入的访客信息,进行更新操作
     *
     * @param visitor
     * @author jiaoqianjin
     * Date: 2020/4/13 20:44
     */
    @Override
    public void updateVisitor(Visitor visitor) {
        int res = this.visitorMapper.updateByPrimaryKeySelective(visitor);
        if (res == 0) {
            throw new BaseException("修改访客信息失败！");
        }
    }

    /**
     * 功能描述：根据访客id,查询对应的访客信息
     *
     * @param visitorId
     * @return 返回对应id的访客信息
     * @author jiaoqianjin
     * Date: 2020/4/13 20:45
     */
    @Override
    public Visitor queryVisitor(Long visitorId) {
        Visitor visitor = this.queryVisitor(visitorId);
        if (visitor == null) {
            throw new BaseException("暂无此游客信息！");
        }
        return visitor;
    }

    /**
     * 功能描述：根据传入的分页排序信息，查询对应访客
     *
     * @param pageVo
     * @return 返回符合条件的访客集合
     * @author jiaoqianjin
     * Date: 2020/4/13 20:46
     */
    @Override
    public PageResultDTO<Visitor> queryVisitorAll( PageVO pageVo) {
        // 分页 排序 查询条件
        PageHelper.startPage(pageVo.getCurrentPage(), pageVo.getRows(), pageVo.getSort());
        // 查询所有的访客信息
        List<Visitor> visitors = this.visitorMapper.selectAll();
        if (visitors == null) {
            throw new BaseException("暂无此游客信息！");
        }
        //分页处理
        PageInfo<Visitor> pageInfo = new PageInfo<>(visitors);
        //返回封装的分页结果集
        return new PageResultDTO<>(pageInfo.getTotal(),pageInfo.getPageSize(),pageInfo.getList());
    }
}
