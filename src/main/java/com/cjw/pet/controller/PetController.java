package com.cjw.pet.controller;

import com.cjw.pet.dto.request.PetQuery;
import com.cjw.pet.pojo.Pet;
import com.cjw.pet.service.PetService;
import com.cjw.pet.dto.response.TagRowVo;
import com.cjw.pet.exception.ExceptionResult;
import com.cjw.pet.pojo.AjaxResult;
import com.cjw.pet.pojo.User;
import com.cjw.pet.utils.ServletUtils;
import com.cjw.pet.utils.UserUtils;
import io.swagger.annotations.Api;
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
@RequestMapping("/pet")
public class PetController {

    private final PetService petService;
    private final UserUtils userUtils;

    public PetController(PetService petService, UserUtils userUtils) {
        this.petService = petService;
        this.userUtils = userUtils;
    }

    /**
     * 新增商品
     * @param pet 商品
     * @return 商品id
     */
    @ApiOperation("新增商品")
    @PostMapping()
    public AjaxResult addPet(@RequestBody Pet pet) {
        AjaxResult ajaxResult = AjaxResult.success("新增宠物食品成功",petService.addPet(pet));
        return ajaxResult;
    }

    /**
     * 修改商品
     * @param pet 商品
     * @return 结果
     */
    @ApiOperation("修改商品")
    @PutMapping()
    public AjaxResult updatePet(@RequestBody Pet pet) {
        AjaxResult ajaxResult = AjaxResult.success("修改宠物食品成功",petService.updatePet(pet));
        return ajaxResult;
    }


    /**
     * 分页查询
     * @param query 参数
     * @return 分页数据
     */
    @ApiOperation("商品列表")
    @PostMapping("/list")
    public AjaxResult listPet(@RequestBody PetQuery query) {
        query.setAuditStatus("1");
        AjaxResult ajaxResult = AjaxResult.success(petService.listPet(query));
        return ajaxResult;
    }

    /**
     * 加载后台商品列表
     * @param query 参数
     * @return 商品列表
     */
    @ApiOperation("加载后台商品列表")
    @PostMapping("/backstage/list")
    public AjaxResult backstageListPet(@RequestBody PetQuery query) {
        AjaxResult ajaxResult = AjaxResult.success(petService.backstageListPet(query));
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
        AjaxResult ajaxResult = AjaxResult.success(petService.listPet(query));
        return ajaxResult;
    }

    /**
     * 加载前台首页商品列表
     * @return 商品列表
     */
    @ApiOperation("加载前台首页商品列表")
    @GetMapping("/prodTagList")
    public List<TagRowVo> listTagRowVo() {
           return petService.listTagRowVo();
    }

    /**
     * 查询商品详情
     * @param id id
     * @return 详情
     */
    @ApiOperation("查询商品详情")
    @GetMapping("/prodInfo/{id}")
    public Pet getPetDetail(@PathVariable Long id) {
        return petService.getPetDetail(id);
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
        return  petService.updateAuditStatus(id,auditStatus);
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
        return  petService.updateSaleable(id,saleable);
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
        return  petService.agency(id,auditStatus);
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
        return  petService.agencyUpdateSaleable(id,saleable);
    }
}
