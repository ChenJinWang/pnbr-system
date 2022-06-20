package com.cjw.pet.dto.response;

import com.cjw.pet.pojo.*;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @author cjw
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel("订单一行数据")
public class OrderRowVo extends Order {
    private List<OrderDetail> orderDetails;
    private List<BackgroundUser> backgroundUsers;
    private String sellerName;
}
