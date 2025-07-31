package com.base.springbootbase.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.base.springbootbase.domain.entity.Goods;
import com.base.springbootbase.domain.entity.SeKillOrders;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author MrLu
 * @version 1.0
 * @description:
 * @date 2025/7/30 17:51
 */
@Mapper
public interface SeKillOrderMapper extends BaseMapper<SeKillOrders> {


}
