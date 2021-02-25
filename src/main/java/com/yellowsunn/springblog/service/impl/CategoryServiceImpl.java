package com.yellowsunn.springblog.service.impl;

import com.yellowsunn.springblog.domain.dto.ArticleDto;
import com.yellowsunn.springblog.domain.dto.CategoryDto;
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

@Service
@Transactional
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final ArticleRepository articleRepository;
    private final CommentRepository commentRepository;
    private final ImageRepository imageRepository;

    @Value("${imagePath}")
    private String imgPath;

    @Transactional(readOnly = true)
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
                articleDtoList.add(getSimpleArticleDto(article));
            }
        } else {
            // 커버 카테고리인 경우
            for (Article article : articleRepository.findLatest3ByCategoryIn(categories)) {
                articleDtoList.add(getSimpleArticleDto(article));
            }
        }

        CategoryDto.CategoryDtoBuilder categoryDtoBuilder = CategoryDto.builder()
                .articles(articleDtoList);

        if (category != null) {
            // 상위 카테고리
            Category parentCategory = category.getParentCategory();
            if (parentCategory == null) parentCategory = category;
            categoryDtoBuilder
                    .id(category.getId())
                    .category(category.getName())
                    .baseCategory(parentCategory.getName());
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
    public ArticleDto getSimpleArticleDto(Article article) {
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
