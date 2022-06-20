package com.cjw.pet.dao.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cjw.pet.dao.CartDao;
import com.cjw.pet.mapper.CartMapper;
import com.cjw.pet.pojo.Cart;
import org.springframework.stereotype.Repository;

/**
 * @author cjw
 */
@Repository
public class CartDaoImpl extends ServiceImpl<CartMapper, Cart> implements CartDao {
}
