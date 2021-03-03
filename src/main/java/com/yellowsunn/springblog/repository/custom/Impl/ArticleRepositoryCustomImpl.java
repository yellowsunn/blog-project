package com.yellowsunn.springblog.repository.custom.Impl;

import com.querydsl.core.QueryResults;
import com.querydsl.core.Tuple;
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

import static com.querydsl.jpa.JPAExpressions.select;
import static com.querydsl.jpa.JPAExpressions.selectFrom;
import static com.yellowsunn.springblog.domain.entity.QArticle.article;
import static com.yellowsunn.springblog.domain.entity.QCategory.category;
import static com.yellowsunn.springblog.domain.entity.QComment.comment;
import static com.yellowsunn.springblog.domain.entity.QImage.image;

@Transactional(readOnly = true)
public class ArticleRepositoryCustomImpl implements ArticleRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public ArticleRepositoryCustomImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public List<Tuple> findSimpleArticles(Category baseCategory, Integer limit) {
        return queryFactory
                .select(article.id, article.title, article.content, article.date, article.category.id,
                        select(image.name).from(image).where(image.article.eq(article) ,image.isThumbnail.eq(true)).limit(1),
                        select(comment.count()).from(comment).where(comment.article.eq(article))
                )
                .from(article)
                .where(baseCategoryEqual(baseCategory))
                .orderBy(article.id.desc())
                .limit(limit)
                .fetch();
    }

    @Override
    public List<Article> findLatest3ByCategoryIn(List<Category> categories) {
        return queryFactory
                .selectFrom(article)
                .join(article.category).fetchJoin()
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

    @Override
    public Page<Article> findByCategory(Category category, Pageable pageable) {
        QueryResults<Article> results = queryFactory
                .selectFrom(article)
                .where(categoryEqual(category))
                .orderBy(article.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchResults();

        return new PageImpl<>(results.getResults(), pageable, results.getTotal());
    }

    @Override
    public Page<Long> findIdByCategory(Category category, Pageable pageable) {
        QueryResults<Long> results = queryFactory
                .select(article.id)
                .from(article)
                .where(categoryEqual(category))
                .orderBy(article.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchResults();

        return new PageImpl<>(results.getResults(), pageable, results.getTotal());
    }

    @Override
    public long findIdxByCategoryAndId(Category category, Long id) {
        long count = queryFactory
                .selectFrom(article)
                .where(categoryEqual(category), article.id.goe(id))
                .orderBy(article.id.desc())
                .fetchCount();

        return count - 1;
    }

    private BooleanExpression baseCategoryEqual(Category baseCategory) {
        return baseCategory != null ? article.category.in(
                selectFrom(category).where(category.parentCategory.eq(baseCategory).or(category.eq(baseCategory)))
        ) : null;
    }

    private BooleanExpression categoryEqual(Category category) {
        return category != null ? article.category.eq(category) : null;
    }

    private BooleanExpression categoryIn(List<Category> categories) {
        return !categories.isEmpty() ? article.category.in(categories) : null;
    }
}
