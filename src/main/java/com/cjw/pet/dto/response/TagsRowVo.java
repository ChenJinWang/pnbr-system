package com.cjw.pet.dto.response;

import com.cjw.pet.pojo.Community;
import com.cjw.pet.pojo.FeedBack;
import com.cjw.pet.pojo.Pet;
import com.cjw.pet.pojo.PetFavorite;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author cjw
 */
@Data
@ApiModel("首页商品列表")
public class TagsRowVo {
    @ApiModelProperty("分类id")
    private Long id;
    @ApiModelProperty("样式")
    private Integer style;
    @ApiModelProperty("分类名")
    private String title;
    @ApiModelProperty("帖子列表")
    private List<Community> communities;
    @ApiModelProperty("宠物食品收藏列表")
    private List<PetFavorite> favorites;
}
