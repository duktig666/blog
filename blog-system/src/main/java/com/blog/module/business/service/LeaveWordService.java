package com.blog.module.business.service;


import com.blog.module.business.domain.LeaveWord;
import com.blog.module.business.domain.Visitor;
import com.blog.page.dto.PageResultDTO;
import com.blog.page.vo.PageVO;

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
     * @param visitor
     * @author jiaoqianjin
     * Date: 2020/4/14 8:17
     */
    void saveLeaveWord (Visitor visitor,LeaveWord leaveWord);
    
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
     * @param leaveWordIds
     * @author jiaoqianjin
     * Date: 2020/4/14 8:34
     */
    void deleteLeaveWords (List<Long> leaveWordIds);
    
    /**
     * 功能描述：博主处理留言，更新状态
     *
     * @param leaveWord
     * @author jiaoqianjin
     * Date: 2020/4/14 8:35
     */
    void updateLeaveWord (LeaveWord leaveWord);
    
    /**
     * 功能描述：根据留言id,查询对应的留言信息
     *
     * @param leaveWordId
     * @return 对应的留言信息
     * @author jiaoqianjin
     * Date: 2020/4/14 8:37
     */
    LeaveWord queryLeaveWordById (Long leaveWordId);
    
    /**
     * 功能描述：根据传入的分页排序信息，查询对应留言信息
     *
     * @param pageVO
     * @return 返回符合条件的留言分页集合
     * @author jiaoqianjin
     * Date: 2020/4/14 8:39
     */
    PageResultDTO<LeaveWord> queryLeaveWordAll (PageVO pageVO);
    
    /**
     * 功能描述：根据筛选出未回复的留言信息，并分页排序
     *
     * @param replyContent 留言处理状态
     * @param pageVO 分页排序条件
     * @return 返回符合筛选添加的分页集合
     * @author jiaoqianjin
     * Date: 2020/4/14 9:55
     */
    PageResultDTO queryLeaveWordsByState (String replyContent, PageVO pageVO);
        

}
