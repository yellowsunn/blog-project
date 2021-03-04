package com.yellowsunn.springblog.service;

import com.yellowsunn.springblog.domain.dto.CategoryDto;
import com.yellowsunn.springblog.domain.dto.CategoryListDto;
import org.springframework.data.domain.Pageable;

public interface CategoryService {

    CategoryDto findCategory(Long categoryId, Pageable pageable);

    CategoryListDto findAll();
}
