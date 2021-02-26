package com.yellowsunn.springblog.repository.custom.Impl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.yellowsunn.springblog.domain.entity.Cover;
import com.yellowsunn.springblog.domain.entity.QCover;
import com.yellowsunn.springblog.repository.custom.CoverRepositoryCustom;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.Optional;

import static com.yellowsunn.springblog.domain.entity.QCover.*;

@Transactional(readOnly = true)
public class CoverRepositoryCustomImpl implements CoverRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public CoverRepositoryCustomImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public Optional<Cover> findFirst() {
        Cover findCover = queryFactory
                .selectFrom(cover)
                .leftJoin(cover.category).fetchJoin()
                .leftJoin(cover.coverCategory).fetchJoin()
                .limit(1)
                .fetchFirst();

        return Optional.ofNullable(findCover);
    }
}
