package com.base.springbootbase.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.base.springbootbase.domain.entity.Goods;
import com.base.springbootbase.mapper.GoodsMapper;
import com.base.springbootbase.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author MrLu
 * @version 1.0
 * @description:
 * @date 2025/7/30 17:50
 */
@Service
public class GoodsServiceImpl extends ServiceImpl<GoodsMapper, Goods> implements GoodsService {
    @Autowired
    private GoodsMapper goodsMapper;
    /**
     * 获取推荐页列表
     * @return
     */
    @Override
    public List<Goods> recommendList() {
        List<Goods> list =goodsMapper.list();
        return list;
    }

}
