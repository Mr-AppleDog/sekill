package com.base.springbootbase.service;

import com.base.springbootbase.common.core.domain.entity.SysDictData;

import java.util.List;

/**
 * @author MrLu
 * @version 1.0
 * @description: TODO
 * @date 2025/7/28 0:36
 */
public interface SysDictTypeService {
    /**
     * 根据字典类型查询字典数据
     *
     * @param dictType 字典类型
     * @return 字典数据集合信息
     */
    List<SysDictData> selectDictDataByType(String dictType);
}
