package com.yellowsunn.springblog.service;

import com.yellowsunn.springblog.domain.dto.ArticleDto;

public interface ArticleService {

    ArticleDto findArticle(Long articleId);

    // 페이지 번호로 게시글 찾기
    Long findArticleIdByPage(Long categoryId, int pageNumber);
}
