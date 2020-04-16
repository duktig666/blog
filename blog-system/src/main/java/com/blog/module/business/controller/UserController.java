package com.blog.module.business.controller;

import com.blog.module.business.domain.User;
import com.blog.module.business.service.UserService;
import com.blog.page.dto.PageResultDTO;
import com.blog.page.vo.PageVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
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
 * 功能描述：
 *
 * @author RenShiWei
 * Date: 2020/4/11 11:12
 **/
@RestController
@RequestMapping("/api/user")
@Api(tags = "用户信息管理模块")
public class UserController {
    @Autowired
    private UserService userService;

    @ApiOperation(value = "新增用户信息",notes="新增一位用户信息; \n author：JQJ")
    @PostMapping
    public ResponseEntity<Void> saveVisitor (
            @ApiParam(name = "visitor", value = "新增的用户信息", required = true)
            @Validated User user) {
        this.userService.saveVisitor(user);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @ApiOperation(value = "删除单个用户信息",notes="根据用户id删除对应用户信息; \n author：JQJ")
    @DeleteMapping("/{visitorId}")
    public ResponseEntity<Void> deleteVisitor (
            @ApiParam(name = "visitorId", value = "博客id", required = true)
            @Valid @PathVariable("visitorId") Long visitorId ){
        this.userService.deleteVisitor(visitorId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @ApiOperation(value = "批量删除用户信息",notes="根据用户id集合批量删除用户信息; \n author：JQJ")
    @ApiParam(name = "visitorIds", value = "用户id集合", required = true)
    @DeleteMapping("/ids")
    public ResponseEntity<Void> deleteVisitors (
            @ApiParam(name = "visitorIds", value = "用户id集合", required = true)
            @Valid List<Long> visitorIds ){
        this.userService.deleteVisitors(visitorIds);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
    @ApiOperation(value = "修改用户信息",notes="根据前台传入的信息进行修改对应用户信息; \n author：JQJ")
    @PutMapping
    public ResponseEntity<Void> updateVisitor (
            @ApiParam(name = "visitor", value = "修改的用户信息", required = true)
            @Validated(User.UpdateGroup.class) User user){
        this.userService.updateVisitor(user);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @ApiOperation(value = "查询一位用户信息",notes="根据用户id查询对应用户信息; \n author：JQJ")
    @GetMapping("/{visitorId}")
    public ResponseEntity<User> queryVisitorById (
            @ApiParam(name = "visitorId", value = "用户id", required = true)
            @Valid @PathVariable("visitorId") Long visitorId ){
        User user = this.userService.queryVisitor(visitorId);
        return ResponseEntity.ok(user);
    }

    @ApiOperation(value = "查询所有的用户信息",notes="根据分页排序条件查询用户信息(用identity判断用户的身份); \n author：JQJ")
    @GetMapping("/all")
    public ResponseEntity<PageResultDTO<User>> queryVisitorAll (
            @ApiParam(name = "pageVo", value = "分页信息") int identity,PageVO pageVo ){
        PageResultDTO<User> visitors = this.userService.queryVisitorAll(identity,pageVo);
        return ResponseEntity.ok(visitors);
    }
}

