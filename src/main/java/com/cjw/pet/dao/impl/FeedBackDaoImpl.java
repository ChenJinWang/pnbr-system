package com.cjw.pet.dao.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.cjw.pet.dao.FeedBackDao;
import com.cjw.pet.mapper.FeedBackMapper;
import com.cjw.pet.pojo.FeedBack;
import org.springframework.stereotype.Repository;

/**
 * @author cjw
 */
@Repository
public class FeedBackDaoImpl extends ServiceImpl<FeedBackMapper, FeedBack> implements FeedBackDao {

}
