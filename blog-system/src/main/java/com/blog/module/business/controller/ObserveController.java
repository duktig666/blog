package com.blog.module.business.controller;

import com.blog.module.business.service.ObserveService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 功能描述：
 *
 * @author RenShiWei
 * Date: 2020/4/11 11:12
 **/
@RestController
@RequestMapping("/api/observe")
@Api(tags = "评论管理模块")
public class ObserveController {

    @Autowired
    private ObserveService observeService;



}

