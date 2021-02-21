package com.yellowsunn.springblog.repository.custom;

import com.yellowsunn.springblog.domain.entity.Article;
import com.yellowsunn.springblog.domain.entity.Category;

import java.util.List;
import java.util.Optional;

public interface ArticleRepositoryCustom {

    Optional<Article> findLatestByCategory(Category category);

    List<Article> findLatest3ByCategory(Category category);
}
