package com.yellowsunn.springblog.repository.custom;

import com.querydsl.core.Tuple;
import com.yellowsunn.springblog.domain.entity.Article;
import com.yellowsunn.springblog.domain.entity.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ArticleRepositoryCustom {

    List<Tuple> findSimpleArticles(Category baseCategory, Integer limit);

    List<Article> findLatest3ByCategoryIn(List<Category> categories);

    Page<Article> findByCategoryIn(List<Category> categories, Pageable pageable);

    Page<Article> findByCategory(Category category, Pageable pageable);

    Page<Long> findIdByCategory(Category category, Pageable pageable);

    // 카테고리에서 최신순으로 몇번째 게시글인지 인덱스를 구한다.
    long findIdxByCategoryAndId(Category category, Long id);
}
