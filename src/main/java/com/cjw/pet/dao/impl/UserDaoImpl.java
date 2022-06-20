package com.cjw.pet.dao.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cjw.pet.dao.UserDao;
import com.cjw.pet.mapper.UserMapper;
import com.cjw.pet.pojo.User;
import org.springframework.stereotype.Repository;

/**
 * @author cjw
 */
@Repository
public class UserDaoImpl extends ServiceImpl<UserMapper, User> implements UserDao {
}
