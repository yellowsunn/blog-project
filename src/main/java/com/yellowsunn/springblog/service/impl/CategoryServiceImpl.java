package com.yellowsunn.springblog.service.impl;

import com.querydsl.core.Tuple;
import com.yellowsunn.springblog.domain.dto.ArticleDto;
import com.yellowsunn.springblog.domain.dto.CategoryDto;
import com.yellowsunn.springblog.domain.dto.CategoryListDto;
import com.yellowsunn.springblog.domain.entity.Category;
import com.yellowsunn.springblog.repository.ArticleRepository;
import com.yellowsunn.springblog.repository.CategoryRepository;
import com.yellowsunn.springblog.service.ArticleService;
import com.yellowsunn.springblog.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final ArticleService articleService;

    private final CategoryRepository categoryRepository;
    private final ArticleRepository articleRepository;

    @Override
    public CategoryDto findCategory(Long categoryId, Pageable pageable) {
        Category category = categoryRepository.findById(categoryId).orElse(null);
        String parentCategoryName = null;

        Page<Tuple> tuplePage = articleRepository.findSimpleArticles(category, pageable);

        List<ArticleDto> articles = new ArrayList<>();
        for (Tuple t : tuplePage) {
            if (category != null) {
                parentCategoryName = category.getParentCategory() != null ? category.getParentCategory().getName() : null;
            }
            ArticleDto articleDto = articleService.changeSimple(categoryRepository, t, category, parentCategoryName);
            articles.add(articleDto);
        }

        return CategoryDto.builder()
                .id(category != null ? category.getId() : null)
                .category(category != null ? category.getName() : null)
                .parentCategory(parentCategoryName)
                .articles(articles)

                .totalElements(tuplePage.getTotalElements())
                .totalPages(tuplePage.getTotalPages())
                .pageNumber(tuplePage.getNumber())
                .isFirst(tuplePage.isFirst())
                .isLast(tuplePage.isLast())
                .hasNext(tuplePage.hasNext())
                .hasPrevious(tuplePage.hasPrevious())
                .build();
    }

    @Override
    public CategoryListDto findAll() {
        List<CategoryDto> categories = categoryRepository.findAllParentCategories()
                .stream().map(category -> {
                    List<CategoryDto> childCategories = categoryRepository.findChildCategories(category).stream().map(childCategory ->
                            CategoryDto.builder()
                                    .id(childCategory.getId())
                                    .category(childCategory.getName())
                                    .totalArticles(articleRepository.countByCategory(childCategory))
                                    .build()
                    ).collect(Collectors.toList());

                    return CategoryDto.builder()
                            .id(category.getId())
                            .category(category.getName())
                            .children(childCategories)
                            .totalArticles(articleRepository.countByCategory(category))
                            .build();
                }).collect(Collectors.toList());

        return CategoryListDto.builder()
                .list(categories)
                .total(articleRepository.count())
                .build();
    }
}
