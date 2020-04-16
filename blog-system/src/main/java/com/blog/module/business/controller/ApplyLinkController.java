package com.blog.module.business.controller;

import com.blog.module.business.domain.ApplyLink;
import com.blog.module.business.service.ApplyLinkService;
import com.blog.page.dto.PageResultDTO;
import com.blog.page.vo.PageVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiOperation;
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
 * Date: 2020/4/11 11:07
 **/
@RestController
@RequestMapping("/api/apply-link")
@Api(tags = "申请友情链接模块")
public class ApplyLinkController {
    @Autowired
    private ApplyLinkService applyLinkService;

    @ApiOperation(value = "新增友情链接信息",notes="新增一条友情链接信息; \n author：JQJ")
    @PostMapping
    public ResponseEntity<Void> saveApplyLink (
            @Validated
            @ApiParam(name = "applyLink", value = "新增的友情链接信息", required = true) ApplyLink applyLink) {
        this.applyLinkService.saveApplyLink(applyLink);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @ApiOperation(value = "删除单个友情链接信息",notes="根据友情链接id删除对应友情链接信息; \n author：JQJ")
    @DeleteMapping("{applyLinkId}")
    public ResponseEntity<Void> deleteApplyLink (
            @ApiParam(name = "applyLinkId", value = "友情链接", required = true)
            @Valid @PathVariable("applyLinkId") Long applyLinkId ){
        this.applyLinkService.deleteApplyLink(applyLinkId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @ApiOperation(value = "批量删除友情链接信息",notes="根据友情链接id集合批量删除友情链接信息; \n author：JQJ")
    @ApiParam(name = "applyLinkIds", value = "友情链接id集合", required = true)
    @DeleteMapping("/ids")
    public ResponseEntity<Void> deleteApplyLinks (@Valid @RequestParam("applyLinkIds") List<Long> applyLinkIds ){
        this.applyLinkService.deleteApplyLinks(applyLinkIds);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
    @ApiOperation(value = "修改友情链接信息",notes="根据前台传入的信息进行修改对应友情链接信息; \n author：JQJ")
    @PutMapping
    public ResponseEntity<String> updateApplyLink (
            @ApiParam(name = "applyLink", value = "修改的友情链接信息", required = true)
            @Validated(ApplyLink.UpdateGroup.class) ApplyLink applyLink ){
        this.applyLinkService.updateApplyLink(applyLink);

        // 如果修改状态成功，返回传入的状态信息
        int state = applyLink.getState();
        if(state == 1){
            return ResponseEntity.ok("同意申请");
        }
        return ResponseEntity.ok("拒绝申请");
    }

    @ApiOperation(value = "查询一条友情链接信息",notes="根据友情链接id查询对应友情链接信息; \n author：JQJ")
    @GetMapping("{applyLinkId}")
    public ResponseEntity<ApplyLink> queryApplyLinkById (
            @ApiParam(name = "applyLinkId", value = "友情链接id", required = true)
            @Valid @PathVariable("applyLinkId") Long applyLinkId ){
        ApplyLink applyLink = this.applyLinkService.queryApplyLinkById(applyLinkId);
        return ResponseEntity.ok(applyLink);
    }

    @ApiOperation(value = "查询所有的友情链接信息",notes="根据分页排序条件查询友情链接信息; \n author：JQJ")
    @GetMapping("/all")
    public ResponseEntity<PageResultDTO<ApplyLink>> queryApplyLinkAll (
            @ApiParam(name = "pageVo", value = "分页信息")PageVO pageVO ){
        PageResultDTO<ApplyLink> applyLinks = (PageResultDTO<ApplyLink>) this.applyLinkService.queryApplyLinkAll(pageVO);
        return ResponseEntity.ok(applyLinks);
    }

    @ApiOperation(value = "查询所有符合状态的友情链接信息",notes="根据分页排序，和状态条件查询友情链接信息; \n author：JQJ")
    @GetMapping("/state/{state}")
    public ResponseEntity<PageResultDTO<ApplyLink>> queryApplyLinksByState(
            @ApiParam(name = "state", value = "友情链接状态") @PathVariable("state") Integer state,
            @ApiParam(name = "pageVo", value = "分页信息") PageVO pageVO){
        PageResultDTO<ApplyLink> applyLinks = this.applyLinkService.queryApplyLinksByState(state,pageVO);
        return ResponseEntity.ok(applyLinks);
    }

}

