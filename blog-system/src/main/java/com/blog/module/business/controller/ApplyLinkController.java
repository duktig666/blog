package com.blog.module.business.controller;

import com.blog.module.business.domain.ApplyLink;
import com.blog.module.business.domain.ApplyLink;
import com.blog.module.business.service.ApplyLinkService;
import com.blog.page.dto.PageResultDto;
import com.blog.page.vo.PageVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
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
    @ApiImplicitParam(name = "applyLink", value = "新增的友情链接信息", required = true)
    @PostMapping
    public ResponseEntity<Void> saveApplyLink (@Validated ApplyLink applyLink) {
        this.applyLinkService.saveApplyLink(applyLink);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @ApiOperation(value = "删除单个友情链接信息",notes="根据友情链接id删除对应友情链接信息; \n author：JQJ")
    @ApiImplicitParam(name = "applyLinkId", value = "友情链接", required = true)
    @DeleteMapping("{applyLinkId}")
    public ResponseEntity<Void> deleteApplyLink (@Valid @PathVariable("applyLinkId") Long applyLinkId ){
        this.applyLinkService.deleteApplyLink(applyLinkId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @ApiOperation(value = "批量删除友情链接信息",notes="根据友情链接id集合批量删除友情链接信息; \n author：JQJ")
    @ApiImplicitParam(name = "applyLinkIds", value = "友情链接id集合", required = true)
    @DeleteMapping("/ids{applyLinkIds}")
    public ResponseEntity<Void> deleteApplyLinks (@Valid @PathVariable("applyLinkIds") List<Long> applyLinkIds ){
        this.applyLinkService.deleteApplyLinks(applyLinkIds);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
    @ApiOperation(value = "修改友情链接信息",notes="根据前台传入的信息进行修改对应友情链接信息; \n author：JQJ")
    @ApiImplicitParam(name = "applyLink", value = "修改的友情链接信息", required = true)
    @PutMapping
    public ResponseEntity<Void> updateApplyLink (@Validated ApplyLink applyLink ){
        this.applyLinkService.updateApplyLink(applyLink);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @ApiOperation(value = "查询一位友情链接信息",notes="根据友情链接id查询对应友情链接信息; \n author：JQJ")
    @ApiImplicitParam(name = "applyLinkId", value = "友情链接id", required = true)
    @GetMapping("{applyLinkId}")
    public ResponseEntity<ApplyLink> queryApplyLinkById (@Valid @PathVariable("applyLinkId") Long applyLinkId ){
        ApplyLink ApplyLink = this.applyLinkService.queryApplyLinkById(applyLinkId);
        return ResponseEntity.ok(ApplyLink);
    }

    @ApiOperation(value = "查询所有的友情链接信息",notes="根据分页排序条件查询友情链接信息; \n author：JQJ")
    @ApiImplicitParam(name = "pageVo", value = "分页信息")
    @GetMapping("/all")
    public ResponseEntity<PageResultDto<ApplyLink>> queryApplyLinkAll (PageVo pageVo ){
        PageResultDto<ApplyLink> ApplyLinks = (PageResultDto<ApplyLink>) this.applyLinkService.queryApplyLinkAll(pageVo);
        return ResponseEntity.ok(ApplyLinks);
    }

    @ApiOperation(value = "查询所有符合状态的友情链接信息",notes="根据分页排序，和状态条件查询友情链接信息; \n author：JQJ")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "state", value = "友情链接状态"),
            @ApiImplicitParam(name = "pageVo", value = "分页信息")
    })
    @GetMapping("/State/{state}")
    public ResponseEntity<PageResultDto<ApplyLink>> queryApplyLinkAll( @PathVariable("state") Integer state, PageVo pageVo){
        PageResultDto<ApplyLink> ApplyLinks = (PageResultDto<ApplyLink>) this.applyLinkService.queryApplyLinksByState(state,pageVo);
        return ResponseEntity.ok(ApplyLinks);
    }

}

