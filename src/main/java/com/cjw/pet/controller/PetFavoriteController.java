package com.cjw.pet.controller;

import com.cjw.pet.service.PetFavoriteService;
import com.cjw.pet.pojo.AjaxResult;
import com.cjw.pet.pojo.PetFavorite;
import com.cjw.pet.pojo.PageDomain;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

/**
 * 收藏夹
 *
 * @author cjw
 */
@Api(tags = "收藏夹")
@RestController
@RequestMapping("/pet/favorite")
public class PetFavoriteController {

    private final PetFavoriteService petFavoriteService;

    public PetFavoriteController(PetFavoriteService petFavoriteService) {
        this.petFavoriteService = petFavoriteService;
    }

    /**
     * 添加收藏
     * @param petFavorite 参数
     * @return id
     */
    @ApiOperation("添加收藏")
    @PostMapping()
    public AjaxResult addPetFavorite(@RequestBody PetFavorite petFavorite) {
        AjaxResult ajaxResult = AjaxResult.success("添加收藏成功",petFavoriteService.addPetFavorite(petFavorite));
        return ajaxResult;
    }

    /**
     * 删除收藏
     * @param id id
     * @return 结果
     */
    @ApiOperation("取消收藏")
    @DeleteMapping("/{id}")
    public AjaxResult deletedPetFavorite(@PathVariable Long id) {
        AjaxResult ajaxResult = AjaxResult.success("删除收藏成功",petFavoriteService.deletedPetFavorite(id));
        return ajaxResult;
    }

    /**
     * 验证是否收藏
     * @param favoriteId 宠物食品id
     * @return 结果
     */
    @ApiOperation("验证是否收藏")
    @GetMapping("/{favoriteId}")
    public AjaxResult verify(@PathVariable Long favoriteId) {
        AjaxResult ajaxResult = AjaxResult.success("验证是否收藏成功",petFavoriteService.verify(favoriteId));
        return ajaxResult;
    }

    /**
     * 加载所有收藏
     * @return 收藏
     */
    @ApiOperation("加载所有收藏")
    @PostMapping("/list")
    public AjaxResult listCollection(@RequestBody PageDomain pageDomain) {
        AjaxResult ajaxResult = AjaxResult.success("加载所有收藏",petFavoriteService.listCollection(pageDomain));
        return ajaxResult;
    }

    /**
     * 获取收藏数
     * @return 结果
     */
    @ApiOperation("获取收藏数")
    @GetMapping("/count")
    public Integer getPetFavoriteCunt() {
       return petFavoriteService.getPetFavoriteCunt();
    }
}
