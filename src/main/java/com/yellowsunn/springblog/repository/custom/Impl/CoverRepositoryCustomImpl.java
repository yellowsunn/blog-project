package com.yellowsunn.springblog.repository.custom.Impl;

import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.yellowsunn.springblog.repository.custom.CoverRepositoryCustom;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.Optional;

import static com.querydsl.jpa.JPAExpressions.select;
import static com.yellowsunn.springblog.domain.entity.QCategory.category;
import static com.yellowsunn.springblog.domain.entity.QCover.cover;

@Transactional(readOnly = true)
public class CoverRepositoryCustomImpl implements CoverRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public CoverRepositoryCustomImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public Optional<Tuple> findMain() {
        Tuple tuple = queryFactory
                .select(cover.coverCategory, cover.category,
                        select(category.name).from(category).where(category.eq(cover.coverCategory.parentCategory)),
                        select(category.name).from(category).where(category.eq(cover.category.parentCategory))
                )
                .from(cover)
                .leftJoin(cover.coverCategory)
                .leftJoin(cover.category)
                .fetchFirst();

        return Optional.ofNullable(tuple);
    }

    @Override
    public Optional<Tuple> findHeader() {
        Tuple tuple = queryFactory
                .select(cover.title, cover.slogunTitle, cover.slogunText)
                .from(cover)
                .fetchFirst();

        return Optional.ofNullable(tuple);
    }

    @Override
    public Optional<Tuple> findProfile() {
        Tuple tuple = queryFactory
                .select(cover.profile.name, cover.profileText)
                .from(cover)
                .leftJoin(cover.profile)
                .fetchFirst();

        return Optional.ofNullable(tuple);
    }
}
