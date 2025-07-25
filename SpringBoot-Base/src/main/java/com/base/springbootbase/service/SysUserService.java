package com.base.springbootbase.service;

import com.base.springbootbase.domain.entity.SysUser;

/**
 * @author MrLu
 * @version 1.0
 * @description:
 * @date 2025/7/26 0:03
 */
public interface SysUserService {
    /**
     * 通过用户名查询用户
     *
     * @param userName 用户名
     * @return 用户对象信息
     */
    public SysUser selectUserByUserName(String userName);
}
