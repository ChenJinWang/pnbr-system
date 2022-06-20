package com.cjw.pet.controller;

import com.cjw.pet.dto.request.EvaluationQuery;
import com.cjw.pet.dto.response.EvaluationRowVo;
import com.cjw.pet.pojo.Evaluation;
import com.cjw.pet.pojo.PageList;
import com.cjw.pet.service.EvaluationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * 评论
 *
 * @author cjw
 */
@Slf4j
@Api(tags = "评论")
@RestController
@RequestMapping("/evaluation")
public class EvaluationController {

    private final EvaluationService evaluationService;

    public EvaluationController(EvaluationService evaluationService) {
        this.evaluationService = evaluationService;
    }

    /**
     * 新增留言
     * @param evaluation 留言
     * @return 留言id
     */
    @ApiOperation("新增留言")
    @PostMapping()
    public Long saveEvaluation(@RequestBody Evaluation evaluation) {
       return evaluationService.saveEvaluation(evaluation);
    }


    /**
     * 分页查询 留言
     * @return 结果
     */
    @ApiOperation("分页查询 留言")
    @PostMapping("/list")
    public PageList<EvaluationRowVo> queryEvaluationList(@RequestBody EvaluationQuery query) {
        return evaluationService.queryEvaluationList(query);
    }


    /**
     * 分页查询 子留言
     * @param query 分页数据
     * @return 结果
     */
    @ApiOperation("分页查询 子留言")
    @PostMapping("/sub/list")
    public PageList<EvaluationRowVo> subQueryEvaluationList(@RequestBody EvaluationQuery query) {
        return evaluationService.subQueryEvaluationList(query);
    }


    /**
     * 根据商品id 查询 留言数
     * @param prodId 商品id
     * @return 留言数
     */
    @ApiOperation("根据商品id 查询总留言数")
    @GetMapping("/count/{prodId}")
    public Integer countEvaluationByProdId(@PathVariable Long prodId) {
        return evaluationService.countEvaluationByProdId(prodId);
    }

}
