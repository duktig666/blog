package com.blog.module.business.controller;

import com.blog.module.business.domain.Blogger;
import com.blog.module.business.domain.Visitor;
import com.blog.module.business.service.VisitorService;
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
@RequestMapping("/api/visitor")
@Api(tags = "游客信息管理模块")
public class VisitorController {
    @Autowired
    private VisitorService visitorService;

    @ApiOperation(value = "新增游客信息",notes="新增一位游客信息; \n author：JQJ")
    @ApiParam(name = "visitor", value = "新增的游客信息", required = true)
    @PostMapping
    public ResponseEntity<Void> saveVisitor (@Validated Visitor visitor) {
        this.visitorService.saveVisitor(visitor);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @ApiOperation(value = "删除单个游客信息",notes="根据游客id删除对应游客信息; \n author：JQJ")
    @ApiImplicitParam(name = "visitorId", value = "博客id", required = true)
    @DeleteMapping("/{visitorId}")
    public ResponseEntity<Void> deleteVisitor (@Valid @PathVariable("visitorId") Long visitorId ){
        this.visitorService.deleteVisitor(visitorId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @ApiOperation(value = "批量删除游客信息",notes="根据游客id集合批量删除游客信息; \n author：JQJ")
    @ApiImplicitParam(name = "blogTypeIds", value = "游客id集合", required = true)
    @DeleteMapping("/ids")
    public ResponseEntity<Void> deleteVisitors (@Valid @PathVariable("visitorIds") List<Long> visitorIds ){
        this.visitorService.deleteVisitors(visitorIds);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
    @ApiOperation(value = "修改游客信息",notes="根据前台传入的信息进行修改对应游客信息; \n author：JQJ")
    @ApiParam(name = "blogType", value = "修改的游客信息", required = true)
    @PutMapping
    public ResponseEntity<Void> updateVisitor (@Validated(Visitor.UpdateGroup.class) Visitor visitor ){
        this.visitorService.updateVisitor(visitor);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @ApiOperation(value = "查询一位游客信息",notes="根据游客id查询对应游客信息; \n author：JQJ")
    @ApiImplicitParam(name = "blogTypeId", value = "游客id", required = true)
    @GetMapping("/{visitorId}")
    public ResponseEntity<Visitor> queryVisitorById (@Valid @PathVariable("visitorId") Long visitorId ){
        Visitor visitor = this.visitorService.queryVisitor(visitorId);
        return ResponseEntity.ok(visitor);
    }

    @ApiOperation(value = "查询所有的游客信息",notes="根据分页排序条件查询游客信息; \n author：JQJ")
    @ApiImplicitParam(name = "pageVo", value = "分页信息")
    @GetMapping("/all")
    public ResponseEntity<PageResultDTO<Visitor>> queryVisitorAll ( PageVO pageVo ){
        PageResultDTO<Visitor> visitors = (PageResultDTO<Visitor>) this.visitorService.queryVisitorAll(pageVo);
        return ResponseEntity.ok(visitors);
    }

}

