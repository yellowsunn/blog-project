package com.yellowsunn.springblog.repository.custom.Impl;


import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.yellowsunn.springblog.domain.entity.QCategory;
import com.yellowsunn.springblog.repository.custom.CategoryRepositoryCustom;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.ArrayDeque;
import java.util.List;
import java.util.Queue;

import static com.querydsl.jpa.JPAExpressions.select;
import static com.yellowsunn.springblog.domain.entity.QArticle.article;
import static com.yellowsunn.springblog.domain.entity.QCategory.category;

@Transactional(readOnly = true)
public class CategoryRepositoryCustomImpl implements CategoryRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public CategoryRepositoryCustomImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public Queue<Tuple> findCategoryList() {
        List<Tuple> list = queryFactory
                .select(category,
                        select(article.count()).from(article).where(article.category.eq(category))
                )
                .from(category)
                .orderBy(category.parentCategory.id.asc(), category.order.asc(), category.id.asc())
                .fetch();

        return new ArrayDeque<>(list);
    }
}
