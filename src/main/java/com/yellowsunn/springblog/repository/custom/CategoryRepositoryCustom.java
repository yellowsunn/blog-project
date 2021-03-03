package com.yellowsunn.springblog.repository.custom;

import com.yellowsunn.springblog.domain.entity.Category;

import java.util.List;

public interface CategoryRepositoryCustom {

    List<Category> findChildCategories(Category baseCategory);

    List<Category> findAllParentCategories();
}
