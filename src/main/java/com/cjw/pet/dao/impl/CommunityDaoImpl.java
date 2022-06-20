package com.cjw.pet.dao.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cjw.pet.dao.CommunityDao;
import com.cjw.pet.dao.PetDao;
import com.cjw.pet.mapper.CommunityMapper;
import com.cjw.pet.mapper.PetMapper;
import com.cjw.pet.pojo.Community;
import com.cjw.pet.pojo.Pet;
import org.springframework.stereotype.Repository;

/**
 * @author cjw
 */
@Repository
public class CommunityDaoImpl extends ServiceImpl<CommunityMapper, Community> implements CommunityDao {

}
