package com.blog.module.business.controller;

import com.blog.module.business.domain.Blogger;
import com.blog.module.business.service.BlogService;
import com.blog.module.business.service.BloggerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * 功能描述：博主信息增删改查
 *
 * @author RenShiWei
 * Date: 2020/4/11 11:07
 **/
@RestController
@RequestMapping("/api/blogger")
@Api(tags = "博主信息管理模块")
public class BloggerController {
    @Autowired
    private BloggerService bloggerService;


    @ApiOperation(value = "新增博主信息",notes="新增一位博主信息;\n author：JQJ")
    @ApiImplicitParam(name = "blogger", value = "新增的博主信息", required = true)
    @PostMapping
    public ResponseEntity<Void> saveBlogger(@Validated Blogger blogger){
        this.bloggerService.saveBlogger(blogger);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @ApiOperation(value = "根据博主id查询博主信息",notes="查询博主信息;\n author：JQJ")
    @ApiImplicitParam(name = "bloggerId", value = "要查询的博主id", required = true)
    @GetMapping
    public ResponseEntity<Blogger> queryBloggerById(@Valid  Long bloggerId){
        Blogger blogger = this.bloggerService.queryBlogger(bloggerId);
        if (blogger == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(blogger);
    }

    @ApiOperation(value = "修改博主信息",notes="根据传入新的博主信息进行修改; \n author：JQJ")
    @ApiImplicitParam(name = "blogger", value = "前端修改后的博主信息", required = true)
    @PutMapping
    public ResponseEntity<Void> updateBlogger(@Validated @RequestBody Blogger blogger) {
        this.bloggerService.updateBlogger(blogger);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @ApiOperation(value = "删除博主信息",notes="根据博主id 删除对应博主信息; \n author：JQJ")
    @ApiImplicitParam(name = "bloggerId", value = "要删除的博主id", required = true)
    @DeleteMapping
    public ResponseEntity<Void> deleteBloggerByIdLong (@Valid Long bloggerId){
        this.bloggerService.deleteBlogger(bloggerId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}

