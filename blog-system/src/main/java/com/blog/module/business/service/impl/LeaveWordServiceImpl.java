package com.blog.module.business.service.impl;

import com.blog.module.business.mapper.LeaveWordMapper;
import com.blog.module.business.service.LeaveWordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
