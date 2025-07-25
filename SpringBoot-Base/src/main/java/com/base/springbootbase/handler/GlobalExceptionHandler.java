package com.base.springbootbase.handler;

import cn.hutool.core.util.StrUtil;
import com.base.springbootbase.common.exception.ServiceException;
import com.base.springbootbase.common.result.Result;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.xml.stream.util.StreamReaderDelegate;

/**
 * @author MrLu
 * @version 1.0
 * @description:  全局异常处理处理，处理项目中的业务异常
 * @date 2025/7/24 23:56
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ServiceException.class)
    public Result handleServiceException(ServiceException e,HttpServletRequest request) {
        Integer code = e.getCode();
        return StrUtil.isEmptyIfStr(code) ? Result.error() : Result.error(code, e.getMessage());
    }
    
    @ExceptionHandler(RuntimeException.class)
    public Result handleRuntimeException(RuntimeException e, HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        // TODO: 2025/7/25  使用日志记录 
//        log.error("请求地址'{}',发生未知异常.", requestURI, e);
        System.out.println(requestURI);
        return Result.error(e.getMessage());
    }
}
