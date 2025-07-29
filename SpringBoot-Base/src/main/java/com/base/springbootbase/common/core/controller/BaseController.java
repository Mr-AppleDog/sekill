package com.base.springbootbase.common.core.controller;

import com.base.springbootbase.common.constant.HttpStatus;
import com.base.springbootbase.common.core.domain.model.LoginUser;
import com.base.springbootbase.common.core.page.TableDataInfo;
import com.base.springbootbase.common.result.Result;
import com.base.springbootbase.common.util.PageUtils;
import com.base.springbootbase.common.util.SecurityUtils;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @author MrLu
 * @version 1.0
 * @description:
 * @date 2025/7/27 23:05
 */
public class BaseController {

    /**
     * 获取登录用户id
     */
    public Long getUserId()
    {
        return getLoginUser().getUserId();
    }
    /**
     * 获取用户缓存信息
     */
    public LoginUser getLoginUser()
    {
        return SecurityUtils.getLoginUser();
    }
    /**
     * 响应返回结果
     *
     * @param rows 影响行数
     * @return 操作结果
     */
    protected Result toResult(int rows)
    {
        return rows > 0 ? Result.success() : Result.error();
    }
    /**
     * 获取登录用户名
     */
    public String getUsername()
    {
        return getLoginUser().getUsername();
    }

    /**
     * 设置请求分页数据
     */
    protected void startPage()
    {
        PageUtils.startPage();
    }
    /**
     * 响应请求分页数据
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    protected TableDataInfo getDataTable(List<?> list)
    {
        TableDataInfo rspData = new TableDataInfo();
        rspData.setCode(HttpStatus.SUCCESS);
        rspData.setMsg("查询成功");
        rspData.setRows(list);
        rspData.setTotal(new PageInfo(list).getTotal());
        return rspData;
    }
    /**
     * 返回成功
     */
    public Result success()
    {
        return Result.success();
    }

}
