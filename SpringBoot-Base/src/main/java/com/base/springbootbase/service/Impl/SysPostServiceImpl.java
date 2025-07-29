package com.base.springbootbase.service.Impl;

import com.base.springbootbase.common.core.domain.entity.SysRole;
import com.base.springbootbase.common.util.spring.SpringUtils;
import com.base.springbootbase.mapper.SysPostMapper;
import com.base.springbootbase.mapper.SysRoleMapper;
import com.base.springbootbase.service.SysPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author MrLu
 * @version 1.0
 * @description:
 * @date 2025/7/29 3:09
 */
@Service
public class SysPostServiceImpl implements SysPostService {
    @Autowired
    private SysRoleMapper roleMapper;
    @Autowired
    private SysPostMapper postMapper;

    /**
     * 根据条件分页查询角色数据
     *
     * @param role 角色信息
     * @return 角色数据集合信息
     */
    @Override
    public List<SysRole> selectRoleList(SysRole role)
    {
        return roleMapper.selectRoleList(role);
    }
    @Override
    public List<SysRole> selectPostAll() {
        return selectRoleList(new SysRole());
        // TODO: 2025/7/29 绕过aop
//        return SpringUtils.getAopProxy(this).selectRoleList(new SysRole());
    }
    /**
     * 根据用户ID获取岗位选择框列表
     *
     * @param userId 用户ID
     * @return 选中岗位ID列表
     */
    @Override
    public List<Long> selectPostListByUserId(Long userId) {
        return postMapper.selectPostListByUserId(userId);
    }
}
