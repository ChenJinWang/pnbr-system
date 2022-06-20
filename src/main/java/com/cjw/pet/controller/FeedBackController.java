package com.cjw.pet.controller;

import com.cjw.pet.dto.request.PetQuery;
import com.cjw.pet.dto.response.TagRowVo;
import com.cjw.pet.exception.ExceptionResult;
import com.cjw.pet.pojo.AjaxResult;
import com.cjw.pet.pojo.FeedBack;
import com.cjw.pet.pojo.User;
import com.cjw.pet.service.FeedBackService;

import com.cjw.pet.utils.ServletUtils;
import com.cjw.pet.utils.UserUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

/**
 * 商品相关请求处理
 *
 * @author cjw
 */
@Slf4j
@Api(tags = "商品相关")
@RestController
@RequestMapping("/feedback")
public class FeedBackController {

    private final FeedBackService feedBackService;
    private final UserUtils userUtils;

    public FeedBackController(FeedBackService feedBackService, UserUtils userUtils) {
        this.feedBackService = feedBackService;
        this.userUtils = userUtils;
    }

    /**
     * 新增商品
     * @param feedBack 商品
     * @return 商品id
     */
    @ApiOperation("新增商品")
    @PostMapping()
    public AjaxResult addFeedBack(@RequestBody FeedBack feedBack) {
        AjaxResult ajaxResult = AjaxResult.success("新增宠物食品成功",feedBackService.addFeedBack(feedBack));
        return ajaxResult;
    }

    /**
     * 修改商品
     * @param feedBack 商品
     * @return 结果
     */
    @ApiOperation("修改商品")
    @PutMapping()
    public AjaxResult updateFeedBack(@RequestBody FeedBack feedBack) {
        AjaxResult ajaxResult = AjaxResult.success("修改宠物食品成功",feedBackService.updateFeedBack(feedBack));
        return ajaxResult;
    }


    /**
     * 分页查询
     * @param query 参数
     * @return 分页数据
     */
    @ApiOperation("商品列表")
    @PostMapping("/list")
    public AjaxResult listFeedBack(@RequestBody PetQuery query) {
        query.setAuditStatus("1");
        AjaxResult ajaxResult = AjaxResult.success(feedBackService.listFeedBack(query));
        return ajaxResult;
    }

    /**
     * 加载后台商品列表
     * @param query 参数
     * @return 商品列表
     */
    @ApiOperation("加载后台商品列表")
    @PostMapping("/backstage/list")
    public AjaxResult backstageListFeedBack(@RequestBody PetQuery query) {
        AjaxResult ajaxResult = AjaxResult.success(feedBackService.backstageListFeedBack(query));
        return ajaxResult;
    }

    /**
     * 我的商品
     * @param query 参数
     * @return 分页数据
     */
    @ApiOperation("我的商品")
    @PostMapping("/MyPetDietList")
    public AjaxResult MyPetDietList(@RequestBody  PetQuery query) {
        // 获取登录用户
        User user = userUtils.getUser(ServletUtils.getRequest());
        if (Objects.isNull(user)) {
            throw new ExceptionResult("user","false",null,"请先登陆");
        }
        query.setCreateId(user.getId());
        AjaxResult ajaxResult = AjaxResult.success(feedBackService.listFeedBack(query));
        return ajaxResult;
    }

    /**
     * 删除反馈
     * @param id 反馈id
     * @return 结果
     */
    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除反馈", notes = "删除反馈")
    @ApiImplicitParam(name = "id", required = true, value = "反馈的编号")
    public Boolean deletedFeedBack(@PathVariable String id) {
        return this.feedBackService.deletedFeedBack(id);
    }

    /**
     * 加载前台首页商品列表
     * @return 商品列表
     */
    @ApiOperation("加载前台首页商品列表")
    @GetMapping("/prodTagList")
    public List<TagRowVo> listTagRowVo() {
           return feedBackService.listTagRowVo();
    }

    /**
     * 查询商品详情
     * @param id id
     * @return 详情
     */
    @ApiOperation("查询商品详情")
    @GetMapping("/prodInfo/{id}")
    public FeedBack getFeedBackDetail(@PathVariable Long id) {
        return feedBackService.getFeedBackDetail(id);
    }

    /**
     * 修改审核状态
     * @param id id
     * @param auditStatus 审核状态
     * @return 结果
     */
    @ApiOperation("修改审核状态")
    @PutMapping("/auditStatus/{id}/{auditStatus}")
    public Boolean updateAuditStatus(@PathVariable Long id,@PathVariable String auditStatus) {
        return  feedBackService.updateAuditStatus(id,auditStatus);
    }

    /**
     * 修改上架下架
     * @param id id
     * @param saleable 上架下架
     * @return 结果
     */
    @ApiOperation("修改上架下架 ")
    @PutMapping("/saleable/{id}/{saleable}")
    public Boolean updateSaleable(@PathVariable Long id,@PathVariable Boolean saleable) {
        return  feedBackService.updateSaleable(id,saleable);
    }

    /**
     * 代理商品
     * @param id id
     * @param auditStatus 审核状态
     * @return 结果
     */
    @ApiOperation("代理商品 ")
    @PutMapping("/agency/{id}/{auditStatus}")
    public Boolean agency(@PathVariable Long id,@PathVariable String auditStatus) {
        return  feedBackService.agency(id,auditStatus);
    }

    /**
     * 修改上架下架
     * @param id id
     * @param saleable 上架下架
     * @return 结果
     */
    @ApiOperation("修改上架下架 ")
    @PutMapping("/agency/saleable/{id}/{saleable}")
    public Boolean agencyUpdateSaleable(@PathVariable Long id,@PathVariable Boolean saleable) {
        return  feedBackService.agencyUpdateSaleable(id,saleable);
    }
}
