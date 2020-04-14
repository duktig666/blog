package com.blog.module.business.service;


import com.blog.module.business.domain.LeaveWord;
import com.blog.page.dto.PageResultDto;
import com.blog.page.vo.PageVo;

import java.util.List;

/**
 * Description：
 *
 * @author jiaoqianjin
 * Date: 2020/4/11 11:13
 **/
public interface LeaveWordService {
    /**
     * 功能描述：增加一条留言信息
     *
     * @param leaveWord
     * @author jiaoqianjin
     * Date: 2020/4/14 8:17
     */
    void saveLeaveWord (LeaveWord leaveWord);
    
    /**
     * 功能描述：根据留言id,删除对应留言信息
     *
     * @param leaveWordId
     * @author jiaoqianjin
     * Date: 2020/4/14 8:32
     */
    void deleteLeaveWord (Long leaveWordId);
    
    /**
     * 功能描述：批量删除留言
     *
     * @param LeaveWordIds
     * @author jiaoqianjin
     * Date: 2020/4/14 8:34
     */
    void deleteLeaveWords (List<Long> LeaveWordIds);
    
    /**
     * 功能描述：博主处理留言，更新状态
     *
     * @param LeaveWord
     * @author jiaoqianjin
     * Date: 2020/4/14 8:35
     */
    void updateLeaveWord (LeaveWord LeaveWord);
    
    /**
     * 功能描述：根据留言id,查询对应的留言信息
     *
     * @param LeaveWordId
     * @return 对应的留言信息
     * @author jiaoqianjin
     * Date: 2020/4/14 8:37
     */
    LeaveWord queryLeaveWordById (Long LeaveWordId);
    
    /**
     * 功能描述：根据传入的分页排序信息，查询对应留言信息
     *
     * @param pageVo
     * @return 返回符合条件的留言分页集合
     * @author jiaoqianjin
     * Date: 2020/4/14 8:39
     */
    PageResultDto<LeaveWord> queryLeaveWordAll (PageVo pageVo);
    
    /**
     * 功能描述：根据状态筛选出符合合适的留言信息，并分页排序
     *
     * @param state 留言处理状态
     * @param pageVo 分页排序条件
     * @return 返回符合筛选添加的分页集合
     * @author jiaoqianjin
     * Date: 2020/4/14 9:55
     */
    PageResultDto queryLeaveWordsByState (Integer state, PageVo pageVo);
        

}
