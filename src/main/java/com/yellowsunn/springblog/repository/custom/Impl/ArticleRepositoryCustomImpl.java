package com.yellowsunn.springblog.repository.custom.Impl;

import com.querydsl.core.QueryResults;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
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
        return simpleArticlesQuery(baseCategory)
                .limit(limit)
                .fetch();
    }

    @Override
    public Page<Tuple> findSimpleArticles(Category baseCategory, Pageable pageable) {
        List<Tuple> content = simpleArticlesQuery(baseCategory)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        long total = queryFactory.selectFrom(article)
                .where(baseCategoryEqual(baseCategory))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchCount();

        return new PageImpl<>(content, pageable, total);
    }

    @Override
    public List<Tuple> findVerySimpleArticles(boolean isPopular) {
        JPAQuery<Tuple> query = queryFactory
                .select(article.id, article.title, article.date,
                        select(image.name).from(image).where(image.article.eq(article), image.isThumbnail.eq(true)).limit(1)
                )
                .from(article);

        if (isPopular) {
            query.orderBy(article.hit.desc(), article.id.desc());
        } else {
            query.orderBy(article.id.desc());
        }

        return query.limit(3).fetch();
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

    private JPAQuery<Tuple> simpleArticlesQuery(Category baseCategory) {
        return queryFactory
                .select(article.id, article.title, article.content, article.date, article.category.id,
                        select(image.name).from(image).where(image.article.eq(article), image.isThumbnail.eq(true)).limit(1),
                        select(comment.count()).from(comment).where(comment.article.eq(article))
                )
                .from(article)
                .where(baseCategoryEqual(baseCategory))
                .orderBy(article.id.desc());
    }

    private BooleanExpression baseCategoryEqual(Category baseCategory) {
        return baseCategory != null ? article.category.in(
                selectFrom(category).where(category.parentCategory.eq(baseCategory).or(category.eq(baseCategory)))
        ) : null;
    }

    private BooleanExpression categoryEqual(Category category) {
        return category != null ? article.category.eq(category) : null;
    }
}
