package com.cjw.pet.dao.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cjw.pet.dao.CategoryDao;
import com.cjw.pet.mapper.CategoryMapper;
import com.cjw.pet.pojo.Category;
import org.springframework.stereotype.Repository;


/**
 * @author cjw
 */
@Repository
public class CategoryDaoImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryDao {
}
