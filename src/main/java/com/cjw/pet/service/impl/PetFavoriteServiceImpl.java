package com.cjw.pet.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cjw.pet.dto.response.PetRowVo;
import com.cjw.pet.service.PetFavoriteService;
import com.cjw.pet.dao.PetDao;
import com.cjw.pet.dao.PetFavoriteDao;
import com.cjw.pet.exception.ExceptionResult;
import com.cjw.pet.pojo.*;
import com.cjw.pet.utils.ServletUtils;
import com.cjw.pet.utils.UserUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author cjw
 */
@Service
public class PetFavoriteServiceImpl implements PetFavoriteService {

    private final PetFavoriteDao petFavoriteDao;
    private final PetDao petDao;
    private final ModelMapper modelMapper;

    @Autowired
    private UserUtils userUtils;

    public PetFavoriteServiceImpl(PetFavoriteDao petFavoriteDao, PetDao petDao, ModelMapper modelMapper) {
        this.petFavoriteDao = petFavoriteDao;
        this.petDao = petDao;
        this.modelMapper = modelMapper;
    }

    @Override
    public Long addPetFavorite(PetFavorite petFavorite) {
        // 获取登录用户
        User user = userUtils.getUser(ServletUtils.getRequest());
        if (Objects.isNull(user)) {
            throw new ExceptionResult("user","false",null,"请先登陆");
        }
        Integer count1 = petDao.lambdaQuery().eq(Pet::getId, petFavorite.getFavoriteId()).eq(Pet::getCreateId, user.getId()).count();
        if (count1>0) {
            throw new ExceptionResult("cart","false",null,"不能收藏自己发布的商品");
        }
        if (Objects.isNull(user)) {
            throw new ExceptionResult("user","false",null,"请先登陆");
        }
        petFavorite.setUid(user.getId());
        if (!petFavoriteDao.save(petFavorite)) {
            throw new ExceptionResult("petFavorite","false",null,"收藏失败");
        }
        return petFavorite.getId();
    }

    @Override
    public Boolean deletedPetFavorite(Long id) {
        // 获取登录用户
        User user = userUtils.getUser(ServletUtils.getRequest());
        if (Objects.isNull(user)) {
            throw new ExceptionResult("user","false",null,"请先登陆");
        }
        if (!petFavoriteDao.lambdaUpdate().eq(PetFavorite::getFavoriteId,id).remove()) {
            throw new ExceptionResult("petFavorite","false",null,"取消收藏失败");
        }
        return true;
    }

    @Override
    public Boolean verify(Long favoriteId) {
        // 获取登录用户
        User user = userUtils.getUser(ServletUtils.getRequest());
        if (Objects.isNull(user)) {
            throw new ExceptionResult("user","false",null,"请先登陆");
        }
        if(petFavoriteDao.lambdaQuery().eq(PetFavorite::getUid,user.getId()).eq(PetFavorite::getFavoriteId,favoriteId).count()==0) {
            return false;
        }
        return true;
    }

    @Override
    public PageList<PetRowVo> listCollection(PageDomain pageDomain) {
        // 获取登录用户
        User user = userUtils.getUser(ServletUtils.getRequest());
        if (Objects.isNull(user)) {
            throw new ExceptionResult("user","false",null,"请先登陆");
        }
        Page<PetFavorite> page = petFavoriteDao
                .lambdaQuery()
                .eq(PetFavorite::getUid, user.getId())
                .orderByDesc(PetFavorite::getCreateTime)
                .page(new Page<>(pageDomain.getPageNum(), pageDomain.getPageSize()));
        List<PetRowVo> petRowVos = new ArrayList<>();
        page.getRecords().forEach(petFavorite -> {
            Pet pet = petDao.getById(petFavorite.getFavoriteId());
            if (!Objects.isNull(pet)) {
                PetRowVo petRowVo = modelMapper.map(pet,PetRowVo.class);
                petRowVo.setCategoryName("");
                petRowVos.add(petRowVo);
            }
        });
        return PageList.of(petRowVos, page);
    }

    @Override
    public Integer getPetFavoriteCunt() {
        User user = userUtils.getUser(ServletUtils.getRequest());
        if (Objects.isNull(user)) {
            throw new ExceptionResult("user","false",null,"请先登陆");
        }
        return petFavoriteDao.lambdaQuery().eq(PetFavorite::getUid,user.getId()).count();
    }
}
