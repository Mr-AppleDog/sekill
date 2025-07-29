package com.base.springbootbase.service.Impl;

import com.base.springbootbase.common.core.domain.entity.SysDictData;
import com.base.springbootbase.common.util.DictUtils;
import com.base.springbootbase.common.util.StringUtils;
import com.base.springbootbase.mapper.SysDictDataMapper;
import com.base.springbootbase.service.SysDictTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author MrLu
 * @version 1.0
 * @description:
 * @date 2025/7/28 0:38
 */
@Service
public class SysDictTypeImpl implements SysDictTypeService {
    @Autowired
    private SysDictDataMapper dictDataMapper;
    @Override
    public List<SysDictData> selectDictDataByType(String dictType) {
        // TODO: 2025/7/28 取消缓存
//        List<SysDictData> dictDatas = DictUtils.getDictCache(dictType);
//        if (StringUtils.isNotEmpty(dictDatas))
//        {
//            return dictDatas;
//        }
        List<SysDictData> dictDatas= null;
        dictDatas = dictDataMapper.selectDictDataByType(dictType);
        if (StringUtils.isNotEmpty(dictDatas))
        {
            // TODO: 2025/7/28 取消设置缓存
//            DictUtils.setDictCache(dictType, dictDatas);
            return dictDatas;
        }
        return null;
    }
}
