package com.cjw.pet.dto.request;

import com.cjw.pet.pojo.PageDomain;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author cjw
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel("查询留言")
public class ComEvaluationQuery extends PageDomain {
    /**
     * 父评论id
     */
    private Long parentId;

    /**
     * 商品id
     */
    private Long petId;
}
