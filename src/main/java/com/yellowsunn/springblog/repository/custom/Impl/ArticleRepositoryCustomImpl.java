package com.yellowsunn.springblog.repository.custom.Impl;

import com.querydsl.core.QueryResults;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.yellowsunn.springblog.domain.entity.Article;
import com.yellowsunn.springblog.domain.entity.Category;
import com.yellowsunn.springblog.repository.custom.ArticleRepositoryCustom;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

import static com.yellowsunn.springblog.domain.entity.QArticle.article;

@Transactional(readOnly = true)
public class ArticleRepositoryCustomImpl implements ArticleRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public ArticleRepositoryCustomImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public Optional<Article> findLatestByCategory(Category category) {
        Article latestArticle = queryFactory
                .selectFrom(article)
                .where(article.category.eq(category))
                .orderBy(article.id.desc())
                .fetchFirst();

        return Optional.ofNullable(latestArticle);
    }

    @Override
    public List<Article> findLatest3ByCategoryIn(List<Category> categories) {
        return queryFactory
                .selectFrom(article)
                .where(categoryIn(categories))
                .orderBy(article.id.desc())
                .limit(3)
                .fetch();
    }

    @Override
    public Page<Article> findByCategoryIn(List<Category> categories, Pageable pageable) {
        QueryResults<Article> results = queryFactory
                .selectFrom(article)
                .where(categoryIn(categories))
                .orderBy(article.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchResults();

        return new PageImpl<>(results.getResults(), pageable, results.getTotal());
    }

    private BooleanExpression categoryIn(List<Category> categories) {
        return !categories.isEmpty() ? article.category.in(categories) : null;
    }
}
