package com.base.springbootbase.common.util;

import com.base.springbootbase.common.core.page.PageDomain;
import com.base.springbootbase.common.core.page.TableSupport;
import com.base.springbootbase.common.util.sql.SqlUtil;
import com.github.pagehelper.PageHelper;

/**
 * @author MrLu
 * @version 1.0
 * @description:
 * @date 2025/7/28 3:00
 */
public class PageUtils extends PageHelper {
    /**
     * 设置请求分页数据
     */
    public static void startPage()
    {
        PageDomain pageDomain = TableSupport.buildPageRequest();
        Integer pageNum = pageDomain.getPageNum();
        Integer pageSize = pageDomain.getPageSize();
        String orderBy = SqlUtil.escapeOrderBySql(pageDomain.getOrderBy());
        Boolean reasonable = pageDomain.getReasonable();
        PageHelper.startPage(pageNum, pageSize, orderBy).setReasonable(reasonable);
    }
}
