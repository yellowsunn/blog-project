package com.yellowsunn.springblog.service.impl;

import com.querydsl.core.Tuple;
import com.querydsl.core.types.dsl.Expressions;
import com.yellowsunn.springblog.domain.dto.ArticleDto;
import com.yellowsunn.springblog.domain.dto.CategoryDto;
import com.yellowsunn.springblog.domain.dto.CategoryListDto;
import com.yellowsunn.springblog.domain.entity.Category;
import com.yellowsunn.springblog.domain.entity.QCategory;
import com.yellowsunn.springblog.repository.ArticleRepository;
import com.yellowsunn.springblog.repository.CategoryRepository;
import com.yellowsunn.springblog.service.ArticleService;
import com.yellowsunn.springblog.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

import static com.yellowsunn.springblog.domain.entity.QArticle.article;
import static com.yellowsunn.springblog.domain.entity.QCategory.category;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final ArticleService articleService;

    private final CategoryRepository categoryRepository;
    private final ArticleRepository articleRepository;

    @Transactional
    @Override
    public ResponseEntity<Long> createCategory(CategoryDto categoryDto) {
        Category.CategoryBuilder builder = Category.builder()
                .name(categoryDto.getCategory())
                .order(categoryDto.getOrder());

        if (categoryDto.getParentId() != null) {
            Optional<Category> categoryOptional = categoryRepository.findById(categoryDto.getParentId());
            if (categoryOptional.isEmpty()) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

            builder.parentCategory(categoryOptional.get());
        }

        Category saveCategory = categoryRepository.save(builder.build());

        return new ResponseEntity<>(saveCategory.getId(), HttpStatus.CREATED);
    }

    @Override
    public CategoryDto findCategory(Long categoryId) {
        Optional<Category> categoryOptional = categoryRepository.findById(categoryId);
        if (categoryOptional.isEmpty()) return null;

        Category category = categoryOptional.get();

        return CategoryDto.builder()
                .id(category.getId())
                .category(category.getName())
                .parentId(category.getParentCategory() != null ? category.getParentCategory().getId() : null)
                .order(category.getOrder())
                .build();
    }

    @Transactional
    @Override
    public HttpStatus updateCategory(CategoryDto categoryDto) {
        Optional<Category> categoryOptional = categoryRepository.findById(categoryDto.getId());
        if (categoryOptional.isEmpty()) return HttpStatus.BAD_REQUEST;

        Category parentCategory = null;
        if(categoryDto.getParentId() != null) {
            Optional<Category> parentCategoryOptional = categoryRepository.findById(categoryDto.getParentId());
            if (parentCategoryOptional.isEmpty()) return HttpStatus.BAD_REQUEST;
            parentCategory = parentCategoryOptional.get();
        }

        Category category = categoryOptional.get();
        category.changeName(categoryDto.getCategory());
        category.changeParentCategory(parentCategory);
        category.changeOrder(categoryDto.getOrder());
        return HttpStatus.OK;
    }

    @Transactional
    @Override
    public HttpStatus deleteCategory(Long categoryId) {
        Optional<Category> categoryOptional = categoryRepository.findById(categoryId);
        if (categoryOptional.isEmpty()) return HttpStatus.BAD_REQUEST;

        categoryRepository.delete(categoryOptional.get());
        return HttpStatus.OK;
    }

    @Override
    public CategoryDto findArticles(Long categoryId, Pageable pageable) {
        Category category = categoryRepository.findById(categoryId).orElse(null);
        String parentCategoryName = null;

        Page<Tuple> tuplePage = articleRepository.findSimpleArticles(category, pageable);

        List<ArticleDto> articles = new ArrayList<>();
        for (Tuple t : tuplePage) {
            if (category != null) {
                parentCategoryName = category.getParentCategory() != null ? category.getParentCategory().getName() : null;
            }
            ArticleDto articleDto = articleService.changeSimple(categoryRepository, t);
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
    public CategoryDto search(String search, Pageable pageable) {
        Page<Tuple> tuplePage = articleRepository.searchSimpleArticles(search, pageable);
        List<ArticleDto> articles = new ArrayList<>();
        for (Tuple t : tuplePage) {
            ArticleDto articleDto = articleService.changeSimple(categoryRepository, t);
            articles.add(articleDto);
        }

        return CategoryDto.builder()
                .search(search)
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

        // 상위 카테고리의 id를 key로 한 Map
        Map<Long, CategoryDto> map = new HashMap<>();
        Queue<Tuple> tuples = categoryRepository.findCategoryList();
        List<CategoryDto> categoryList = new ArrayList<>();

        long total = 0;

        // 상위 카테고리 DTO 변환
        while (!tuples.isEmpty()) {
            Tuple tuple = tuples.peek();
            Category category = tuple.get(QCategory.category);
            Long totalArticles = tuple.get(1, Long.class);
            if (category.getParentCategory() != null) break;

            CategoryDto categoryDto = CategoryDto.builder()
                    .id(category.getId())
                    .category(category.getName())
                    .totalArticles(totalArticles)
                    .build();

            categoryList.add(categoryDto);
            total += (totalArticles != null ? totalArticles : 0);
            map.put(category.getId(), categoryDto);
            tuples.poll();
        }

        // 하위 카테고리 DTO 변환
        while (!tuples.isEmpty()) {
            Tuple tuple = tuples.poll();
            Category subCategory = tuple.get(QCategory.category);
            Long totalArticles = tuple.get(1, Long.class);

            CategoryDto childCategoryDto = CategoryDto.builder()
                    .id(subCategory.getId())
                    .category(subCategory.getName())
                    .totalArticles(totalArticles)
                    .build();

            CategoryDto parentCategoryDto = map.get(subCategory.getParentCategory().getId());
            if (parentCategoryDto.getChildren() == null) {
                parentCategoryDto.setChildren(new ArrayList<>());
            }
            parentCategoryDto.getChildren().add(childCategoryDto);
            parentCategoryDto.setTotalArticles(parentCategoryDto.getTotalArticles() + childCategoryDto.getTotalArticles());

            total += (totalArticles != null ? totalArticles : 0);
        }

        return CategoryListDto.builder()
                .list(categoryList)
                .total(total)
                .build();
    }
}
