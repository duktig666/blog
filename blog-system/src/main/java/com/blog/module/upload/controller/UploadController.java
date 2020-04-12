package com.blog.module.upload.controller;

import com.blog.module.upload.service.UploadService;
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
@RequestMapping("/upload")
public class UploadController {

    @Autowired
    private UploadService uploadService;

    /**
     * 图片上传
     * - 请求方式：上传肯定是POST
     * - 请求路径：/upload/image
     * - 请求参数：文件，参数名是file，SpringMVC会封装为一个接口：MultipartFile
     * - 返回结果：上传成功后得到的文件的url路径，也就是返回String
     * @param file
     * @return
     */
    @PostMapping("/image")
    public ResponseEntity<String> uploadImage(@RequestParam("file") MultipartFile file){
        String url = this.uploadService.upload(file);
        //判断url是否为空
        if(StringUtils.isBlank(url)){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(url);
    }
}

