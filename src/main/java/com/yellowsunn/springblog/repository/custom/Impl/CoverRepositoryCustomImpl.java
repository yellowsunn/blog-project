package com.yellowsunn.springblog.repository.custom.Impl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.yellowsunn.springblog.domain.entity.Cover;
import com.yellowsunn.springblog.domain.entity.QCover;
import com.yellowsunn.springblog.repository.custom.CoverRepositoryCustom;

import javax.persistence.EntityManager;
import java.util.Optional;

public class CoverRepositoryCustomImpl implements CoverRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public CoverRepositoryCustomImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public Optional<Cover> findFirst() {
        Cover cover = queryFactory
                .selectFrom(QCover.cover)
                .limit(1)
                .fetchFirst();

        return Optional.ofNullable(cover);
    }
}
