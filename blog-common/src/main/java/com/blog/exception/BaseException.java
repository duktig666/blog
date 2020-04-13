package com.blog.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

/**
 * 功能描述：统一异常处理
 *
 * @author RenShiWei
 * Date: 2020/4/11 19:52
 **/
@Getter
public class BaseException extends RuntimeException{

    private Integer status = BAD_REQUEST.value();

    public BaseException ( String msg){
        super(msg);
    }

    public BaseException ( HttpStatus status, String msg){
        super(msg);
        this.status = status.value();
    }
}
