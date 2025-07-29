package com.base.springbootbase.common.core.domain.entity;

/**
 * @author MrLu
 * @version 1.0
 * @description: 用户和角色关联 sys_user_role
 * @date 2025/7/29 2:05
 */
public class SysUserRole {
    /** 用户ID */
    private Long userId;

    /** 角色ID */
    private Long roleId;

    public Long getUserId()
    {
        return userId;
    }

    public void setUserId(Long userId)
    {
        this.userId = userId;
    }

    public Long getRoleId()
    {
        return roleId;
    }

    public void setRoleId(Long roleId)
    {
        this.roleId = roleId;
    }
}
