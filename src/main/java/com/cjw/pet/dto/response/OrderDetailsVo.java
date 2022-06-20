package com.cjw.pet.dto.response;

import com.cjw.pet.pojo.Order;
import com.cjw.pet.pojo.OrderDetail;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @author cjw
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel("订单详情")
public class OrderDetailsVo extends Order {
    private List<OrderDetail> orderDetails;
    private String sellerName;
}
