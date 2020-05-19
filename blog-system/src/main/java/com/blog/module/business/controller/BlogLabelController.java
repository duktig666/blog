package com.blog.module.business.controller;

import com.blog.module.business.domain.BlogLabel;
import com.blog.module.business.domain.BlogType;
import com.blog.module.business.service.BlogLabelService;
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
 * 功能描述：博客标签管理接口
 *
 * @author RenShiWei
 * Date: 2020/4/11 11:08
 **/
@RestController
@RequestMapping("/api/blog-label")
@Api(tags = "博客标签管理模块")
public class BlogLabelController {

    @Autowired
    private BlogLabelService blogLabelService;

    @ApiOperation(value = "新增博客标签", notes = "新增一条博客标签;\nauthor：RSW")
    @PostMapping
    public ResponseEntity<Void> saveBlogType (
            @ApiParam(name = "blogLabel", value = "新增的博客标签信息", required = true)
            @Validated BlogLabel blogLabel ) {
        blogLabelService.saveBlogLabel(blogLabel);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @ApiOperation(value = "删除单个博客标签", notes = "根据博客标签id删除;\nauthor：RSW")
    @DeleteMapping("/{blogLabelId}")
    public ResponseEntity<Void> deleteBlogType (
            @ApiParam(name = "blogLabelId", value = "博客标签id", required = true) @PathVariable Long blogLabelId ) {
        blogLabelService.deleteBlogLabel(blogLabelId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @ApiOperation(value = "批量删除博客标签", notes = "根据博客标签id集合批量删除;\nauthor：RSW")
    @PostMapping("/ids")
    public ResponseEntity<Void> deleteBlogTypes (
            @ApiParam(name = "blogLabelIds", value = "博客标签id集合", required = true)
            @RequestBody List<Long> blogLabelIds ) {
        blogLabelService.deleteBlogLabels(blogLabelIds);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @ApiOperation(value = "修改博客标签信息", notes = "根据前台传入的信息进行修改;\nauthor：RSW")
    @PutMapping
    public ResponseEntity<Void> updateBlogType (
            @ApiParam(name = "blogLabel", value = "修改的博客标签信息", required = true)
            @Validated(BlogLabel.UpdateGroup.class) BlogLabel blogLabel ) {
        blogLabelService.updateBlogLabel(blogLabel);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @ApiOperation(value = "查询一条博客标签信息", notes = "根据博客标签id查询;\nauthor：RSW")
    @GetMapping("/{blogLabelId}")
    public ResponseEntity<BlogLabel> queryBlogById (
            @ApiParam(name = "blogLabelId", value = "博客标签id", required = true)
            @Valid @PathVariable Long blogLabelId ) {
        return ResponseEntity.ok(blogLabelService.queryBlogLabelById(blogLabelId));
    }

    @ApiOperation(value = "查询所有的博客标签（可以分页和排序）", notes = "可以分页;\nauthor：RSW")
    @GetMapping("/all")
    public ResponseEntity<PageResultDTO<BlogLabel>> queryBlogAll (
            @ApiParam(name = "pageVo", value = "分页信息") PageVO pageVo ) {
        return ResponseEntity.ok(blogLabelService.queryBlogLabelAll(pageVo));
    }

}

