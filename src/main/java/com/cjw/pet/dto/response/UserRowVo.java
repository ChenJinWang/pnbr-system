package com.cjw.pet.dto.response;

import com.cjw.pet.pojo.OrderDetail;
import com.cjw.pet.pojo.Pet;
import com.cjw.pet.pojo.User;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @author cjw
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel("用户管理-用户的一行数据")
public class UserRowVo extends User {
    @ApiModelProperty("用户名")
    private List<User> Users;
    private String userName;
}
