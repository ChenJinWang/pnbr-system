package com.cjw.pet.controller;

import com.cjw.pet.dto.response.*;
import com.cjw.pet.service.StatisticsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 数据统计
 *
 * @author cjw
 */
@Slf4j
@Api(tags = "数据统计")
@RestController
@RequestMapping("/statistics")
public class StatisticsController {
    private final StatisticsService statisticsService;

    public StatisticsController(StatisticsService statisticsService) {
        this.statisticsService = statisticsService;
    }

    /**
     * 首页 top 统计
     * @return 统计数据
     */
    @GetMapping("/top")
    @ApiOperation("首页 top 统计")
    public StatisticsTopVo getStatisticsTopVo() {
        return statisticsService.getStatisticsTopVo();
    }

//    /**
//     * 首页年龄分布统计
//     * @return 年龄分布统计
//     */
//    @GetMapping("/ageAnalysis")
//    @ApiOperation("首页年龄分布统计")
//    public List<AgeAnalysisVo> getAgeAnalysisVo() {
//        return statisticsService.getAgeAnalysisVo();
//    }

    /**
     * 商品销售额排行
     * @return 销售额排行
     */
    @GetMapping("/petSales")
    @ApiOperation("商品销售额排行")
    public PetSalesVo getPetSalesVo() {
        return statisticsService.getPetSalesVo();
    }

    /**
     * 最受欢迎的商品排行
     * @return 商品排行
     */
    @GetMapping("/petRanking")
    @ApiOperation("商品销量排行")
    public PetRankingVo getPetRankingVo() {
        return statisticsService.getPetRankingVo();
    }

    /**
     * 商品收藏排行
     * @return 收藏排行
     */
    @GetMapping("/petComplaint")
    @ApiOperation("商品收藏排行")
    public PetComplaintVo getPetComplaintVo() {
        return statisticsService.getPetComplaintVo();
    }

    /**
     * 用户地区分布
     * @return 地区分布
     */
    @GetMapping("/userConsumption")
    @ApiOperation("用户消费额排行")
    public UserConsumptionVo getUserConsumptionVo() {
        return statisticsService.getUserConsumptionVo();
    }

}
