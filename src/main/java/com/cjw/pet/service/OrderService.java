package com.cjw.pet.service;

import com.cjw.pet.dto.request.OrderListBody;
import com.cjw.pet.dto.request.OrderQuery;
import com.cjw.pet.dto.response.OrderDetailsVo;
import com.cjw.pet.dto.response.OrderRowVo;
import com.cjw.pet.pojo.PageList;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author cjw
 */
public interface OrderService {

    /**
     * 创建订单
     *
     * @param orderListBody
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    List<String> createOrder(OrderListBody orderListBody);

    /**
     * 根据订单id查询订单
     *
     * @param id
     * @return
     */
    OrderDetailsVo queryById(String id);

    /**
     * 分页查询登录用户订单
     *
     * @param page
     * @param rows
     * @param status
     * @return
     */
    PageList<OrderRowVo> queryUserOrderList(Integer page, Integer rows, Integer status);

    /**
     * 后台加载订单数据
     * @param query 参数
     * @return 订单数据
     */
    PageList<OrderRowVo> queryOrderList(OrderQuery query);


    /**
     * 更新订单状态
     *
     * @param id
     * @param status
     * @return
     */
    Boolean updateStatus(String id, Integer status);

    /**
     * 获取订单数
     * @return 订单数
     */
    Integer getOrderCount();

    /**
     * 删除订单
     * @param id 订单id
     * @return 结果
     */
    Boolean deletedOrder(String id);

}
