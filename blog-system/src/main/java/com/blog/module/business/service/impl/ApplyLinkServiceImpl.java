package com.blog.module.business.service.impl;

import com.blog.module.business.mapper.ApplyLinkMapper;
import com.blog.module.business.service.ApplyLinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
