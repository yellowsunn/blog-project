package com.yellowsunn.springblog.domain.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class AsideArticlesDto {
    List<ArticleDto> recentArticles;
    List<ArticleDto> popularArticles;
}
