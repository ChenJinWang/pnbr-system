package com.cjw.pet.service;

import com.cjw.pet.dto.response.*;

/**
 * @author cjw
 */
public interface StatisticsService {

    /**
     * 首页 top 统计
     * @return 统计数据
     */
    StatisticsTopVo getStatisticsTopVo();

//    /**
//     * 首页主题统计
//     * @return 年龄分布统计
//     */
//    List<AgeAnalysisVo> getAgeAnalysisVo();

    /**
     * 商品销售额排行
     * @return 销售额排行
     */
    PetSalesVo getPetSalesVo();



    /**
     * 商品销量排行
     * @return 销量排行
     */
    PetRankingVo getPetRankingVo();

    /**
     * 商品收藏排行
     * @return 收藏排行
     */
    PetComplaintVo getPetComplaintVo();

    /**
     * 用户消费额排行
     * @return 用户消费
     */
    UserConsumptionVo getUserConsumptionVo();

}
