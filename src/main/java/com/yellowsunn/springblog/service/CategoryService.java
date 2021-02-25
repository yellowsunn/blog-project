package com.yellowsunn.springblog.service;

import com.yellowsunn.springblog.domain.dto.ArticleDto;
import com.yellowsunn.springblog.domain.dto.CategoryDto;
import com.yellowsunn.springblog.domain.entity.Article;
import org.springframework.data.domain.Pageable;

public interface CategoryService {

    CategoryDto findArticles(Long categoryId, Pageable pageable);

    // 게시글을 단순화
    ArticleDto getSimpleArticleDto(Article article);
}
