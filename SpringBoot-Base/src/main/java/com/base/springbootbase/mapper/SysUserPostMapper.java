package com.base.springbootbase.mapper;

import com.base.springbootbase.common.core.domain.entity.SysUserPost;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author MrLu
 * @version 1.0
 * @description: TODO
 * @date 2025/7/29 2:57
 */
@Mapper
public interface SysUserPostMapper {
    /**
     * 批量新增用户岗位信息
     *
     * @param userPostList 用户角色列表
     * @return 结果
     */
    int batchUserPost(List<SysUserPost> userPostList);
    /**
     * 通过用户ID删除用户和岗位关联
     *
     * @param userId 用户ID
     * @return 结果
     */
    void deleteUserPostByUserId(Long userId);
    /**
     * 批量删除用户和岗位关联
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    void deleteUserPost(Long[] ids);
}
