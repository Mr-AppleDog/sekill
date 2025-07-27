package com.base.springbootbase.service;

import com.base.springbootbase.domain.entity.SysMenu;
import com.base.springbootbase.domain.vo.RouterVo;

import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * @author MrLu
 * @version 1.0
 * @description:
 * @date 2025/7/26 19:05
 */
public interface SysMenuService {

    /**
     * 根据角色ID查询权限
     *
     * @param roleId 角色ID
     * @return 权限列表
     */
    Set<String> selectMenuPermsByRoleId(Long roleId);

    /**
     * 根据用户ID查询权限
     *
     * @param userId 用户ID
     * @return 权限列表
     */
    Set<String> selectMenuPermsByUserId(Long userId);

    /**
     * 根据用户ID查询菜单树信息
     *
     * @param userId 用户ID
     * @return 菜单列表
     */
    List<SysMenu> selectMenuTreeByUserId(Long userId);
    /**
     * 构建前端路由所需要的菜单
     *
     * @param menus 菜单列表
     * @return 路由列表
     */
    public List<RouterVo> buildMenus(List<SysMenu> menus);
}
