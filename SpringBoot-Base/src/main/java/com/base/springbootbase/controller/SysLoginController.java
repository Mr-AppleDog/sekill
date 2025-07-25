package com.base.springbootbase.controller;

import com.base.springbootbase.entity.User;
import com.base.springbootbase.common.result.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author MrLu
 * @version 1.0
 * @description: 登录认证
 * @date 2025/7/24 18:32
 */
@RestController
public class SysLoginController {

    /**
     * 登录
     * @return
     */
    @PostMapping("/login")
    public Result login() {
//        System.out.println("登录成功");
        Result result = Result.success("登录成功");
        result.put("token", "123456");
        return result;
    }

    /**
     * 用户信息
     * @return
     */
    @GetMapping("/getInfo")
    public Result getInfo() {
        User user = new User();
        user.setName("admin-spring-test344");
        return Result.success(user);
    }

    /**
     * 获取路由信息
     * @return
     */
    @GetMapping("/getRouters")
    public Result getRouters() {
        User user = new User();
        user.setName("admin");
        return Result.success(user);
    }
}
