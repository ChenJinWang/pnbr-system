package com.cjw.pet.dto.request;

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
@ApiModel("新增订单")
public class OrderBody extends Order {
    private List<OrderDetail> orderDetails;

}
