package com.yellowsunn.springblog.controller.api;

import com.yellowsunn.springblog.domain.dto.CategoryDto;
import com.yellowsunn.springblog.domain.dto.CategoryListDto;
import com.yellowsunn.springblog.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    // 카테고리 생성
    @PostMapping("/category/create")
    public ResponseEntity<Long> create(@RequestBody CategoryDto categoryDto) {
        return categoryService.createCategory(categoryDto);
    }

    // Id로 카테고리 조회
    @GetMapping("/category/info/{categoryId}")
    public CategoryDto findCategory(@PathVariable(value = "categoryId") Long categoryId) {
        return categoryService.findCategory(categoryId);
    }

    // 카테고리 정보 업데이트
    @PutMapping("/category/update")
    public ResponseEntity<?> update(@RequestBody CategoryDto categoryDto) {
        HttpStatus httpStatus = categoryService.updateCategory(categoryDto);
        return new ResponseEntity<>(httpStatus);
    }

    // 카테고리 삭제
    @DeleteMapping("/category/delete/{categoryId}")
    public ResponseEntity<?> delete(@PathVariable(value = "categoryId") Long categoryId) {
        HttpStatus httpStatus = categoryService.deleteCategory(categoryId);
        return new ResponseEntity<>(httpStatus);
    }

    // 카테고리 Id로 게시글 조회
    @GetMapping(value = {"/category", "/category/{categoryId}"})
    public CategoryDto findArticles(@PathVariable(value = "categoryId", required = false) Long categoryId, Pageable pageable) {
        return categoryService.findArticles(categoryId != null ? categoryId : 0L, pageable);
    }

    // 검색으로 게시글 조회
    @GetMapping(value = {"/search", "/search/{search}"})
    public CategoryDto search(@PathVariable(value = "search", required = false) String search, Pageable pageable) {
        if (search == null) {
            return CategoryDto.builder().search("")
                    .totalElements(0L).totalPages(0).pageNumber(0)
                    .isFirst(true).isLast(true).hasNext(false).hasPrevious(false)
                    .build();
        }

        return categoryService.search(search, pageable);
    }

    // 카테고리 목록
    @GetMapping("/categoryList")
    public CategoryListDto findCategoryList() {
        return categoryService.findAll();
    }
}
