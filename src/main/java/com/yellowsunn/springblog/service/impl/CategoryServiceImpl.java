package com.yellowsunn.springblog.service.impl;

import com.yellowsunn.springblog.domain.dto.ArticleDto;
import com.yellowsunn.springblog.domain.dto.CategoryDto;
import com.yellowsunn.springblog.domain.dto.CategoryListDto;
import com.yellowsunn.springblog.domain.entity.Article;
import com.yellowsunn.springblog.domain.entity.Category;
import com.yellowsunn.springblog.repository.ArticleRepository;
import com.yellowsunn.springblog.repository.CategoryRepository;
import com.yellowsunn.springblog.repository.CommentRepository;
import com.yellowsunn.springblog.repository.ImageRepository;
import com.yellowsunn.springblog.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final ArticleRepository articleRepository;
    private final CommentRepository commentRepository;
    private final ImageRepository imageRepository;

    @Value("${imagePath}")
    private String imgPath;

    @Override
    public CategoryDto findArticles(Long categoryId, Pageable pageable) {
        Category category = categoryRepository.findById(categoryId).orElse(null);

        List<ArticleDto> articleDtoList = new ArrayList<>();
        Page<Article> articlePage = null;

        List<Category> categories = new ArrayList<>();
        if (category != null) {
            categories.add(category);
            categories.addAll(categoryRepository.findChildCategories(category));
        }

        if (pageable != null) {
            articlePage = articleRepository.findByCategoryIn(categories, pageable);
            for (Article article : articlePage) {
                articleDtoList.add(getSimpleArticle(article));
            }
        } else {
            // 커버 카테고리인 경우
            for (Article article : articleRepository.findLatest3ByCategoryIn(categories)) {
                articleDtoList.add(getSimpleArticle(article));
            }
        }

        CategoryDto.CategoryDtoBuilder categoryDtoBuilder = CategoryDto.builder()
                .articles(articleDtoList);

        if (category != null) {
            // 상위 카테고리
            categoryDtoBuilder
                    .id(category.getId())
                    .category(category.getName())
                    .parentCategory(category.getParentCategory() != null ? category.getParentCategory().getName() : "");
        }

        if (articlePage != null) {
            categoryDtoBuilder
                    .totalElements(articlePage.getTotalElements())
                    .totalPages(articlePage.getTotalPages())
                    .pageNumber(articlePage.getNumber())
                    .isFirst(articlePage.isFirst())
                    .isLast(articlePage.isLast())
                    .hasNext(articlePage.hasNext())
                    .hasPrevious(articlePage.hasPrevious());
        }

        return categoryDtoBuilder.build();
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

    @Override
    public ArticleDto getSimpleArticle(Article article) {
        // 본문 내용 요약처리
        String content = removeTag(article.getContent());
        if (content.length() > 200) {
            content = content.substring(0, 200);
        }
        Category category = article.getCategory();
        long commentCount = commentRepository.countByArticle(article);

        ArticleDto.ArticleDtoBuilder builder = ArticleDto.builder()
                .categoryId(category.getId())
                .category(category.getName())
                .id(article.getId())
                .title(article.getTitle())
                .summary(content)
                .commentCount(commentCount)
                .simpleDate(article.getDate());

        imageRepository.findThumbnailByArticle(article)
                .ifPresent(image -> {
                    String serverImg = getServerUrl().concat(image.getName());
                    builder.thumbnail(serverImg);
                });

        // 부모 카테고리
        Category parentCategory = article.getCategory().getParentCategory();
        if (parentCategory != null) {
            builder.parentCategory(parentCategory.getName());
        }

        return builder.build();
    }

    private String removeTag(String html) {
        return html.replaceAll("(<([^>]+)>)", "");
    }

    private String getServerUrl() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        return request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + imgPath;
    }
}
