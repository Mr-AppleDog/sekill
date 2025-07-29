package com.base.springbootbase.common.core.domain.entity;

/**
 * @author MrLu
 * @version 1.0
 * @description: 角色和菜单关联 sys_role_menu
 * @date 2025/7/28 16:45
 */
public class SysRoleMenu {
    /**
     * 角色ID
     */
    private Long roleId;

    /**
     * 菜单ID
     */
    private Long menuId;

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Long getMenuId() {
        return menuId;
    }

    public void setMenuId(Long menuId) {
        this.menuId = menuId;
    }

}
