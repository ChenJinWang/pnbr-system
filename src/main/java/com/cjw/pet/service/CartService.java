package com.cjw.pet.service;

import com.cjw.pet.dto.response.CartRowVo;
import com.cjw.pet.pojo.Cart;
import com.cjw.pet.pojo.PageDomain;
import com.cjw.pet.pojo.PageList;

/**
 * @author cjw
 */
public interface CartService {

    /**
     * 添加购物车
     * @param cart 参数
     * @return id
     */
    Long addCart(Cart cart);

    /**
     * 修改购物车中的数据
     * @param cart 参数
     * @return 结果
     */
    Boolean updateCart(Cart cart);

    /**
     * 删除购物车数据
     * @param id 购物车id
     * @return 结果
     */
    Boolean deletedCart(Long id);

    /**
     * 分页查询购物车
     * @param pageDomain 分页数据
     * @return 购物车数据
     */
    PageList<CartRowVo> listCart(PageDomain pageDomain);

    /**
     * 获取商品的购物详情
     * @param id
     * @return
     */
    Cart getCartDetail(Long id);

    /**
     * 获取商品的购物详情
     * @param skuId
     * @return
     */
    Cart getCartDetailBySkuId(Long skuId);

    /**
     * 获取购物车数量
     * @return 数量
     */
    Integer count();

}
