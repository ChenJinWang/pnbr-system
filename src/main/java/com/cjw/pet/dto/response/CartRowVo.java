package com.cjw.pet.dto.response;

import com.cjw.pet.pojo.Cart;
import com.cjw.pet.pojo.User;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.util.List;

/**
 * @author cjw
 */
@Data
@ApiModel("购物车一行数据")
public class CartRowVo {
    private User user;
    private List<Cart> carts;
    private Boolean isAgent;
    /**
     * 后台代理用户id
     */
    private Long backgroundAgentId;
}
