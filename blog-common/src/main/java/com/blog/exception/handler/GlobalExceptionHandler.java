package com.blog.exception.handler;

import com.blog.exception.BaseException;
import com.blog.exception.dto.ApiErrorDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Objects;

/**
 * 功能描述：全局异常处理
 *
 * @author RenShiWei
 * Date: 2020/4/11 19:52
 **/
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 功能描述：统一返回异常对象的信息
     * @param apiErrorDto 自定义封装的异常对象
     * @return 异常对象的信息
     * @author RenShiWei
     * Date: 2020/4/11 20:01
     */
    private ResponseEntity<ApiErrorDTO> buildResponseEntity( ApiErrorDTO apiErrorDto ) {
        return new ResponseEntity<>(apiErrorDto, HttpStatus.valueOf(apiErrorDto.getStatus()));
    }

    /**
     * 功能描述：处理所有不可知的异常
     * @param e 异常 Throwable(异常的根类)
     * @return 异常对象信息
     * @author RenShiWei
     * Date: 2020/4/11 19:54
     */
    @ExceptionHandler(Throwable.class)
    public ResponseEntity<ApiErrorDTO> handleException( Throwable e){
        // 异常信息
        log.error("Throwable",e);
        return buildResponseEntity(ApiErrorDTO.error(e.getMessage()));
    }

    /**
     * 处理自定义异常
     */
    @ExceptionHandler(value = BaseException.class)
    public ResponseEntity<ApiErrorDTO> badRequestException( BaseException e) {
        // 打印堆栈信息
        log.error("BadRequestException(自定义请求异常)",e);
        return buildResponseEntity(ApiErrorDTO.error(e.getStatus(),e.getMessage()));
    }

    /**
     * 功能描述：处理所有接口数据验证异常
     * 使用：在接口的参数前使用注解 @Valid 验证参数的合法性
     *
     *  暂时不确定能否使用（需要测试）
     * @param e 接口数据验证异常 MethodArgumentNotValidException
     * @return 接口数据验证异常
     * @author RenShiWei
     * Date: 2020/4/11 20:19
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiErrorDTO> handleMethodArgumentNotValidException( MethodArgumentNotValidException e){
        // 打印堆栈信息
        log.error("MethodArgumentNotValidException(接口数据验证异常)",e);
        String message = Objects.requireNonNull(e.getBindingResult().getFieldError()).getDefaultMessage();
        return buildResponseEntity(ApiErrorDTO.error(message));
    }

}

