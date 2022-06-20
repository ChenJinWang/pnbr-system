package com.cjw.pet.service.impl;

import com.cjw.pet.dao.AddressDao;
import com.cjw.pet.mapper.AddressMapper;
import com.cjw.pet.pojo.Address;
import com.cjw.pet.pojo.User;
import com.cjw.pet.service.AddressService;
import com.cjw.pet.utils.ServletUtils;
import com.cjw.pet.utils.UserUtils;
import com.cjw.pet.exception.ExceptionResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * @author cjw
 */
@Service
public class AddressServiceImpl implements AddressService {

    private final AddressDao addressDao;
    private final AddressMapper addressMapper;
    @Autowired
    private UserUtils userUtils;

    public AddressServiceImpl(AddressDao addressDao, AddressMapper addressMapper) {
        this.addressDao = addressDao;
        this.addressMapper = addressMapper;
    }

    @Override
    public Long addAddress(Address address) {
        User user = userUtils.getUser(ServletUtils.getRequest());
        if (Objects.isNull(user)) {
            throw new ExceptionResult("Address","false",null,"请先登陆");
        }
        address.setUid(user.getId());
        if(!addressDao.save(address)) {
            throw new ExceptionResult("Address","false",null,"添加失败");
        }
        return address.getId();
    }

    @Override
    public Boolean updateAddress(Address address) {
        User user = userUtils.getUser(ServletUtils.getRequest());
        if (Objects.isNull(user)) {
            throw new ExceptionResult("Address","false",null,"请先登陆");
        }
        if (!addressDao.updateById(address)) {
            throw new ExceptionResult("Address","false",null,"修改失败");
        }
        return true;
    }

    @Override
    public Boolean deletedAddress(Long id) {
        User user = userUtils.getUser(ServletUtils.getRequest());
        if (Objects.isNull(user)) {
            throw new ExceptionResult("Address","false",null,"请先登陆");
        }
        if (addressMapper.deleteById(id)==0) {
            throw new ExceptionResult("Address","false",null,"删除失败");
        }
        return true;
    }

    @Override
    public List<Address> listAddress() {
        User user = userUtils.getUser(ServletUtils.getRequest());
        if (Objects.isNull(user)) {
            throw new ExceptionResult("Address","false",null,"请先登陆");
        }
        return addressDao.lambdaQuery().eq(Address::getUid, userUtils.getUser(ServletUtils.getRequest()).getId()).list();
    }

    @Override
    public Address getDetail(Long id) {
        User user = userUtils.getUser(ServletUtils.getRequest());
        if (Objects.isNull(user)) {
            throw new ExceptionResult("Address","false",null,"请先登陆");
        }
        return addressDao.lambdaQuery().eq(Address::getId,id).one();
    }
}
