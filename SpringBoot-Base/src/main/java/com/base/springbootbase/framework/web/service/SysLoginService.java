package com.base.springbootbase.framework.web.service;

import com.base.springbootbase.domain.model.LoginBody;
import com.base.springbootbase.domain.model.LoginUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
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
    private AuthenticationManager authenticationManager;
    public String login(LoginBody loginBody) {
        Authentication authentication = null;
        UsernamePasswordAuthenticationToken  authenticationToken  = new UsernamePasswordAuthenticationToken(loginBody.getUsername(), loginBody.getPassword());
        authentication=authenticationManager.authenticate(authenticationToken);
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        System.out.println(loginUser);
        return "登录成功";
    }
}
