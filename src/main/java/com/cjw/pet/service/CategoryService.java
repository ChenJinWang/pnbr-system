package com.cjw.pet.service;

import com.cjw.pet.pojo.Category;

import java.util.List;

/**
 * @author cjw
 */
public interface CategoryService {

    /**
     * 新增分类
     * @param category 参数
     * @return 结果
     */
    Long addCategory(Category category);

    /**
     * 修改分类
     * @param category 分类
     * @return 结果
     */
    Boolean updateCategory(Category category);

    /**
     * 删除分类
     * @param id 分类id
     * @return 结果
     */
    Boolean deleteCategory(Long id);

    /**
     * 查询所有分类
     * @return
     */
    List<Category> queryCategory();
}
