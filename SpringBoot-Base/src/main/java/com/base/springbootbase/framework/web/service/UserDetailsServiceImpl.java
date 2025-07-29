package com.base.springbootbase.framework.web.service;

import cn.hutool.core.util.ObjectUtil;
import com.base.springbootbase.common.enums.UserStatus;
import com.base.springbootbase.common.exception.ServiceException;
import com.base.springbootbase.common.core.domain.entity.SysUser;
import com.base.springbootbase.common.core.domain.model.LoginUser;
import com.base.springbootbase.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsPasswordService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;

/**
 * @author MrLu
 * @version 1.0
 * @description: 自定义用户登录逻辑
 * @date 2025/7/26 0:01
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsManager, UserDetailsPasswordService {

    @Autowired
    private SysUserService userService;
    @Autowired
    private SysPermissionService permissionService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUser user = userService.selectUserByUserName(username);
        if (ObjectUtil.isEmpty(user)) {
            throw new ServiceException("登录用户：" + username + " 不存在");
        }
        else if(UserStatus.DELETED.getCode().equals(user.getDelFlag())){
            throw new ServiceException("对不起，您的账号：" + username + " 已被删除");
        }
        else if (UserStatus.DISABLE.getCode().equals(user.getStatus())) {
            throw new ServiceException("对不起，您的账号：" + username + " 已停用");
        }

        // 返回一个UserDetails 对象 对user 进行封装
        return createLoginUser(user);
    }
    public UserDetails createLoginUser(SysUser user){
//        return new LoginUser(user.getUserId(), user.getDeptId(), user, permissionService.getMenuPermission(user));
        return new LoginUser(user.getUserId(), user.getDeptId(), user, permissionService.getMenuPermission( user));
//        new org.springframework.security.core.userdetails.User()
    }

    @Override
    public UserDetails updatePassword(UserDetails user, String newPassword) {
        return null;
    }

    @Override
    public void createUser(UserDetails user) {

    }

    @Override
    public void updateUser(UserDetails user) {

    }

    @Override
    public void deleteUser(String username) {

    }

    @Override
    public void changePassword(String oldPassword, String newPassword) {

    }

    @Override
    public boolean userExists(String username) {
        return false;
    }
}
