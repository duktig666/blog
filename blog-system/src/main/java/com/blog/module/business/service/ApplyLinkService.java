package com.blog.module.business.service;

import com.blog.module.business.domain.ApplyLink;
import com.blog.module.business.domain.LeaveWord;
import com.blog.page.dto.PageResultDto;
import com.blog.page.vo.PageVo;

import java.util.List;

public interface ApplyLinkService {
    /**
     * 功能描述：增加一条友情链接信息
     *
     * @param applyLink
     * @author jiaoqianjin
     * Date: 2020/4/14 8:17
     */
    void saveApplyLink (ApplyLink applyLink);

    /**
     * 功能描述：根据友情链接id,删除对应友情链接信息
     *
     * @param applyLinkId
     * @author jiaoqianjin
     * Date: 2020/4/14 8:32
     */
    void deleteApplyLink (Long applyLinkId);

    /**
     * 功能描述：批量删除友情链接
     *
     * @param applyLinkIds
     * @author jiaoqianjin
     * Date: 2020/4/14 8:34
     */
    void deleteApplyLinks (List<Long> applyLinkIds);

    /**
     * 功能描述：博主处理友情链接，更新状态
     *
     * @param applyLink
     * @author jiaoqianjin
     * Date: 2020/4/14 8:35
     */
    void updateApplyLink (ApplyLink applyLink);

    /**
     * 功能描述：根据友情链接id,查询对应的友情链接信息
     *
     * @param applyLinkId
     * @return 对应的友情链接信息
     * @author jiaoqianjin
     * Date: 2020/4/14 8:37
     */
    ApplyLink queryApplyLinkById (Long applyLinkId);

    /**
     * 功能描述：根据传入的分页排序信息，查询对应友情链接信息
     *
     * @param pageVo
     * @return 返回符合条件的友情链接分页集合
     * @author jiaoqianjin
     * Date: 2020/4/14 8:39
     */
    PageResultDto<ApplyLink> queryApplyLinkAll (PageVo pageVo);

    /**
     * 功能描述：根据状态筛选出符合合适的友情链接信息，并分页排序
     *
     * @param state 友情链接处理状态
     * @param pageVo 分页排序条件
     * @return 返回符合筛选添加的分页集合
     * @author jiaoqianjin
     * Date: 2020/4/14 9:55
     */
    PageResultDto queryApplyLinksByState (Integer state, PageVo pageVo);

}
