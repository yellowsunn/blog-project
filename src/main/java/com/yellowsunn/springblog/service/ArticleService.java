package com.yellowsunn.springblog.service;

import com.yellowsunn.springblog.domain.dto.ArticleDto;

public interface ArticleService {

    ArticleDto findArticle(Long articleId);
}
