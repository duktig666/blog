package com.blog.module.upload.controller;

import com.blog.module.upload.service.UploadService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 * Description： 文件上传功能
 *
 *
 * @author jiaoqianjin
 * Date: 2020/4/11 21:18
 **/

@Controller
@RequestMapping("/api/upload")
@Api(tags = "上传图片模块")
public class UploadController {

    @Autowired
    private UploadService uploadService;

    /**
     * 功能描述：图片上传
     *  请求参数：文件，参数名是file，SpringMVC会封装为一个接口：MultipartFile
     *   返回结果：上传成功后得到的文件的url路径，也就是返回String
     * @param file
     * @return 图片的url地址
     * @author jiaoqianjin
     * Date: 2020/4/13 14:25
     */
    @ApiOperation(value = "上传图片",notes="根据上传图片并返回url; author：JQJ")
    @PostMapping("/image")
    public ResponseEntity<String> uploadImage(
            @ApiParam(name = "file", value = "选择上传的图片", required = true)
            @RequestParam("file") MultipartFile file){
        String url = this.uploadService.upload(file);
        //判断url是否为空
        if(StringUtils.isBlank(url)){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(url);
    }
}

