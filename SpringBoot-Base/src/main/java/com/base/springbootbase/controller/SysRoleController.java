package com.base.springbootbase.controller;

import com.base.springbootbase.common.constant.UserConstants;
import com.base.springbootbase.common.core.controller.BaseController;
import com.base.springbootbase.common.core.domain.entity.SysRole;
import com.base.springbootbase.common.core.domain.entity.SysUser;
import com.base.springbootbase.common.core.domain.entity.SysUserRole;
import com.base.springbootbase.common.core.domain.model.LoginUser;
import com.base.springbootbase.common.core.page.TableDataInfo;
import com.base.springbootbase.common.result.Result;
import com.base.springbootbase.common.util.StringUtils;
import com.base.springbootbase.framework.web.service.SysPermissionService;
import com.base.springbootbase.framework.web.service.TokenService;
import com.base.springbootbase.service.SysRoleService;
import com.base.springbootbase.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author MrLu
 * @version 1.0
 * @description:
 * @date 2025/7/28 2:56
 */
@RestController
@RequestMapping("/system/role")
public class SysRoleController extends BaseController {
    @Autowired
    private SysRoleService roleService;

    @Autowired
    private SysPermissionService permissionService;

    @Autowired
    private SysUserService userService;
    @Autowired
    private TokenService tokenService;

    @GetMapping("/list")
    public TableDataInfo list(SysRole  role){
        System.out.println("成功");
        System.out.println(role);
        startPage();
        List<SysRole> list = roleService.selectRoleList(role);
        return getDataTable(list);
    }
    /**
     * 根据角色编号获取详细信息
     */
    @GetMapping("/{roleId}")
    public Result getInfo(@PathVariable Long roleId) {
        roleService.checkRoleDataScope(roleId);
        return Result.success(roleService.selectRoleById(roleId));
    }
    /**
     * 状态修改
     */
    @PutMapping("/changeStatus")
    public Result changeStatus(@RequestBody SysRole role){
        roleService.checkRoleAllowed(role);
        roleService.checkRoleDataScope(role.getRoleId());
        role.setUpdateBy(getUsername());
        return toResult(roleService.updateRoleStatus(role));
    }
    /**
     * 修改保存角色
     */
    @PutMapping
    public Result edit(@Validated @RequestBody SysRole role) {
        roleService.checkRoleAllowed(role);
        roleService.checkRoleDataScope(role.getRoleId());
        if(UserConstants.NOT_UNIQUE.equals(roleService.checkRoleNameUnique(role))){
            return Result.error("修改角色'" + role.getRoleName() + "'失败，角色名称已存在");
        }else if(UserConstants.NOT_UNIQUE.equals(roleService.checkRoleKeyUnique(role))){
            return Result.error("修改角色'" + role.getRoleName() + "'失败，角色权限已存在");
        }
        role.setUpdateBy(getUsername());
        if(roleService.updateRole(role)>0){
            // 更新缓存用户权限
            LoginUser loginUser=getLoginUser();
            if(StringUtils.isNotNull(loginUser.getUser()) && !loginUser.getUser().isAdmin()){
                loginUser.setPermissions(permissionService.getMenuPermission(loginUser.getUser()));
                loginUser.setUser(userService.selectUserByUserName(loginUser.getUser().getUserName()));
                tokenService.setLoginUser(loginUser);
            }
            return Result.success();
        }
        return Result.error("修改角色'" + role.getRoleName() + "'失败，请联系管理员");
    }
    /**
     * 新增角色
     */
    @PostMapping
    public Result add(@Validated @RequestBody SysRole role){
        if(UserConstants.NOT_UNIQUE.equals(roleService.checkRoleNameUnique(role))){
            return Result.error("新增角色'" + role.getRoleName() + "'失败，角色名称已存在");
        } else if (UserConstants.NOT_UNIQUE.equals(roleService.checkRoleKeyUnique(role))) {
            return Result.error("新增角色'" + role.getRoleName() + "'失败，角色权限已存在");
        }
        role.setCreateBy(getUsername());
        return toResult(roleService.insertRole(role));
    }
    /**
     * 删除角色
     */
    @DeleteMapping("/{roleIds}")
    public Result remove(@PathVariable Long[] roleIds){
       return toResult(roleService.deleteRoleByIds(roleIds));
    }
    /**
     * 查询已分配用户角色列表
     */
    @GetMapping("/authUser/allocatedList")
    public TableDataInfo allocatedList(SysUser user){
        startPage();
        List<SysUser> list = userService.selectAllocatedList(user);
        return getDataTable(list);
    }
    /**
     * 查询未分配用户角色列表
     */
    @GetMapping("/authUser/unallocatedList")
    public TableDataInfo unallocatedList(SysUser user){
        startPage();
        List<SysUser> list = userService.selectUnallocatedList(user);
        return getDataTable(list);
    }
    /**
     * 批量选择用户授权
     */
    @PutMapping("/authUser/selectAll")
    public Result selectAuthUserAll(Long roleId, Long[] userIds){
        roleService.checkRoleDataScope(roleId);
        return toResult(roleService.insertAuthUsers(roleId, userIds));
    }
    /**
     * 取消授权用户
     */
    @PutMapping("/authUser/cancel")
    public Result cancelAuthUser(@RequestBody SysUserRole userRole){
        return toResult(roleService.deleteAuthUser(userRole));
    }

    /**
     * 批量取消授权用户
     */
    @PutMapping("/authUser/cancelAll")
    public Result cancelAuthUserAll(Long roleId, Long[] userIds){
        return toResult(roleService.deleteAuthUsers(roleId, userIds));
    }
}
