package com.szml.pl.common;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @description: 全局异常捕获
 * @author：karma
 * @date: 2023/10/21
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 处理自定义异常
     *
     */
    @ExceptionHandler(value = DefinitionException.class)
    @ResponseBody
    public Result bizExceptionHandler(DefinitionException e) {
        return Result.buildResult(e.errorCode.toString(),e.errorMsg);
    }

    /**
     * 处理其他异常
     *
     */
//    @ExceptionHandler(value = Exception.class)
//    @ResponseBody
//    public Result exceptionHandler( Exception e) {
//        return Result.buildErrorResult();
//    }
}
