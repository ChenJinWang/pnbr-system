package com.cjw.pet.service;

import com.cjw.pet.dto.response.PetRowVo;
import com.cjw.pet.pojo.PetFavorite;
import com.cjw.pet.pojo.PageDomain;
import com.cjw.pet.pojo.PageList;

/**
 * @author cjw
 */
public interface PetFavoriteService {

    /**
     * 添加收藏
     * @param petFavorite 参数
     * @return id
     */
    Long addPetFavorite(PetFavorite petFavorite);

    /**
     * 删除收藏
     * @param id id
     * @return 结果
     */
    Boolean deletedPetFavorite(Long id);

    /**
     * 验证是否收藏
     * @param favoriteId 宠物食品id
     * @return 结果
     */
    Boolean verify(Long favoriteId);

    /**
     * 加载所有收藏
     * @return 收藏
     */
    PageList<PetRowVo> listCollection(PageDomain pageDomain);

    /**
     * 获取收藏数
     * @return 收藏数
     */
    Integer getPetFavoriteCunt();

}
