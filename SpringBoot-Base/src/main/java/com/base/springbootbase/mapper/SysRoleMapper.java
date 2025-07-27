package com.base.springbootbase.mapper;

import com.base.springbootbase.domain.entity.SysRole;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author MrLu
 * @version 1.0
 * @description:
 * @date 2025/7/27 15:55
 */
@Mapper
public interface SysRoleMapper {
    /**
     * 根据用户ID查询角色
     *
     * @param userId 用户ID
     * @return 角色列表
     */
    public List<SysRole> selectRolePermissionByUserId(Long userId);
}
