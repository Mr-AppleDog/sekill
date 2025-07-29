package com.base.springbootbase.controller;

import com.base.springbootbase.common.core.controller.BaseController;
import com.base.springbootbase.common.core.domain.entity.SysDictData;
import com.base.springbootbase.common.result.Result;
import com.base.springbootbase.common.util.StringUtils;
import com.base.springbootbase.service.SysDictTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author MrLu
 * @version 1.0
 * @description:
 * @date 2025/7/28 0:29
 */
@RestController
@RequestMapping("/system/dict/data")
public class SysDictDataController extends BaseController {
    @Autowired
    private SysDictTypeService dictTypeService;

    @GetMapping(value = "/type/{dictType}")
    public Result dictType(@PathVariable String dictType){
        List<SysDictData> data = dictTypeService.selectDictDataByType(dictType);
        if (StringUtils.isNull(data))
        {
            data = new ArrayList<SysDictData>();
        }
        return Result.success(data);
    }
}
