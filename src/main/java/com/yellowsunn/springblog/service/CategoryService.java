package com.yellowsunn.springblog.service;

import com.yellowsunn.springblog.domain.dto.CategoryDto;
import org.springframework.data.domain.Pageable;

public interface CategoryService {

    CategoryDto findArticles(Long categoryId, Pageable pageable);
}
