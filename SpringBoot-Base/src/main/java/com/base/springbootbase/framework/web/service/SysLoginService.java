package com.base.springbootbase.framework.web.service;

import com.base.springbootbase.common.constant.MessageConstant;
import com.base.springbootbase.common.exception.PasswordErrorException;
import com.base.springbootbase.common.core.domain.entity.SysUser;
import com.base.springbootbase.common.core.domain.model.LoginBody;
import com.base.springbootbase.common.core.domain.model.LoginUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

/**
 * @author MrLu
 * @version 1.0
 * @description:
 * @date 2025/7/26 1:13
 */
@Component
public class SysLoginService {

    @Autowired
    //SpringSecurit 暴露出的认证管理器
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    public String login(LoginBody loginBody) {
        Authentication authentication = null;
        //封装用户名密码
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginBody.getUsername(), loginBody.getPassword());
        try {
            // 验证成功后，会返回一个包含完整认证信息的 Authentication 对象
            authentication = authenticationManager.authenticate(authenticationToken);
        } catch (Exception e) {
            //校验失败
            if (e instanceof BadCredentialsException) {
                throw new PasswordErrorException(MessageConstant.PASSWORD_ERROR);
            } else {
                throw new RuntimeException(e.getMessage());
            }
        }
        System.out.println(authentication);
        // 获取登录用户
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        recordLoginInfo(loginUser.getUserId());
        return tokenService.createToken(loginUser);
    }

    /**
     * 记录登录信息
     * @param userId
     */
    public void recordLoginInfo(Long userId) {
        SysUser sysUser = new SysUser();
        sysUser.setUserId(userId);
        // TODO: 2025/7/26  完善记录登录信息
        System.out.println(sysUser);
//        sysUser.setLoginIp(ServletUtils.getClientIP());
//        sysUser.setLoginDate(DateUtils.getNowDate());
//        userService.updateUserProfile(sysUser);
    }
}
