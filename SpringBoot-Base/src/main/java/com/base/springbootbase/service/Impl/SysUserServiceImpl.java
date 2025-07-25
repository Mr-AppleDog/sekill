package com.base.springbootbase.service.Impl;

import com.base.springbootbase.domain.entity.SysUser;
import com.base.springbootbase.mapper.SysUserMapper;
import com.base.springbootbase.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author MrLu
 * @version 1.0
 * @description:
 * @date 2025/7/26 0:04
 */
@Service
public class SysUserServiceImpl implements SysUserService {
    @Autowired
    private SysUserMapper userMapper;
    @Override
    public SysUser selectUserByUserName(String userName) {
        return userMapper.selectUserByUserName(userName);
    }
}
