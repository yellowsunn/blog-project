package com.yellowsunn.springblog.service;

import com.yellowsunn.springblog.domain.dto.CategoryDto;
import com.yellowsunn.springblog.domain.dto.CategoryListDto;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public interface CategoryService {

    ResponseEntity<Long> createCategory(CategoryDto categoryDto);

    CategoryDto findCategory(Long categoryId);

    HttpStatus updateCategory(CategoryDto categoryDto);

    HttpStatus deleteCategory(Long categoryId);

    CategoryDto findArticles(Long categoryId, Pageable pageable);

    CategoryDto search(String search, Pageable pageable);

    CategoryListDto findAll();
}
