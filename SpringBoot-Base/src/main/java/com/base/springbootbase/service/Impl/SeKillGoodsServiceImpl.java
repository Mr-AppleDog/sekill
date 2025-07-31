package com.base.springbootbase.service.Impl;

import com.base.springbootbase.domain.entity.SeKillGoods;
import com.base.springbootbase.mapper.GoodsMapper;
import com.base.springbootbase.mapper.SeKillGoodsMapper;
import com.base.springbootbase.service.SeKillGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author MrLu
 * @version 1.0
 * @description:
 * @date 2025/7/30 19:13
 */
@Service
public class SeKillGoodsServiceImpl implements SeKillGoodsService {
    @Autowired
    private SeKillGoodsMapper seKillGoodsMapper;
    @Autowired
    private GoodsMapper goodsMapper;
    /**
     * 根据商品id查询商品信息
     * @param goodsId
     * @return
     */
    @Override
    public SeKillGoods getOneById(Long goodsId) {
        //获取秒杀数据
        SeKillGoods one = seKillGoodsMapper.getById(goodsId);
        return one;
    }

    /**
     * 检查秒杀库存
     * @param goodsId
     * @return
     */
    @Override
    public int checkKillStock(Long goodsId) {
        SeKillGoods oneById = getOneById(goodsId);
        return oneById.getKillStock();
    }

}
