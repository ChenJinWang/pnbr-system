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
public class UserQuery extends PageDomain {
    @ApiModelProperty("用户id")
    private Long id;

    @ApiModelProperty("用户名")
    private String userName;

    @ApiModelProperty("手机号码")
    private String phone;



}