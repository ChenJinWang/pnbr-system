package com.cjw.pet.dto.request;

import com.cjw.pet.pojo.PageDomain;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author cjw
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel("查询订单")
public class OrderQuery extends PageDomain {
    @ApiModelProperty("订单id")
    private String orderId;
    @ApiModelProperty("订单状态")
    private Integer status;
    @ApiModelProperty("商品类型")
    private Integer type;
    @ApiModelProperty("验证商家手机")
    private String phone;
}
