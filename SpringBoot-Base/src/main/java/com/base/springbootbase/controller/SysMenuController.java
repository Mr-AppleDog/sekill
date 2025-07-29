package com.base.springbootbase.controller;

import com.base.springbootbase.common.constant.UserConstants;
import com.base.springbootbase.common.core.controller.BaseController;
import com.base.springbootbase.common.result.Result;
import com.base.springbootbase.common.core.domain.entity.SysMenu;
import com.base.springbootbase.common.util.StringUtils;
import com.base.springbootbase.service.SysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.base.springbootbase.common.util.SecurityUtils.getUserId;

/**
 * @author MrLu
 * @version 1.0
 * @description: 菜单管理
 * @date 2025/7/27 22:54
 */
@RestController
@RequestMapping("/system/menu")
public class SysMenuController extends BaseController {
    @Autowired
    private SysMenuService menuService;

    /**
     * 获取菜单列表
     */
    @GetMapping("/list")
    public Result list(SysMenu menu) {
        List<SysMenu> menus = menuService.selectMenuList(menu, getUserId());
        return Result.success(menus);
    }

    /**
     * 根据菜单编号获取详细信息
     */
    @GetMapping("/{menuId}")
    public Result getInfo(@PathVariable Long menuId) {
        return Result.success(menuService.selectMenuById(menuId));
    }
    /**
     * 加载对应角色菜单列表树
     */
    @GetMapping("/roleMenuTreeselect/{roleId}")
    public Result roleMenuTreeselect(@PathVariable("roleId") Long roleId) {
        List<SysMenu> menus = menuService.selectMenuList(getUserId());
        Result result=Result.success();
        result.put("checkedKeys", menuService.selectMenuListByRoleId(roleId));
        result.put("menus", menuService.buildMenuTreeSelect(menus));
        return result;
    }
    /**
     * 获取菜单下拉树列表
     */
    @GetMapping("/treeselect")
    public Result treeselect(SysMenu menu) {
        List<SysMenu> menus = menuService.selectMenuList(getUserId());
        return Result.success(menuService.buildMenuTreeSelect(menus));
    }

    /**
     * 修改菜单
     */
    @PutMapping
    public Result edit(@Validated @RequestBody SysMenu menu) {
        if (UserConstants.NOT_UNIQUE.equals(menuService.checkMenuNameUnique(menu))) {
            return Result.error("修改菜单'" + menu.getMenuName() + "'失败，菜单名称已存在");
        } else if (UserConstants.YES_FRAME.equals(menu.getIsFrame()) && !StringUtils.ishttp(menu.getPath())) {
            return Result.error("修改菜单'" + menu.getMenuName() + "'失败，地址必须以http(s)://开头");
        } else if (menu.getMenuId().equals(menu.getParentId())) {
            return Result.error("修改菜单'" + menu.getMenuName() + "'失败，上级菜单不能选择自己");
        }
        menu.setUpdateBy(getUsername());
        return toResult(menuService.updateMenu(menu));
    }
    /**
     * 删除菜单
     */
    @DeleteMapping("/{menuId}")
    public Result remove(@PathVariable("menuId") Long menuId) {
        if(menuService.hasChildByMenuId(menuId)){
            return Result.error("存在子菜单,不允许删除");
        }
        if(menuService.checkMenuExistRole(menuId)){
            return Result.error("菜单已分配,不允许删除");
        }
        return toResult(menuService.deleteMenuById(menuId));
    }
    /**
     * 新增菜单
     */
    @PostMapping
    public Result add(@Validated @RequestBody SysMenu menu) {
        if(UserConstants.NOT_UNIQUE.equals(menuService.checkMenuNameUnique(menu))){
            return Result.error("新增菜单'" + menu.getMenuName() + "'失败，菜单名称已存在");
        } else if (UserConstants.YES_FRAME.equals (menu.getIsFrame()) && !StringUtils.ishttp(menu.getPath())) {
            return Result.error("新增菜单'" + menu.getMenuName() + "'失败，地址必须以http(s)://开头");
        }
        menu.setCreateBy(getUsername());
        return toResult(menuService.insertMenu(menu));
    }

}
