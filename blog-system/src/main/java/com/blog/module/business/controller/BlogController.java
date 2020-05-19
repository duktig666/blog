package com.blog.module.business.controller;

import com.blog.module.business.domain.Blog;
import com.blog.module.business.domain.bo.BlogBO;
import com.blog.module.business.service.BlogService;
import com.blog.module.business.service.dto.BlogCountDTO;
import com.blog.module.business.domain.vo.BlogAndLabelVO;
import com.blog.page.dto.PageResultDTO;
import com.blog.page.vo.PageVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
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
            @ApiParam(name = "blog", value = "新增的博客信息", required = true)
            @RequestBody  @Validated BlogAndLabelVO blogAndLabelVO
    ) {
        blogService.saveBlog(blogAndLabelVO.getBlog(), blogAndLabelVO.getBlogLabelIds());
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @ApiOperation(value = "删除一条博客", notes = "根据博客id，删除一条博客（并维护中间表）;\nauthor：RSW")
    @DeleteMapping("/{blogId}")
    public ResponseEntity<Void> deleteBlog (
            @ApiParam(name = "blogId", value = "博客id", required = true) @PathVariable Long blogId ) {
        blogService.deleteBlog(blogId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @ApiOperation(value = "批量删除博客", notes = "根据博客id集合，批量删除博客（并维护中间表）;\nauthor：RSW")
    @PostMapping("/ids")
    public ResponseEntity<Void> deleteBlogs (
            @ApiParam(name = "blogIds", value = "博客id集合", required = true) @RequestBody List<Long> blogIds ) {
        blogService.deleteBlogs(blogIds);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @ApiOperation(value = "修改博客信息", notes = "修改博客信息（并维护中间表，先删除，在新增）;\nauthor：RSW")
    @PutMapping
    public ResponseEntity<Void> updateBlog (
            @ApiParam(name = "blog", value = "修改的博客信息", required = true) @RequestBody @Validated(Blog.UpdateGroup.class) BlogAndLabelVO blogAndLabelVO
    ) {
        blogService.updateBlog(blogAndLabelVO.getBlog(), blogAndLabelVO.getBlogLabelIds());
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @ApiOperation(value = "查询一条博客信息", notes = "根据博客id，查询一条博客的详细信息（包含博客类型和博客标签）;\nauthor：RSW")
    @GetMapping("/{blogId}")
    public ResponseEntity<BlogBO> queryBlogById (
            @ApiParam(name = "blogId", value = "博客标签id", required = true) @PathVariable Long blogId ) {
        return ResponseEntity.ok(blogService.queryBlogByBlogId(blogId));
    }

    @ApiOperation(value = "查询所有的博客信息(包含博客类型和博客标签)", notes = "可以分页和排序,可以根据博客标题、博客正文、博客摘要进行模糊查询;\nauthor：RSW")
    @GetMapping("/all")
    public ResponseEntity<PageResultDTO<BlogBO>> queryBlogList (
            @ApiParam(name = "pageVo", value = "分页信息") PageVO pageVo,
            @ApiParam(name = "blogDimSearchStr", value = "博客模糊查询所需数据")
            @RequestParam(required = false) String blogDimSearchStr,
            @ApiParam(name = "blogDateRange", value = "按照时间区间查询博客,开始时间")
            @RequestParam(required = false,defaultValue = "2020-01-01 00:00:00") String blogDateStart,
            @ApiParam(name = "blogDateEnd", value = "按照时间区间查询博客，结束时间")
            @RequestParam(required = false) String blogDateEnd
    ) {
        //转换开始时间
        Timestamp blogDateTimeStart = Timestamp.valueOf(blogDateStart);
        // 给时间区间结束时间设置默认时间为当前时间
        Timestamp blogDateTimeEnd;
        if(blogDateEnd==null) { // 如果结束时间为空，设置默认时间为当前时间
            blogDateTimeEnd = new Timestamp((new Date()).getTime());
        } else {
            blogDateTimeEnd = Timestamp.valueOf(blogDateEnd);
        }
        return ResponseEntity.ok(blogService.queryBlogList(pageVo, blogDimSearchStr, blogDateTimeStart,blogDateTimeEnd));
    }

    @ApiOperation(value = "查询所有的博客信息(不包含博客类型和博客标签)", notes = "可以分页和排序,可以根据博客标题、博客正文、博客摘要进行模糊查询;\nauthor：RSW")
    @GetMapping("/all/single")
    public ResponseEntity<PageResultDTO<Blog>> queryBlogAll (
            @ApiParam(name = "pageVo", value = "分页信息") PageVO pageVo,
            @ApiParam(name = "blogDimSearchStr", value = "博客模糊查询所需数据")
            @RequestParam(required = false) String blogDimSearchStr
    ) {
        return ResponseEntity.ok(blogService.queryBlogAll(pageVo, blogDimSearchStr));
    }

    @ApiOperation(value = "查询博客的总访问量、点赞量、评论量", notes = "查询博客的总访问量、点赞量、评论量;\nauthor：RSW")
    @GetMapping("/count")
    public ResponseEntity<BlogCountDTO> queryBlogCount () {
        return ResponseEntity.ok(blogService.queryBlogCount());
    }

    @ApiOperation(value = "根据博客类型id查询所有的博客信息", notes = "可以分页和排序;\nauthor：JQJ")
    @GetMapping("/all/byTypeId")
    public ResponseEntity<PageResultDTO<Blog>> queryBlogByType (
            @ApiParam(name = "pageVo", value = "分页信息") PageVO pageVo,
            @ApiParam(name = "typeId", value = "博客类型ID")
            @RequestParam(required = false) Integer typeId
    ) {
        return ResponseEntity.ok(blogService.queryBlogByType(pageVo, typeId));
    }

    @ApiOperation(value = "根据博客标签id查询所有的博客信息", notes = "可以分页和排序;\nauthor：JQJ")
    @GetMapping("/all/buLabelIds")
    public ResponseEntity<PageResultDTO<Blog>> queryBlogByLabelIds (
            @ApiParam(name = "pageVo", value = "分页信息") PageVO pageVo,
            @ApiParam(name = "labelId", value = "博客标签ID")
            @RequestParam("labelId") Long labelId
    ) {
        return ResponseEntity.ok(blogService.queryBlogByLabelId(pageVo, labelId));
    }

    @ApiOperation(value = "增加博客的浏览量", notes = "增加博客的浏览量;\nauthor：RSW")
    @PutMapping("/increase-view-number/{blogId}")
    public ResponseEntity<Void> increaseViewCount (
            @ApiParam(name = "blogId", value = "博客id", required = true) @PathVariable Long blogId
    ) {
        blogService.increaseViewCount(blogId);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @ApiOperation(value = "增加博客的点赞量", notes = "增加博客的点赞量;\nauthor：RSW")
    @PutMapping("/increase-like-number/{blogId}")
    public ResponseEntity<Void> increaseLikeCount (
            @ApiParam(name = "blogId", value = "博客id", required = true) @PathVariable Long blogId
    ) {
        blogService.increaseLikeCount(blogId);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }


}

