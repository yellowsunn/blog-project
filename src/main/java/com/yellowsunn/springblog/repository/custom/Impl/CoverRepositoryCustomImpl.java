package com.yellowsunn.springblog.repository.custom.Impl;

import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.yellowsunn.springblog.repository.custom.CoverRepositoryCustom;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.Optional;

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
                .select(cover.coverArticleCategory, cover.coverCategory)
                .from(cover)
                .leftJoin(cover.coverArticleCategory)
                .leftJoin(cover.coverCategory)
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

    @Override
    public Optional<Tuple> findCoverCategory() {
        Tuple tuple = queryFactory
                .select(cover.coverArticleCategory.id, cover.coverCategory.id)
                .from(cover)
                .fetchFirst();

        return Optional.ofNullable(tuple);
    }
}
