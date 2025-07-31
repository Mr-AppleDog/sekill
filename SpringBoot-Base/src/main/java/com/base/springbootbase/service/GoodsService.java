package com.base.springbootbase.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.base.springbootbase.domain.entity.Goods;

import java.util.List;

/**
 * @author MrLu
 * @version 1.0
 * @description: TODO
 * @date 2025/7/30 17:50
 */
public interface GoodsService extends IService<Goods> {

    /**
     * 获取推荐页列表
     * @return
     */
    List<Goods> recommendList();

}
