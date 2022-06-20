package com.cjw.pet.dto.request;

import com.cjw.pet.pojo.PageDomain;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 宠物食品
 * @author cjw
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel("查询宠物食品")
public class PetQuery extends PageDomain {
    @ApiModelProperty("分类id")
    private Long cid;

    @ApiModelProperty("标题")
    private String title;

    @ApiModelProperty("发布人id")
    private Long createId;

    @ApiModelProperty("价格")
    private Double price;

    @ApiModelProperty("审核状态")
    private String auditStatus;

    @ApiModelProperty("是否上架")
    private Boolean saleable;

    @ApiModelProperty("类型")
    private Integer type;


}