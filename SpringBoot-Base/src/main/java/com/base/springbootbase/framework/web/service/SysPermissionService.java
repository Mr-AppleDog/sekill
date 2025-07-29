package com.base.springbootbase.framework.web.service;

import com.base.springbootbase.common.core.domain.entity.SysRole;
import com.base.springbootbase.common.core.domain.entity.SysUser;
import com.base.springbootbase.service.SysMenuService;
import com.base.springbootbase.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author MrLu
 * @version 1.0
 * @description: 用户权限处理
 * @date 2025/7/26 0:35
 */
@Component
public class SysPermissionService {

    @Autowired
    private SysMenuService menuService;

    @Autowired
    private SysRoleService roleService;
    /**
     * 获取菜单数据权限
     *
     * @param user 用户信息
     * @return 菜单权限信息
     */
    public Set<String> getMenuPermission(SysUser user)
    {
        Set<String> perms = new HashSet<String>();
        // 管理员拥有所有权限
        if (user.isAdmin())
        {
            // TODO: 2025/7/26 获取所有权限  perms.addAll(menuService.AllMenuPerms());
//            perms.addAll(menuService.selectMenuPermsByUserId(user.getUserId()));
            perms.add("*:*:*");
        }
        else
        {
            List<SysRole> roles = user.getRoles();
            if (!roles.isEmpty() && roles.size() > 1)
            {
                // 多角色设置permissions属性，以便数据权限匹配权限
                for (SysRole role : roles)
                {
                    Set<String> rolePerms = menuService.selectMenuPermsByRoleId(role.getRoleId());
                    role.setPermissions(rolePerms);
                    perms.addAll(rolePerms);
                }
            }
            else
            {
                perms.addAll(menuService.selectMenuPermsByUserId(user.getUserId()));
            }
        }
        return perms;
    }

    /**
     * 获取角色数据权限
     *
     * @param user 用户信息
     * @return 角色权限信息
     */
    public Set<String> getRolePermission(SysUser user)
    {
        Set<String> roles = new HashSet<String>();
        // 管理员拥有所有权限
        if (user.isAdmin())
        {
            roles.add("admin");
        }
        else
        {
            roles.addAll(roleService.selectRolePermissionByUserId(user.getUserId()));
        }
        return roles;
    }
}
