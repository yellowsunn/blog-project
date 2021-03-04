package com.yellowsunn.springblog.repository.custom;

import com.querydsl.core.Tuple;
import com.yellowsunn.springblog.domain.entity.Article;
import com.yellowsunn.springblog.domain.entity.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ArticleRepositoryCustom {

    List<Tuple> findSimpleArticles(Category baseCategory, Integer limit);

    Page<Tuple> findSimpleArticles(Category baseCategory, Pageable pageable);

    // 사이드바에 있는 매우 간략한 정보의 게시글
    List<Tuple> findVerySimpleArticles(boolean isPopular);

    Page<Article> findByCategory(Category category, Pageable pageable);

    Page<Long> findIdByCategory(Category category, Pageable pageable);

    // 카테고리에서 최신순으로 몇번째 게시글인지 인덱스를 구한다.
    long findIdxByCategoryAndId(Category category, Long id);
}
