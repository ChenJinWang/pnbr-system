package com.cjw.pet.dto.response;

import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * @author cjw
 */
@Data
@ApiModel("首页top统计")
public class StatisticsTopVo {
    private double saleroom;
    private Integer orderQuantity;
    private Integer numberGoods;
    private Integer categoryName;
    private Integer localDistribution;
    private Integer totalNumberRegistrants;
    private Integer newRegistrations;
}
