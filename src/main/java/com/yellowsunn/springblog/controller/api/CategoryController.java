package com.yellowsunn.springblog.controller.api;

import com.yellowsunn.springblog.domain.dto.CategoryDto;
import com.yellowsunn.springblog.domain.dto.CategoryListDto;
import com.yellowsunn.springblog.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@CrossOrigin(value = "*")
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping(value = {"/category", "/category/{categoryId}"})
    public CategoryDto findArticles(@PathVariable(value = "categoryId", required = false) Long categoryId, Pageable pageable) {
        return categoryService.findCategory(categoryId != null ? categoryId : 0L, pageable);
    }

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


    @GetMapping("/categoryList")
    public CategoryListDto findCategoryList() {
        return categoryService.findAll();
    }
}
