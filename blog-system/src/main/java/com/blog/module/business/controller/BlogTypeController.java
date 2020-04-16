package com.blog.module.business.controller;

import com.blog.module.business.domain.BlogType;
import com.blog.module.business.service.BlogTypeService;
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
 * 功能描述：博客相关的接口
 *
 * @author RenShiWei
 * Date: 2020/4/11 11:08
 **/
@RestController
@RequestMapping("/api/blog-type")
@Api(tags = "博客类型管理模块")
public class BlogTypeController {

    @Autowired
    private BlogTypeService blogTypeService;

    @ApiOperation(value = "新增博客类型", notes = "新增一条博客类型信息;\nauthor：RSW")
    @PostMapping
    public ResponseEntity<Void> saveBlogType (
            @ApiParam(name = "blogType", value = "新增的博客类型信息", required = true)
            @Validated BlogType blogType ) {
        blogTypeService.saveBlogType(blogType);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @ApiOperation(value = "删除单个博客类型", notes = "根据博客类型id删除;\nauthor：RSW")
    @DeleteMapping("/{blogTypeId}")
    public ResponseEntity<Void> deleteBlogType (
            @ApiParam(name = "blogTypeId", value = "博客类型id", required = true)
            @Valid @PathVariable Long blogTypeId ) {
        blogTypeService.deleteBlogType(blogTypeId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @ApiOperation(value = "批量删除博客类型", notes = "根据博客类型id集合批量删除;\nauthor：RSW")
    @DeleteMapping("/ids")
    public ResponseEntity<Void> deleteBlogTypes (
            @ApiParam(name = "blogTypeIds", value = "博客类型id集合",required = true)
            @RequestParam("blogTypeIds") List<Long> blogTypeIds ) {
        blogTypeService.deleteBlogTypes(blogTypeIds);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @ApiOperation(value = "修改博客类型信息", notes = "根据前台传入的信息进行修改;\nauthor：RSW")
    @PutMapping
    public ResponseEntity<Void> updateBlogType (
            @ApiParam(name = "blogType", value = "修改的博客类型信息", required = true)
            @Validated(BlogType.UpdateGroup.class) BlogType blogType ) {
        blogTypeService.updateBlogType(blogType);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @ApiOperation(value = "查询一条博客类型信息", notes = "根据博客类型id查询;\nauthor：RSW")
    @GetMapping("/{blogTypeId}")
    public ResponseEntity<BlogType> queryBlogById (
            @ApiParam(name = "blogTypeId", value = "博客类型id", required = true)
            @Valid @PathVariable Long blogTypeId ) {
        return ResponseEntity.ok(blogTypeService.queryBlogTypeById(blogTypeId));
    }

    @ApiOperation(value = "查询所有的博客类型（可以分页和排序）", notes = "可以分页;\nauthor：RSW")
    @GetMapping("/all")
    public ResponseEntity<PageResultDTO<BlogType>> queryBlogAll (
            @ApiParam(name = "pageVo", value = "分页信息") PageVO pageVo ) {
        return ResponseEntity.ok(blogTypeService.queryBlogTypeAll(pageVo));
    }

}

