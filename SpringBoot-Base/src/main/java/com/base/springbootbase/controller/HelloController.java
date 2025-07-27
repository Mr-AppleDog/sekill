package com.base.springbootbase.controller;

import com.base.springbootbase.common.constant.HttpStatus;
import com.base.springbootbase.common.constant.MessageConstant;
import com.base.springbootbase.common.exception.PasswordErrorException;
import com.base.springbootbase.common.exception.ServiceException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author MrLu
 * @version 1.0
 * @description:
 * @date 2025/7/24 17:34
 */
@RestController
@RequestMapping("/hello")
public class HelloController {
    @PreAuthorize("hasAuthority('sys:hello')")
    @RequestMapping("/test")
    public String hello(){
        return "Hello world";
    }

    @GetMapping("/testServiceException")
    public String testServiceException(){
        throw new ServiceException("未登录", HttpStatus.UNAUTHORIZED);
    }

    @GetMapping("/testRuntimeException")
    public String testRuntimeException(){
        throw new PasswordErrorException(MessageConstant.PASSWORD_ERROR);
    }
}
