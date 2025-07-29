package com.base.springbootbase.service;

import com.base.springbootbase.common.core.domain.entity.SysRole;

import java.util.List;

/**
 * @author MrLu
 * @version 1.0
 * @description: TODO
 * @date 2025/7/29 3:09
 */
public interface SysPostService {

    /**
     * 根据条件分页查询角色数据
     *
     * @param role 角色信息
     * @return 角色数据集合信息
     */
    public List<SysRole> selectRoleList(SysRole role);
    /**
     * 查询所有角色
     *
     * @return 角色列表
     */
    List<SysRole> selectPostAll();
    /**
     * 根据用户ID获取岗位选择框列表
     *
     * @param userId 用户ID
     * @return 选中岗位ID列表
     */
    List<Long> selectPostListByUserId(Long userId);
}
