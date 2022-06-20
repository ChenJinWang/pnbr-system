package com.cjw.pet.dto.response;

import com.cjw.pet.pojo.Evaluation;
import com.cjw.pet.pojo.PageList;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * @author cjw
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel("留言的一行数据")
public class EvaluationRowVo extends Evaluation {
    private String toUserName;
    private String toAvatarImage;
    private String commentator;
    private String commentatorAvatarImage;
    private PageList<EvaluationRowVo> subEvaluationRowVos;
}
