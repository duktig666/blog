package com.blog.module.business.controller;

import com.blog.module.business.domain.Blog;
import com.blog.module.business.domain.BlogLabel;
import com.blog.module.business.domain.BlogType;
import com.blog.module.business.domain.bo.BlogBO;
import com.blog.module.business.service.BlogService;
import com.blog.module.business.service.dto.BlogCountDTO;
import com.blog.module.business.service.dto.BlogInsertVO;
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

    @ApiOperation(value = "新增博客", notes = "新增一条博客信息;\nauthor：RSW")
    @PostMapping
    public ResponseEntity<Void> saveBlogType (
            @ApiParam(name = "blog", value = "新增的博客信息", required = true) @RequestBody BlogInsertVO blogInsertVO
    ) {
        System.out.println(blogInsertVO + "------");
        blogService.saveBlog(blogInsertVO.getBlog(), blogInsertVO.getBlogLabelIds());
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @ApiOperation(value = "删除一条博客", notes = "根据博客id，删除一条博客（并维护中间表）;\nauthor：RSW")
    @DeleteMapping("/{blogId}")
    public ResponseEntity<Void> deleteBlog (
            @ApiParam(name = "blogId", value = "博客id", required = true)
            @Valid @PathVariable Long blogId ) {
        blogService.deleteBlog(blogId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @ApiOperation(value = "批量删除博客", notes = "根据博客id集合，批量删除博客（并维护中间表）;\nauthor：RSW")
    @DeleteMapping("/ids")
    public ResponseEntity<Void> deleteBlogs (
            @ApiParam(name = "blogIds", value = "博客id集合", required = true)
            @Valid @RequestParam("blogIds") List<Long> blogIds ) {
        blogService.deleteBlogs(blogIds);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @ApiOperation(value = "修改博客信息", notes = "修改博客信息（并维护中间表，先删除，在新增）;\nauthor：RSW")
    @PutMapping
    public ResponseEntity<Void> updateBlog (
            @ApiParam(name = "blog", value = "修改的博客信息", required = true) @Validated(Blog.UpdateGroup.class) Blog blog,
            @ApiParam(name = "blogLabelIds", value = "修改博客对应的博客标签集合", required = true)
            @RequestParam List<Long> blogLabelIds
    ) {
        blogService.updateBlog(blog, blogLabelIds);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @ApiOperation(value = "查询一条博客信息", notes = "根据博客id，查询一条博客的详细信息（包含博客类型和博客标签）;\nauthor：RSW")
    @GetMapping("/{blogId}")
    public ResponseEntity<BlogBO> queryBlogById (
            @ApiParam(name = "blogId", value = "博客标签id", required = true)
            @Valid @PathVariable Long blogId ) {
        return ResponseEntity.ok(blogService.queryBlogByBlogId(blogId));
    }

    @ApiOperation(value = "查询所有的博客信息(包含博客类型和博客标签)", notes = "可以分页和排序,可以根据博客标题、博客正文、博客摘要进行模糊查询;\nauthor：RSW")
    @GetMapping("/all")
    public ResponseEntity<PageResultDTO<BlogBO>> queryBlogList (
            @ApiParam(name = "pageVo", value = "分页信息") PageVO pageVo,
            @ApiParam(name = "blogDimSearchStr", value = "博客模糊查询所需数据") @RequestParam(required = false) String blogDimSearchStr
    ) {
        return ResponseEntity.ok(blogService.queryBlogList(pageVo, blogDimSearchStr));
    }

    @ApiOperation(value = "查询所有的博客信息(不包含博客类型和博客标签)", notes = "可以分页和排序,可以根据博客标题、博客正文、博客摘要进行模糊查询;\nauthor：RSW")
    @GetMapping("/all/single")
    public ResponseEntity<PageResultDTO<Blog>> queryBlogAll (
            @ApiParam(name = "pageVo", value = "分页信息") PageVO pageVo,
            @ApiParam(name = "blogDimSearchStr", value = "博客模糊查询所需数据") @RequestParam(required = false) String blogDimSearchStr
    ) {
        return ResponseEntity.ok(blogService.queryBlogAll(pageVo, blogDimSearchStr));
    }

    @ApiOperation(value = "查询博客的总访问量、点赞量、评论量", notes = "查询博客的总访问量、点赞量、评论量;\nauthor：RSW")
    @GetMapping("/count")
    public ResponseEntity<BlogCountDTO> queryBlogCount () {
        return ResponseEntity.ok(blogService.queryBlogCount());
    }

}

