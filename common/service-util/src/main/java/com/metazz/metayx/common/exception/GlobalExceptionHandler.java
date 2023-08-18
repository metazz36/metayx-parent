package com.metazz.metayx.common.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import com.metazz.metayx.common.result.Result;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result error(Exception e) {
        e.printStackTrace();
        return Result.fail(null);
    }

    //自定义异常处理
    @ExceptionHandler(MetayxException.class)
    @ResponseBody
    public Result error(MetayxException exception) {
        return Result.build(null,exception.getCode(),exception.getMessage());
    }

}
