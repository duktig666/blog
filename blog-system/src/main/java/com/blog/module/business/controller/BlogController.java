package com.blog.module.business.controller;

import com.blog.module.business.domain.Blog;
import com.blog.module.business.domain.BlogLabel;
import com.blog.module.business.domain.BlogType;
import com.blog.module.business.domain.bo.BlogBO;
import com.blog.module.business.service.BlogService;
import com.blog.module.business.service.dto.BlogDimQueryDTO;
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
 * Date: 2020/4/11 11:07
 **/
@RestController
@RequestMapping("/api/blog")
@Api(tags = "博客管理模块")
public class BlogController {

    @Autowired
    private BlogService blogService;

    @ApiOperation(value = "新增博客类型", notes = "新增一条博客信息;\nauthor：RSW")
    @PostMapping
    public ResponseEntity<Void> saveBlogType (
            @ApiParam(name = "blog", value = "新增的博客信息", required = true) @Validated Blog blog,
            @ApiParam(name = "blogLabelIds", value = "新增博客对应的博客标签集合", required = true) List<Long> blogLabelIds
    ) {
        blogService.saveBlog(blog, blogLabelIds);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @ApiOperation(value = "删除一条博客", notes = "根据博客id，删除一条博客（并维护中间表）;\nauthor：RSW")
    @ApiImplicitParam(name = "blogId", value = "博客id", required = true)
    @DeleteMapping("/{blogId}")
    public ResponseEntity<Void> deleteBlog ( @Valid @PathVariable Long blogId ) {
        blogService.deleteBlog(blogId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @ApiOperation(value = "批量删除博客", notes = "根据博客id集合，批量删除博客（并维护中间表）;\nauthor：RSW")
    @ApiImplicitParam(name = "blogIds", value = "博客id集合", required = true)
    @DeleteMapping("/ids")
    public ResponseEntity<Void> deleteBlogs ( @Valid List<Long> blogIds ) {
        blogService.deleteBlogs(blogIds);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @ApiOperation(value = "修改博客信息", notes = "修改博客信息（并维护中间表，先删除，在新增）;\nauthor：RSW")
    @PutMapping
    public ResponseEntity<Void> updateBlog (
            @ApiParam(name = "blog", value = "修改的博客信息", required = true) @Validated(Blog.UpdateGroup.class) Blog blog,
            @ApiParam(name = "blogLabelIds", value = "修改博客对应的博客标签集合", required = true) List<Long> blogLabelIds
    ) {
        blogService.updateBlog(blog, blogLabelIds);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @ApiOperation(value = "查询一条博客信息", notes = "根据博客id，查询一条博客的详细信息（包含博客类型和博客标签）;\nauthor：RSW")
    @ApiImplicitParam(name = "blogId", value = "博客标签id", required = true)
    @GetMapping("/{blogId}")
    public ResponseEntity<BlogBO> queryBlogById ( @Valid @PathVariable Long blogId ) {
        return ResponseEntity.ok(blogService.queryBlogByBlogId(blogId));
    }

    @ApiOperation(value = "查询所有的博客信息", notes = "不包含博客类型和博客标签（可以分页和排序）;\nauthor：RSW")
    @GetMapping("/all")
    public ResponseEntity<PageResultDTO<Blog>> queryBlogAll (
            @ApiParam(name = "pageVo", value = "分页信息") PageVO pageVo,
            @ApiParam(name = "blogDimQueryDTO", value = "博客模糊查询所需数据") BlogDimQueryDTO blogDimQueryDTO
    ) {
        return ResponseEntity.ok(blogService.queryBlogList(pageVo, blogDimQueryDTO));
    }

}

