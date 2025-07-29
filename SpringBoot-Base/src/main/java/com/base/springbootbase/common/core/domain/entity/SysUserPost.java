package com.base.springbootbase.common.core.domain.entity;

/**
 * @author MrLu
 * @version 1.0
 * @description: 用户和岗位关联 sys_user_post
 * @date 2025/7/29 2:53
 */
public class SysUserPost {
    /** 用户ID */
    private Long userId;

    /** 岗位ID */
    private Long postId;

    public Long getUserId()
    {
        return userId;
    }

    public void setUserId(Long userId)
    {
        this.userId = userId;
    }

    public Long getPostId()
    {
        return postId;
    }

    public void setPostId(Long postId)
    {
        this.postId = postId;
    }
}
