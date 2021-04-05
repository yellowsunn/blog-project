package com.yellowsunn.springblog.repository.custom.Impl;

import com.querydsl.core.QueryResults;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.yellowsunn.springblog.domain.entity.Article;
import com.yellowsunn.springblog.domain.entity.Comment;
import com.yellowsunn.springblog.repository.custom.CommentRepositoryCustom;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.parameters.P;

import javax.persistence.EntityManager;
import java.util.List;

import static com.yellowsunn.springblog.domain.entity.QComment.comment;

public class CommentRepositoryCustomImpl implements CommentRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public CommentRepositoryCustomImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public Page<Comment> findByArticle(Article article, Pageable pageable) {
        List<Comment> content = queryFactory
                .selectFrom(comment)
                .where(comment.article.eq(article))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(comment.orderNumber.asc())
                .fetch();

        long total = queryFactory
                .selectFrom(comment)
                .where(comment.article.eq(article))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchCount();

        return new PageImpl<>(content, pageable, total);
    }

    @Override
    public Page<Comment> findCustomAll(Pageable pageable) {
        QueryResults<Comment> results = queryFactory
                .selectFrom(comment)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(comment.id.desc())
                .fetchResults();

        return new PageImpl<>(results.getResults(), pageable, results.getTotal());
    }
}
