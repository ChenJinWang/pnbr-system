package com.cjw.pet.dto.request;

import com.cjw.pet.pojo.PageDomain;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 宠物食品查询
 * @author cjw
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel("查询宠物食品")
public class SaysCommentaryQuery extends PageDomain {
    @ApiModelProperty("宠物食品名")
    private String petName;
}
