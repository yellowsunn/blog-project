package com.yellowsunn.springblog.service.impl;

import com.yellowsunn.springblog.domain.dto.ArticleDto;
import com.yellowsunn.springblog.domain.entity.Article;
import com.yellowsunn.springblog.domain.entity.Category;
import com.yellowsunn.springblog.repository.ArticleRepository;
import com.yellowsunn.springblog.repository.CategoryRepository;
import com.yellowsunn.springblog.repository.ImageRepository;
import com.yellowsunn.springblog.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class ArticleServiceImpl implements ArticleService {

    private final ArticleRepository articleRepository;
    private final ImageRepository imageRepository;
    @Value("${imagePath}")
    private String imgPath;

    @Override
    public ArticleDto findArticle(Long articleId) {
        Optional<Article> articleOptional = articleRepository.findById(articleId);
        if (articleOptional.isEmpty()) return null;

        Article article = articleOptional.get();
        ArticleDto.ArticleDtoBuilder builder = ArticleDto.builder()
                .categoryId(article.getCategory().getId())
                .category(article.getCategory().getName())
                .id(article.getId())
                .writer(article.getWriter())
                .title(article.getTitle())
                .content(article.getContent())
                .like(article.getLike())
                .date(article.getDate());

        // 섬네일 이미지
        imageRepository.findThumbnailByArticle(article).ifPresent(image -> {
            String serverImg = getServerUrl().concat(image.getName());
            builder.thumbnail(serverImg);
        });

        // 부모 카테고리
        Category parentCategory = article.getCategory().getParentCategory();
        if (parentCategory != null) {
            builder.parentCategory(parentCategory.getName());
        }

        // 페이지
        long idx = articleRepository.findIdxByCategoryAndId(article.getCategory(), articleId);
        PageRequest pageRequest = PageRequest.of((int) idx, 1);
        Page<Article> articlePage = articleRepository.findByCategory(article.getCategory(), pageRequest);

        builder.totalElements(articlePage.getTotalElements())
                .totalPages(articlePage.getTotalPages())
                .pageNumber(articlePage.getNumber())
                .isFirst(articlePage.isFirst())
                .isLast(articlePage.isLast())
                .hasNext(articlePage.hasNext())
                .hasPrevious(articlePage.hasPrevious());

        // 이전 게시글
        if (articlePage.hasPrevious()) {
            PageRequest previous = pageRequest.previous();
            Page<Article> prevPage = articleRepository.findByCategory(article.getCategory(), previous);
            Article prevArticle = prevPage.getContent().get(0);
            builder.before(ArticleDto.builder().id(prevArticle.getId()).title(prevArticle.getTitle()).build());
        }

        // 다음 게시글
        if (articlePage.hasNext()) {
            Pageable next = pageRequest.next();
            Page<Article> nextPage = articleRepository.findByCategory(article.getCategory(), next);
            Article nextArticle = nextPage.getContent().get(0);
            builder.after(ArticleDto.builder().id(nextArticle.getId()).title(nextArticle.getTitle()).build());
        }

        return builder.build();
    }

    private String getServerUrl() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        return request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + imgPath;
    }
}
