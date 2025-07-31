package com.base.springbootbase.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.base.springbootbase.domain.entity.SeKillGoods;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author MrLu
 * @version 1.0
 * @description:
 * @date 2025/7/30 17:51
 */
@Mapper
public interface SeKillGoodsMapper extends BaseMapper<SeKillGoods> {

    /**
     * 获取秒杀商品信息
     * @param goodsId
     * @return
     */
    SeKillGoods getById(Long goodsId);
}
