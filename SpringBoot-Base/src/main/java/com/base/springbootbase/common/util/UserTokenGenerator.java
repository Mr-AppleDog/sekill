package com.base.springbootbase.common.util;

import com.base.springbootbase.common.core.domain.entity.SysUser;
import com.base.springbootbase.mapper.SysUserMapper;
import com.base.springbootbase.service.Impl.SysUserServiceImpl;
import com.base.springbootbase.service.SysUserService;
import org.aspectj.lang.annotation.AfterReturning;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author MrLu
 * @version 1.0
 * @description:
 * @date 2025/7/31 16:21
 */
public class UserTokenGenerator {
    // 用户名前缀
    private static final String USERNAME_PREFIX = "user_";
    // 登录地址
    private static final String LOGIN_URL = "http://localhost:9090/login";
    // 生成的token保存的文件路径
    private static final String TOKEN_FILE_PATH = "tokens.txt";
    // 要生成的用户数量
    private static final int USER_COUNT = 100;
    // HTTP客户端超时时间(秒)
    private static final int HTTP_TIMEOUT = 10;

    /**
     * 生成指定数量的用户
     * @param count 用户数量
     * @return 用户列表
     */
    public static void generateUsers(int count) {
        SysUserService sysUserService = new SysUserServiceImpl();
        for (int i = 0; i < count; i++) {
            SysUser user = new SysUser();
            String username = USERNAME_PREFIX + (i + 1);
            // 生成随机6位数字密码，符合示例中的数字密码格式
            String password = "123456";
            user.setUserName(username);
            user.setPassword(password);
            user.setPassword(SecurityUtils.encryptPassword(user.getPassword()));
            sysUserService.insertUser(user);
        }
        System.out.println("成功生成 " + count + " 个用户");
    }

    public static void main(String[] args) {
        generateUsers(2);
    }
}
