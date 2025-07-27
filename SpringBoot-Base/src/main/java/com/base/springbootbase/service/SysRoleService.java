package com.base.springbootbase.service;

import java.util.Set;

/**
 * @author MrLu
 * @version 1.0
 * @description: TODO
 * @date 2025/7/27 15:53
 */
public interface SysRoleService {
    public Set<String> selectRolePermissionByUserId(Long userId);
}
