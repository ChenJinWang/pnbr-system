package com.cjw.pet.dao.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cjw.pet.dao.AddressDao;
import com.cjw.pet.mapper.AddressMapper;
import com.cjw.pet.pojo.Address;
import org.springframework.stereotype.Repository;

/**
 * @author cjw
 */
@Repository
public class AddressDaoImpl extends ServiceImpl<AddressMapper, Address> implements AddressDao {
}
