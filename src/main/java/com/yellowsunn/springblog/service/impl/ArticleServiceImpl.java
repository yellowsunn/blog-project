package com.yellowsunn.springblog.service.impl;

import com.querydsl.core.Tuple;
import com.yellowsunn.springblog.domain.dto.ArticleDto;
import com.yellowsunn.springblog.domain.dto.AsideArticlesDto;
import com.yellowsunn.springblog.domain.entity.Article;
import com.yellowsunn.springblog.domain.entity.Category;
import com.yellowsunn.springblog.repository.ArticleRepository;
import com.yellowsunn.springblog.repository.CategoryRepository;
import com.yellowsunn.springblog.repository.ImageRepository;
import com.yellowsunn.springblog.service.ArticleService;
import com.yellowsunn.springblog.service.Common;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.yellowsunn.springblog.domain.entity.QArticle.article;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ArticleServiceImpl implements ArticleService {

    private final Common common;

    private final CategoryRepository categoryRepository;
    private final ArticleRepository articleRepository;
    private final ImageRepository imageRepository;

    @Transactional
    @Override
    public ArticleDto findArticle(Long articleId) {
        Optional<Article> articleOptional = articleRepository.findById(articleId);
        if (articleOptional.isEmpty()) return null;

        Article article = articleOptional.get();
        article.updateHit(); // 조회수 증가
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
            builder.thumbnail(common.getServerUrlImage(image.getName()));
        });

        // 부모 카테고리
        Category parentCategory = article.getCategory().getParentCategory();
        if (parentCategory != null) {
            builder.parentCategory(parentCategory.getName());
        }

        // 페이지
        // - 게시글 Id -> 특정 카테고리내의 사이즈 1인 페이지의 번호로 변환
        long idx = articleRepository.findIdxByCategoryAndId(article.getCategory(), articleId);
        PageRequest pageRequest = PageRequest.of((int) idx, 1);
        Page<Long> idPage = articleRepository.findIdByCategory(article.getCategory(), pageRequest);

        builder.totalElements(idPage.getTotalElements())
                .totalPages(idPage.getTotalPages())
                .pageNumber(idPage.getNumber())
                .isFirst(idPage.isFirst())
                .isLast(idPage.isLast())
                .hasNext(idPage.hasNext())
                .hasPrevious(idPage.hasPrevious());

        // 이전 게시글
        if (idPage.hasPrevious()) {
            PageRequest previous = pageRequest.previous();
            Page<Article> prevPage = articleRepository.findByCategory(article.getCategory(), previous);
            Article prevArticle = prevPage.getContent().get(0);
            builder.before(ArticleDto.builder().id(prevArticle.getId()).title(prevArticle.getTitle()).build());
        }

        // 다음 게시글
        if (idPage.hasNext()) {
            Pageable next = pageRequest.next();
            Page<Article> nextPage = articleRepository.findByCategory(article.getCategory(), next);
            Article nextArticle = nextPage.getContent().get(0);
            builder.after(ArticleDto.builder().id(nextArticle.getId()).title(nextArticle.getTitle()).build());
        }

        return builder.build();
    }

    @Override
    public Long findArticleIdByPage(Long categoryId, int pageNumber) {
        Optional<Category> categoryOptional = categoryRepository.findById(categoryId);
        if (categoryOptional.isEmpty()) return null;

        // 특정 카테고리내의 사이즈 1인  페이지 번호 -> 게시글 Id로 변환
        PageRequest pageRequest = PageRequest.of(pageNumber, 1);
        Page<Long> idPage = articleRepository.findIdByCategory(categoryOptional.get(), pageRequest);

        List<Long> content = idPage.getContent();
        return !content.isEmpty() ? content.get(0) : null;
    }

    @Override
    public AsideArticlesDto findAsideArticles() {
        List<Tuple> recentTuples = articleRepository.findVerySimpleArticles(false);
        List<Tuple> popularTuples = articleRepository.findVerySimpleArticles(true);

        List<ArticleDto> recent = new ArrayList<>();
        for (Tuple recentTuple : recentTuples) {
            recent.add(changeVerySimple(recentTuple));
        }
        List<ArticleDto> popular = new ArrayList<>();
        for (Tuple popularTuple : popularTuples) {
            popular.add(changeVerySimple(popularTuple));
        }

        return AsideArticlesDto.builder()
                .recentArticles(recent)
                .popularArticles(popular)
                .build();
    }

    @Override
    public ArticleDto changeSimple(CategoryRepository categoryRepository, Tuple tuple) {
        ArticleDto.ArticleDtoBuilder builder = ArticleDto.builder()
                .id(tuple.get(article.id))
                .title(tuple.get(article.title))
                .summary(common.getSummary(tuple.get(article.content)))
                .commentCount(tuple.get(6, Long.class))
                .thumbnail(common.getServerUrlImage(tuple.get(5, String.class)))
                .simpleDate(tuple.get(article.date));

        Long id = tuple.get(article.category.id);
        if (id != null) {
            categoryRepository.findById(id).ifPresent(c ->
                    builder.categoryId(c.getId())
                            .category(c.getName())
                            .parentCategory(c.getParentCategory() != null ? c.getParentCategory().getName() : null)
            );
        }

        return builder.build();
    }

    public ArticleDto changeVerySimple(Tuple tuple) {
        return ArticleDto.builder()
                .id(tuple.get(article.id))
                .title(tuple.get(article.title))
                .simpleDate(tuple.get(article.date))
                .thumbnail(common.getServerUrlImage(tuple.get(3, String.class)))
                .build();
    }
}
