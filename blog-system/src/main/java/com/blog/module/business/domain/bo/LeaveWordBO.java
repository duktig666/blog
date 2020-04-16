package com.blog.module.business.domain.bo;

import com.blog.module.business.domain.*;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Description：封装 留言 游客
 *
 * @author jiaoqianjin
 * Date: 2020/4/15 10:01
 **/
@Data
@AllArgsConstructor
public class LeaveWordBO {

    /** 游客信息 */
    private User user;
    /** 留言信息 */
    private LeaveWord leaveWord;


}
