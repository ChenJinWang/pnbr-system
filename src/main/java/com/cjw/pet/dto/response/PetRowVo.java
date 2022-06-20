package com.cjw.pet.dto.response;

import com.cjw.pet.pojo.Pet;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author cjw
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel("宠物食品管理-宠物食品的一行数据")
public class PetRowVo extends Pet {
    @ApiModelProperty("分类名")
    private String categoryName;
}
