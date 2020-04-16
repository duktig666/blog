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
    @ApiParam(name = "visitor", value = "新增的用户信息", required = true)
    @PostMapping
    public ResponseEntity<Void> saveVisitor (@Validated User user) {
        this.userService.saveVisitor(user);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @ApiOperation(value = "删除单个用户信息",notes="根据用户id删除对应用户信息; \n author：JQJ")
    @ApiImplicitParam(name = "visitorId", value = "博客id", required = true)
    @DeleteMapping("/{visitorId}")
    public ResponseEntity<Void> deleteVisitor (@Valid @PathVariable("visitorId") Long visitorId ){
        this.userService.deleteVisitor(visitorId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @ApiOperation(value = "批量删除用户信息",notes="根据用户id集合批量删除用户信息; \n author：JQJ")
    @ApiImplicitParam(name = "visitorIds", value = "用户id集合", required = true)
    @DeleteMapping("/ids")
    public ResponseEntity<Void> deleteVisitors (@Valid @PathVariable("visitorIds") List<Long> visitorIds ){
        this.userService.deleteVisitors(visitorIds);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
    @ApiOperation(value = "修改用户信息",notes="根据前台传入的信息进行修改对应用户信息; \n author：JQJ")
    @ApiParam(name = "visitor", value = "修改的用户信息", required = true)
    @PutMapping
    public ResponseEntity<Void> updateVisitor (@Validated(User.UpdateGroup.class) User user){
        this.userService.updateVisitor(user);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @ApiOperation(value = "查询一位用户信息",notes="根据用户id查询对应用户信息; \n author：JQJ")
    @ApiImplicitParam(name = "visitorId", value = "用户id", required = true)
    @GetMapping("/{visitorId}")
    public ResponseEntity<User> queryVisitorById (@Valid @PathVariable("visitorId") Long visitorId ){
        User user = this.userService.queryVisitor(visitorId);
        return ResponseEntity.ok(user);
    }

    @ApiOperation(value = "查询所有的用户信息",notes="根据分页排序条件查询用户信息(用identity判断用户的身份); \n author：JQJ")
    @ApiParam(name = "pageVo", value = "分页信息")
    @GetMapping("/all")
    public ResponseEntity<PageResultDTO<User>> queryVisitorAll (int identity,PageVO pageVo ){
        PageResultDTO<User> visitors = this.userService.queryVisitorAll(identity,pageVo);
        return ResponseEntity.ok(visitors);
    }
}

