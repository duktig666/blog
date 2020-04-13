package com.blog.module.business.service;

import com.blog.module.business.domain.Visitor;
import com.blog.page.dto.PageResultDto;
import com.blog.page.vo.PageVo;

import java.util.List;

/**
 * Description：
 *
 * @author jiaoqianjin
 * Date: 2020/4/11 11:13
 **/
public interface VisitorService {
    /**
     * 功能描述：增加一位访客的信息
     * 
     * @param visitor
     * @author jiaoqianjin
     * Date: 2020/4/13 20:42
     */
    void saveVisitor (Visitor visitor);
    
    /**
     * 功能描述：根据访客id ,删除对应的访客信息
     * 
     * @param visitorId
     * @author jiaoqianjin
     * Date: 2020/4/13 20:43
     */
    void deleteVisitor (Long visitorId);
    
    /**
     * 功能描述：批量删除访客信息
     * 
     * @param visitorIds
     * @author jiaoqianjin
     * Date: 2020/4/13 20:43
     */
    void deleteVisitors (List<Long> visitorIds);
    
    /**
     * 功能描述：根据传入的访客信息,进行更新操作
     * 
     * @param visitor
     * @author jiaoqianjin
     * Date: 2020/4/13 20:44
     */
    void updateVisitor (Visitor visitor);

    /**
     * 功能描述：根据访客id,查询对应的访客信息
     *
     * @param visitorId
     * @return 返回对应id的访客信息
     * @author jiaoqianjin
     * Date: 2020/4/13 20:45
     */
    Visitor queryVisitor (Long visitorId);

    /**
     * 功能描述：根据传入的分页排序信息，查询对应访客
     *
     * @param pageVo
     * @return 返回符合条件的访客集合
     * @author jiaoqianjin
     * Date: 2020/4/13 20:46
     */
    PageResultDto<Visitor> queryVisitorAll (PageVo pageVo);
}
