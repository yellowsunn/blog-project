package com.yellowsunn.springblog.service;

import com.querydsl.core.Tuple;
import com.yellowsunn.springblog.domain.dto.ArticleDto;
import com.yellowsunn.springblog.domain.dto.AsideArticlesDto;
import com.yellowsunn.springblog.repository.CategoryRepository;

public interface ArticleService {

    ArticleDto findArticle(Long articleId);

    // 페이지 번호로 게시글 찾기
    Long findArticleIdByPage(Long categoryId, int pageNumber);

    AsideArticlesDto findAsideArticles();

    // 간단한 게시글로 변환
    ArticleDto changeSimple(CategoryRepository categoryRepository, Tuple tuple);
}
