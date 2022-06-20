package com.cjw.pet.controller;

import com.cjw.pet.dto.request.ComEvaluationQuery;
import com.cjw.pet.dto.request.EvaluationQuery;
import com.cjw.pet.dto.response.ComEvaluationRowVo;
import com.cjw.pet.dto.response.EvaluationRowVo;
import com.cjw.pet.pojo.ComEvaluation;
import com.cjw.pet.pojo.Evaluation;
import com.cjw.pet.pojo.PageList;
import com.cjw.pet.service.ComEvaluationService;
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
@RequestMapping("/comevaluation")
public class ComEvaluationController {

    private final ComEvaluationService comEvaluationService;

    public ComEvaluationController(ComEvaluationService comEvaluationService) {
        this.comEvaluationService = comEvaluationService;
    }

    /**
     * 新增留言
     * @param comEvaluation 留言
     * @return 留言id
     */
    @ApiOperation("新增留言")
    @PostMapping()
    public Long saveEvaluation(@RequestBody ComEvaluation comEvaluation) {
       return comEvaluationService.saveEvaluation(comEvaluation);
    }


    /**
     * 分页查询 留言
     * @return 结果
     */
    @ApiOperation("分页查询 留言")
    @PostMapping("/list")
    public PageList<ComEvaluationRowVo> queryEvaluationList(@RequestBody ComEvaluationQuery query) {
        return comEvaluationService.queryEvaluationList(query);
    }


    /**
     * 分页查询 子留言
     * @param query 分页数据
     * @return 结果
     */
    @ApiOperation("分页查询 子留言")
    @PostMapping("/sub/list")
    public PageList<ComEvaluationRowVo> subQueryEvaluationList(@RequestBody ComEvaluationQuery query) {
        return comEvaluationService.subQueryEvaluationList(query);
    }


    /**
     * 根据商品id 查询 留言数
     * @param prodId 商品id
     * @return 留言数
     */
    @ApiOperation("根据商品id 查询总留言数")
    @GetMapping("/count/{prodId}")
    public Integer countEvaluationByProdId(@PathVariable Long prodId) {
        return comEvaluationService.countEvaluationByProdId(prodId);
    }

}
