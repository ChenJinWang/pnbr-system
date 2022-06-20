package com.cjw.pet.service;

import com.cjw.pet.dto.request.ComEvaluationQuery;
import com.cjw.pet.dto.request.EvaluationQuery;
import com.cjw.pet.dto.response.ComEvaluationRowVo;
import com.cjw.pet.dto.response.EvaluationRowVo;
import com.cjw.pet.pojo.ComEvaluation;
import com.cjw.pet.pojo.Evaluation;
import com.cjw.pet.pojo.PageList;

/**
 * @author cjw
 */
public interface ComEvaluationService {

    /**
     * 新增留言
     * @param comEvaluation 留言
     * @return 留言id
     */
    Long saveEvaluation(ComEvaluation comEvaluation);

    /**
     * 分页查询 留言
     * @param query 分页数据
     * @return 结果
     */
    PageList<ComEvaluationRowVo> queryEvaluationList(ComEvaluationQuery query);


    /**
     * 分页查询 子留言
     * @param query 分页数据
     * @return 结果
     */
    PageList<ComEvaluationRowVo> subQueryEvaluationList(ComEvaluationQuery query);

    /**
     * 根据商品id 查询 留言数
     * @param prodId 商品id
     * @return 留言数
     */
    Integer countEvaluationByProdId(Long prodId);

}
