package com.cjw.pet.dao.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cjw.pet.dao.OrderDetailDao;
import com.cjw.pet.mapper.OrderDetailMapper;
import com.cjw.pet.pojo.OrderDetail;
import org.springframework.stereotype.Repository;

/**
 * @author cjw
 */
@Repository
public class OrderDetailDaoImpl extends ServiceImpl<OrderDetailMapper, OrderDetail> implements OrderDetailDao {
}
