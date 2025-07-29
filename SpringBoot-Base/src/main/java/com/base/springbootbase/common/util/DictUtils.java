package com.base.springbootbase.common.util;

import com.alibaba.fastjson2.JSONArray;
import com.base.springbootbase.common.constant.CacheConstants;
import com.base.springbootbase.common.core.domain.entity.SysDictData;
import com.base.springbootbase.common.util.spring.SpringUtils;

import java.util.List;

/**
 * @author MrLu
 * @version 1.0
 * @description: 字典工具类
 * @date 2025/7/28 0:40
 */
public class DictUtils {
    /**
     * 获取字典缓存
     *
     * @param key 参数键
     * @return dictDatas 字典数据列表
     */
    public static List<SysDictData> getDictCache(String key)
    {
        JSONArray arrayCache = SpringUtils.getBean(RedisCache.class).getCacheObject(getCacheKey(key));
        if (StringUtils.isNotNull(arrayCache))
        {
            return arrayCache.toList(SysDictData.class);
        }
        return null;
    }
    /**
     * 设置字典缓存
     *
     * @param key 参数键
     * @param dictDatas 字典数据列表
     */
    public static void setDictCache(String key, List<SysDictData> dictDatas)
    {
        SpringUtils.getBean(RedisCache.class).setCacheObject(getCacheKey(key), dictDatas);
    }
    /**
     * 设置cache key
     *
     * @param configKey 参数键
     * @return 缓存键key
     */
    public static String getCacheKey(String configKey)
    {
        return CacheConstants.SYS_DICT_KEY + configKey;
    }
}
