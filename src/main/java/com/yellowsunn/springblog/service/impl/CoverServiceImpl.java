package com.yellowsunn.springblog.service.impl;

import com.yellowsunn.springblog.domain.dto.ArticleDto;
import com.yellowsunn.springblog.domain.dto.CategoryDto;
import com.yellowsunn.springblog.domain.dto.CoverDto;
import com.yellowsunn.springblog.domain.entity.Article;
import com.yellowsunn.springblog.domain.entity.Category;
import com.yellowsunn.springblog.domain.entity.Cover;
import com.yellowsunn.springblog.repository.ArticleRepository;
import com.yellowsunn.springblog.repository.CoverRepository;
import com.yellowsunn.springblog.service.CoverService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class CoverServiceImpl implements CoverService {

    private final CoverRepository coverRepository;
    private final ArticleRepository articleRepository;

    @Transactional(readOnly = true)
    @Override
    public CoverDto findCover() {
        Optional<Cover> coverOptional = coverRepository.findFirst();
        if (coverOptional.isEmpty()) {
            return CoverDto.builder().build();
        }

        Cover cover = coverOptional.get();
        CoverDto.CoverDtoBuilder builder = CoverDto.builder()
                .title(cover.getTitle())
                .slogunTitle(cover.getSlogunTitle())
                .slogunText(cover.getSlogunText());

        // 커버 게시글
        if (cover.getCoverCategory() != null) {
            articleRepository.findLatestByCategory(cover.getCoverCategory())
                    .ifPresent(article -> {
                        ArticleDto articleDto = getSimpleArticleDto(article);
                        builder.cover(articleDto);
                    });
        }

        // 커버 카테고리
        Category category = cover.getCategory();
        if (category != null) {
            List<ArticleDto> articleDtoList = new ArrayList<>();
            for (Article article : articleRepository.findLatest3ByCategory(category)) {
                articleDtoList.add(getSimpleArticleDto(article));
            }

            CategoryDto categoryDto = CategoryDto.builder()
                    .id(category.getId())
                    .name(category.getName())
                    .articles(articleDtoList)
                    .build();
            builder.category(categoryDto);
        }

        return builder.build();
    }

    private String removeTag(String html) {
        return html.replaceAll("(<([^>]+)>)", "");
    }

    private ArticleDto getSimpleArticleDto(Article article) {
        String content = removeTag(article.getContent());
        if (content.length() > 200) {
            content = content.substring(0, 200);
        }
        Category category = article.getCategory();

        return ArticleDto.builder()
                .categoryId(category.getId())
                .categoryName(category.getName())
                .id(article.getId())
                .title(article.getTitle())
                .summary(content)
                .simpleDate(article.getDate())
                .build();
    }
}
