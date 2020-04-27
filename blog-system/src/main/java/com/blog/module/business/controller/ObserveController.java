package com.blog.module.business.controller;

import com.blog.module.business.domain.Observe;
import com.blog.module.business.domain.User;
import com.blog.module.business.domain.bo.ObserveNodeBO;
import com.blog.module.business.service.ObserveService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * 功能描述：评论信息的接口
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

    @ApiOperation(value = "新增评论", notes = "新增博客评论,同时注册游客信息;\nauthor：RSW")
    @PostMapping
    public ResponseEntity<Void> saveObserve (
            @ApiParam(name = "user", value = "新增评论的游客信息", required = true) @Validated User user,
            @ApiParam(name = "observe", value = "新增的评论信息", required = true) @Validated Observe observe
    ) {
        observeService.saveObserve(user, observe);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @ApiOperation(value = "删除评论", notes = "根据评论id，删除评论;\nauthor：RSW")
    @DeleteMapping("{observeId}")
    public ResponseEntity<Void> deleteObserve (
            @ApiParam(name = "observeId", value = "评论id", required = true) @Valid @PathVariable Long observeId
    ) {
        observeService.deleteObserve(observeId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @ApiOperation(value = "修改评论", notes = "需要传入评论id;\nauthor：RSW")
    @PutMapping
    public ResponseEntity<Void> updateObserve (
            @ApiParam(name = "observe", value = "修改的评论信息", required = true) @Validated(Observe.UpdateGroup.class) Observe observe
    ) {
        observeService.updateObserve(observe);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @ApiOperation(value = "查询一篇博客的所有评论信息", notes = "根据博客id，查询此博客的所有评论信息（链表类型的数据）;\nauthor：RSW")
    @GetMapping("/{blogId}")
    public ResponseEntity<List<ObserveNodeBO>> queryObserveByBlogId (
            @ApiParam(name = "blogId", value = "博客id", required = true) @Valid @PathVariable Long blogId
    ) {
        return ResponseEntity.ok(observeService.queryObserveByBlogId(blogId));
    }

}

