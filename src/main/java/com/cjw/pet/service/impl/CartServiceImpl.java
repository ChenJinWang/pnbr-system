package com.cjw.pet.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cjw.pet.dao.CartDao;
import com.cjw.pet.dao.PetDao;
import com.cjw.pet.dao.UserDao;
import com.cjw.pet.mapper.CartMapper;
import com.cjw.pet.pojo.Cart;
import com.cjw.pet.pojo.PageDomain;
import com.cjw.pet.pojo.PageList;
import com.cjw.pet.pojo.User;
import com.cjw.pet.service.CartService;
import com.cjw.pet.dto.response.CartRowVo;
import com.cjw.pet.exception.ExceptionResult;
import com.cjw.pet.pojo.*;
import com.cjw.pet.utils.ServletUtils;
import com.cjw.pet.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author cjw
 */
@Service
public class CartServiceImpl implements CartService {

    private final CartDao cartDao;
    private final CartMapper cartMapper;
    private final PetDao petDao;
    private final UserDao userDao;

    @Autowired
    private UserUtils userUtils;

    public CartServiceImpl(CartDao cartDao, CartMapper cartMapper, PetDao petDao, UserDao userDao) {
        this.cartDao = cartDao;
        this.cartMapper = cartMapper;
        this.petDao = petDao;
        this.userDao = userDao;
    }

    @Override
    public Long addCart(Cart cart) {
        User user = userUtils.getUser(ServletUtils.getRequest());
        if (Objects.isNull(user)) {
            throw new ExceptionResult("user","false",null,"请先登陆");
        }
        cart.setUserId(user.getId());
        Integer count1 = petDao.lambdaQuery().eq(Pet::getId, cart.getSkuId()).eq(Pet::getCreateId, user.getId()).count();
        if (count1>0) {
            throw new ExceptionResult("cart","false",null,"不能添加自己发布的商品");
        }
        Integer count = cartDao.lambdaQuery().eq(Cart::getSkuId, cart.getSkuId()).eq(Cart::getUserId,user.getId()).count();
        if (count==0) {
            if(!cartDao.save(cart)) {
                throw new ExceptionResult("cart","false",null,"添加失败");
            }
        }else {
            if(!cartDao.lambdaUpdate().eq(Cart::getSkuId,cart.getSkuId()).eq(Cart::getUserId,user.getId()).update(cart)) {
                throw new ExceptionResult("cart","false",null,"添加失败");
            }
        }
        return cart.getId();
    }

    @Override
    public Boolean updateCart(Cart cart) {
        User user = userUtils.getUser(ServletUtils.getRequest());
        if (Objects.isNull(user)) {
            throw new ExceptionResult("user","false",null,"请先登陆");
        }
        if (!cartDao.lambdaUpdate().eq(Cart::getId,cart.getId()).eq(Cart::getUserId,user.getId()).update(cart)) {
            throw new ExceptionResult("cart","false",null,"修改失败");
        }
        return true;
    }

    @Override
    public Boolean deletedCart(Long id) {
        User user = userUtils.getUser(ServletUtils.getRequest());
        if (Objects.isNull(user)) {
            throw new ExceptionResult("user","false",null,"请先登陆");
        }
        if(cartMapper.deleteById(id)==0) {
            throw new ExceptionResult("cart","false",null,"删除失败");
        }
        return true;
    }

    @Override
    public PageList<CartRowVo> listCart(PageDomain pageDomain) {
        User user = userUtils.getUser(ServletUtils.getRequest());
        if (Objects.isNull(user)) {
            throw new ExceptionResult("user","false",null,"请先登陆");
        }
        Page<Cart> page = cartDao.lambdaQuery().eq(Cart::getUserId,user.getId()).page(new Page<>(pageDomain.getPageNum(), pageDomain.getPageSize()));
        List<Cart> carts = page.getRecords();
        List<CartRowVo> cartRowVos = new ArrayList<>();
        Map<Long, List<Cart>> collect = carts.parallelStream().collect(Collectors.groupingBy(Cart::getSellerId));
        Iterator<Map.Entry<Long, List<Cart>>> entries = collect.entrySet().iterator();
        while(entries.hasNext()){
            Map.Entry<Long, List<Cart>> entry = entries.next();
            Map<Long, List<Cart>> collect2 = entry.getValue().parallelStream().collect(Collectors.groupingBy(Cart::getBackgroundAgentId));
            Iterator<Map.Entry<Long, List<Cart>>> entries2 = collect2.entrySet().iterator();
            while(entries2.hasNext()){
                Map.Entry<Long, List<Cart>> entry2 = entries2.next();
                User user1 = userDao.getById(entry.getKey());
                User user2 = new User();
                CartRowVo cartRowVo = new CartRowVo();
                if (Objects.isNull(user1)) {
                    user2.setUsername("");
                }
                if (entry2.getKey()>0) {
                    cartRowVo.setIsAgent(true);
                    cartRowVo.setBackgroundAgentId(entry2.getKey());
                }else {
                    cartRowVo.setIsAgent(false);
                }
                user2.setUsername(user1.getUsername());
                user2.setId(user1.getId());
                user2.setAvatarImage(user1.getAvatarImage());
                cartRowVo.setUser(user2);
                cartRowVo.setCarts(entry2.getValue());
                cartRowVos.add(cartRowVo);
            }
        }

        return PageList.of(cartRowVos, page);
    }

    @Override
    public Cart getCartDetail(Long id) {
        return cartDao.getById(id);
    }

    @Override
    public Cart getCartDetailBySkuId(Long skuId) {
        return cartDao.lambdaQuery().eq(Cart::getSkuId,skuId).one();
    }

    @Override
    public Integer count() {
        User user = userUtils.getUser(ServletUtils.getRequest());
        if (Objects.isNull(user)) {
            throw new ExceptionResult("user","false",null,"请先登陆");
        }
        return cartDao.lambdaQuery().eq(Cart::getUserId,user.getId()).count();
    }
}
