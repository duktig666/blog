package com.blog.exception.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 功能描述：异常对象实体
 *
 * @author RenShiWei
 * Date: 2020/4/11 19:47
 **/
@Data
public class ApiErrorDTO {
    /** 异常状态码 */
    private Integer status = 400;

    /** 出现异常的时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime timestamp;

    /** 异常信息 */
    private String message;

    private ApiErrorDTO () {
        timestamp = LocalDateTime.now();
    }

    /**
     * 功能描述：返回默认状态400的异常
     * @param message 异常信息
     * @return 异常对象
     * @author RenShiWei
     * Date: 2020/4/11 19:49
     */
    public static ApiErrorDTO error( String message){
        ApiErrorDTO apiErrorDto = new ApiErrorDTO();
        apiErrorDto.setMessage(message);
        return apiErrorDto;
    }

    /**
     * 功能描述：返回异常对象，可设置异常状态码和异常信息
     * @param status 异常状态码
     * @param message 异常信息
     * @return 异常对象
     * @author RenShiWei
     * Date: 2020/4/11 19:49
     */
    public static ApiErrorDTO error( Integer status, String message){
        ApiErrorDTO apiErrorDto = new ApiErrorDTO();
        apiErrorDto.setStatus(status);
        apiErrorDto.setMessage(message);
        return apiErrorDto;
    }

}

