package com.cjw.pet.dao.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cjw.pet.dao.OrderDao;
import com.cjw.pet.mapper.OrderMapper;
import com.cjw.pet.pojo.Order;
import org.springframework.stereotype.Repository;

/**
 * @author cjw
 */
@Repository
public class OrderDaoImpl extends ServiceImpl<OrderMapper, Order> implements OrderDao {
}
