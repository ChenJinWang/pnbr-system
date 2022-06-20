package com.cjw.pet.service.impl;

import com.cjw.pet.dao.CategoryDao;
import com.cjw.pet.mapper.CategoryMapper;
import com.cjw.pet.pojo.Category;
import com.cjw.pet.pojo.User;
import com.cjw.pet.service.CategoryService;
import com.cjw.pet.utils.ServletUtils;
import com.cjw.pet.utils.UserUtils;
import com.cjw.pet.exception.ExceptionResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * @author cjw
 */
@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryDao categoryDao;
    private final CategoryMapper categoryMapper;

    @Autowired
    private UserUtils userUtils;

    public CategoryServiceImpl(CategoryDao categoryDao, CategoryMapper categoryMapper) {
        this.categoryDao = categoryDao;
        this.categoryMapper = categoryMapper;
    }

    @Override
    public Long addCategory(Category category) {
        User user = userUtils.getUser(ServletUtils.getRequest());
        if (Objects.isNull(user)) {
            throw new ExceptionResult("user","false",null,"请先登陆");
        }
        if (!"admin".equals(user.getRole()) && !"superadmin".equals(user.getRole())) {
            throw new ExceptionResult("user","false",null,"角色权限不足");
        }
        if (categoryDao.lambdaQuery().eq(Category::getName,category.getName()).count()>0) {
            throw new ExceptionResult("category","false",null,"新增分类失败，该分类已存在");
        }
        if(!categoryDao.save(category)) {
            throw new ExceptionResult("category","false",null,"新增分类失败");
        }
        return category.getId();
    }

    @Override
    public Boolean updateCategory(Category category) {
        User user = userUtils.getUser(ServletUtils.getRequest());
        if (Objects.isNull(user)) {
            throw new ExceptionResult("user","false",null,"请先登陆");
        }
        if (!"admin".equals(user.getRole()) && !"superadmin".equals(user.getRole())) {
            throw new ExceptionResult("user","false",null,"角色权限不足");
        }
        if (categoryDao.getById(category.getId()).getName().equals(category.getName())) {
            throw new ExceptionResult("category","false",null,"内容未修改");
        }
        if (categoryDao.lambdaQuery().eq(Category::getName,category.getName()).ne(Category::getId,category.getId()).count()>0) {
            throw new ExceptionResult("category","false",null,"新增分类失败，该分类已存在");
        }
        if(!categoryDao.lambdaUpdate().eq(Category::getId,category.getId()).update(category)) {
            throw new ExceptionResult("category","false",null,"新增分类失败");
        }
        return true;
    }

    @Override
    public Boolean deleteCategory(Long id) {
        User user = userUtils.getUser(ServletUtils.getRequest());
        if (Objects.isNull(user)) {
            throw new ExceptionResult("user","false",null,"请先登陆");
        }
        if (!"admin".equals(user.getRole()) && !"superadmin".equals(user.getRole())) {
            throw new ExceptionResult("user","false",null,"角色权限不足");
        }
        if(categoryMapper.deleteById(id)==0) {
            throw new ExceptionResult("user","false",null,"删除失败");
        }

        return true;
    }

    @Override
    public List<Category> queryCategory() {
        return categoryDao.lambdaQuery().list();
    }
}
