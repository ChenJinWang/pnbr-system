package com.cjw.pet.dao.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cjw.pet.dao.BackgroundUserDao;
import com.cjw.pet.mapper.BackgroundUserMapper;
import com.cjw.pet.pojo.BackgroundUser;
import org.springframework.stereotype.Repository;

/**
 * @author cjw
 */
@Repository
public class BackgroundUserDaoImpl extends ServiceImpl<BackgroundUserMapper, BackgroundUser> implements BackgroundUserDao {
}
