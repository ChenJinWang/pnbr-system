package com.cjw.pet.dao.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cjw.pet.dao.PetFavoriteDao;
import com.cjw.pet.mapper.PetFavoriteMapper;
import com.cjw.pet.pojo.PetFavorite;
import org.springframework.stereotype.Repository;

/**
 * @author cjw
 */
@Repository
public class PetFavoriteDaoImpl extends ServiceImpl<PetFavoriteMapper, PetFavorite> implements PetFavoriteDao {
}
