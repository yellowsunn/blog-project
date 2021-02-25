package com.yellowsunn.springblog.service.impl;

import com.yellowsunn.springblog.domain.dto.ArticleDto;
import com.yellowsunn.springblog.domain.dto.CategoryDto;
import com.yellowsunn.springblog.domain.dto.HeaderDto;
import com.yellowsunn.springblog.domain.dto.MainDto;
import com.yellowsunn.springblog.domain.entity.Category;
import com.yellowsunn.springblog.domain.entity.Cover;
import com.yellowsunn.springblog.repository.ArticleRepository;
import com.yellowsunn.springblog.repository.CoverRepository;
import com.yellowsunn.springblog.service.CategoryService;
import com.yellowsunn.springblog.service.CoverService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class CoverServiceImpl implements CoverService {

    private final CategoryService categoryService;

    private final CoverRepository coverRepository;
    private final ArticleRepository articleRepository;

    @Transactional(readOnly = true)
    @Override
    public HeaderDto findHeader() {
        Optional<Cover> coverOptional = coverRepository.findFirst();
        if (coverOptional.isEmpty()) {
            return HeaderDto.builder().build();
        }

        Cover cover = coverOptional.get();
        return HeaderDto.builder()
                .title(cover.getTitle())
                .slogunTitle(cover.getSlogunTitle())
                .slogunText(cover.getSlogunText())
                .build();
    }

    @Transactional(readOnly = true)
    @Override
    public MainDto findMainInfo() {
        Optional<Cover> coverOptional = coverRepository.findFirst();
        if (coverOptional.isEmpty()) {
            return MainDto.builder().build();
        }

        Cover cover = coverOptional.get();
        MainDto.MainDtoBuilder builder = MainDto.builder();

        // 커버 게시글
        if (cover.getCoverCategory() != null) {
            articleRepository.findLatestByCategory(cover.getCoverCategory())
                    .ifPresent(article -> {
                        ArticleDto articleDto = categoryService.getSimpleArticleDto(article);

                        // 상위 카테고리
                        Category parentCategory = cover.getCoverCategory().getParentCategory();
                        if (parentCategory == null) parentCategory = cover.getCoverCategory();
                        articleDto.setParentCategory(parentCategory.getName());
                        builder.cover(articleDto);
                    });
        }

        // 커버 카테고리 (카테고리가 null 이면 전체 글에서 선택)
        CategoryDto categoryDto = categoryService.findArticles(cover.getCategory() != null ? cover.getCategory().getId() : 0L, null);
        return builder.category(categoryDto).build();
    }
}
