package com.base.springbootbase.mapper;

import com.base.springbootbase.domain.entity.SysUser;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author MrLu
 * @version 1.0
 * @description: TODO
 * @date 2025/7/26 0:05
 */
@Mapper
public interface SysUserMapper {


    /**
     * 根据用户名查询用户
     * @param userName 用户名
     * @return 用户对象信息
     */
    SysUser selectUserByUserName(String userName);
}
