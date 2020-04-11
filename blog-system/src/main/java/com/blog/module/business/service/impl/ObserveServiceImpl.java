package com.blog.module.business.service.impl;

import com.blog.module.business.mapper.ObserveMapper;
import com.blog.module.business.service.ObserveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Description：
 *
 * @author jiaoqianjin
 * Date: 2020/4/11 11:13
 **/
@Service
public class ObserveServiceImpl implements ObserveService {
    @Autowired
    private ObserveMapper observeMapper;
}
