package com.cjw.pet.controller;

import com.cjw.pet.pojo.AjaxResult;
import com.cjw.pet.pojo.Category;
import com.cjw.pet.service.CategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * 分类相关
 *
 * @author cjw
 */
@Slf4j
@Api(tags = "分类相关")
@RestController
@RequestMapping("/category")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    /**
     * 新增分类
     * @param category 参数
     * @return 结果
     */
    @ApiOperation("新增分类")
    @PostMapping()
    public AjaxResult addCategory(@RequestBody Category category) {
        AjaxResult ajaxResult = AjaxResult.success("新增分类成功",categoryService.addCategory(category));
        return ajaxResult;
    }

    /**
     * 修改分类
     * @param category 分类
     * @return 结果
     */
    @ApiOperation("修改分类")
    @PutMapping()
    public AjaxResult updateCategory(@RequestBody Category category) {
        AjaxResult ajaxResult = AjaxResult.success("修改分类成功",categoryService.updateCategory(category));
        return ajaxResult;
    }
    /**
     * 删除分类
     * @param id 分类id
     * @return 结果
     */
    @ApiOperation("删除分类")
    @DeleteMapping("/{id}")
    public AjaxResult deleteCategory(@PathVariable Long id) {
        AjaxResult ajaxResult = AjaxResult.success("删除分类成功",categoryService.deleteCategory(id));
        return ajaxResult;
    }


    /**
     * 查询所有分类
     * @return
     */
    @ApiOperation("查询分类")
    @GetMapping
    public AjaxResult queryCategory() {
        AjaxResult ajaxResult = AjaxResult.success("查询所有分类成功",categoryService.queryCategory());
        return ajaxResult;
    }
}
