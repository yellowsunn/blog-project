package com.yellowsunn.springblog.service.impl;

import com.querydsl.core.Tuple;
import com.yellowsunn.springblog.domain.dto.*;
import com.yellowsunn.springblog.domain.entity.Category;
import com.yellowsunn.springblog.repository.ArticleRepository;
import com.yellowsunn.springblog.repository.CategoryRepository;
import com.yellowsunn.springblog.repository.CoverRepository;
import com.yellowsunn.springblog.service.ArticleService;
import com.yellowsunn.springblog.service.Common;
import com.yellowsunn.springblog.service.CoverService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.yellowsunn.springblog.domain.entity.QCover.cover;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CoverServiceImpl implements CoverService {

    private final Common common;

    private final ArticleService articleService;

    private final CategoryRepository categoryRepository;
    private final CoverRepository coverRepository;
    private final ArticleRepository articleRepository;

    @Override
    public HeaderDto findHeader() {
        Optional<Tuple> tupleOptional = coverRepository.findHeader();
        if (tupleOptional.isEmpty()) return null;

        Tuple tuple = tupleOptional.get();
        return HeaderDto.builder()
                .title(tuple.get(cover.title))
                .slogunTitle(tuple.get(cover.slogunTitle))
                .slogunText(tuple.get(cover.slogunText))
                .build();
    }

    @Override
    public MainDto findMainInfo() {

        Optional<Tuple> tupleOptional = coverRepository.findMain();
        if (tupleOptional.isEmpty()) return null;

        Tuple tuple = tupleOptional.get();
        Category coverCategory = tuple.get(cover.coverCategory);
        Category category = tuple.get(cover.category);

        MainDto.MainDtoBuilder builder = MainDto.builder();

        // 커버 게시글
        List<Tuple> simpleArticles = articleRepository.findSimpleArticles(coverCategory, 1);
        if (!simpleArticles.isEmpty()) {
            tuple = simpleArticles.get(0);
            ArticleDto articleDto = articleService.changeSimple(categoryRepository, tuple);
            builder.cover(articleDto);
        }

        // 카테고리 목록
        simpleArticles = articleRepository.findSimpleArticles(category, 3);
        List<ArticleDto> articles = new ArrayList<>();
        for (Tuple t : simpleArticles) {
            ArticleDto articleDto = articleService.changeSimple(categoryRepository, t);
            articles.add(articleDto);
        }

        CategoryDto categoryDto = CategoryDto.builder()
                .id(category != null ? category.getId() : null)
                .category(category != null ? category.getName() : null)
                .articles(articles)
                .build();

        return builder.category(categoryDto).build();
    }

    @Override
    public ProfileDto findProfile() {
        Optional<Tuple> tupleOptional = coverRepository.findProfile();
        if (tupleOptional.isEmpty()) return null;

        Tuple tuple = tupleOptional.get();
        return ProfileDto.builder()
                .profileImage(common.getServerUrlImage(tuple.get(cover.profile.name)))
                .profileText(tuple.get(cover.profileText))
                .build();
    }
}
