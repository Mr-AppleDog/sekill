package com.base.springbootbase.domain.vo;

import java.time.LocalDateTime;

/**
 * @author MrLu
 * @version 1.0
 * @description:
 * @date 2025/7/30 19:53
 */
public class SeKillGoodsDetailDTO {
    private Long goodsId;
    private String goodsName;
    private String goodsTitle;
    private String goodsImg;
    private String goodsDetail;
    private Double goodsPrice;

    // 秒杀信息
    private Double miaoshaPrice;
    private Integer stockCount;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
}
