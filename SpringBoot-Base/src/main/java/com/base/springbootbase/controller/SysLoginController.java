package com.base.springbootbase.controller;

import com.base.springbootbase.common.util.SecurityUtils;
import com.base.springbootbase.common.core.domain.entity.SysMenu;
import com.base.springbootbase.common.core.domain.entity.SysUser;
import com.base.springbootbase.common.result.Result;
import com.base.springbootbase.common.core.domain.model.LoginBody;
import com.base.springbootbase.framework.web.service.SysLoginService;
import com.base.springbootbase.framework.web.service.SysPermissionService;
import com.base.springbootbase.service.SysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

/**
 * @author MrLu
 * @version 1.0
 * @description: 登录认证
 * @date 2025/7/24 18:32
 */
@RestController
public class SysLoginController {
    @Autowired
    private SysLoginService loginService;

    @Autowired
    private SysPermissionService permissionService;

    @Autowired
    private SysMenuService menuService;
    /**
     * 登录
     * @return
     */
    @PostMapping("/login")
    public Result login(@RequestBody LoginBody loginBody) {
//        System.out.println("登录成功");
        String token=loginService.login(loginBody);
        Result result = Result.success(token);
        return result;
    }

    /**
     * 用户信息
     * @return
     */
    @GetMapping("/getInfo")
    public Result getInfo() {
        SysUser user = SecurityUtils.getLoginUser().getUser();
        Set<String> roles = permissionService.getRolePermission(user);
        // 权限集合
        Set<String> permissions = permissionService.getMenuPermission(user);
        Result result = Result.success();
        result.put("user", user);
        result.put("roles", roles);
        result.put("permissions", permissions);
        return result;
    }

    /**
     * 获取路由信息
     * @return
     */
    @GetMapping("/getRouters")
    public Result getRouters() {
        Long userId = SecurityUtils.getUserId();
        List<SysMenu> menus = menuService.selectMenuTreeByUserId(userId);
        return Result.success(menuService.buildMenus(menus));
    }
}
