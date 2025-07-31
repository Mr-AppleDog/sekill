package com.base.springbootbase.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.base.springbootbase.domain.entity.Goods;
import com.base.springbootbase.domain.entity.SeKillGoods;

import java.util.List;

/**
 * @author MrLu
 * @version 1.0
 * @description:  秒杀
 * @date 2025/7/30 17:50
 */
public interface SeKillGoodsService{


    /**
     * 获取秒杀商品信息
     * @param goodsId
     * @return
     */
    SeKillGoods getOneById(Long goodsId);

    /**
     * 秒杀检查库存
     * @param goodsId
     * @return
     */
    int checkKillStock(Long goodsId);

}
