package com.yellowsunn.springblog.repository.custom.Impl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.yellowsunn.springblog.domain.entity.Article;
import com.yellowsunn.springblog.domain.entity.Image;
import com.yellowsunn.springblog.repository.custom.ImageRepositoryCustom;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.Optional;

import static com.yellowsunn.springblog.domain.entity.QImage.image;

@Transactional(readOnly = true)
public class ImageRepositoryCustomImpl implements ImageRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public ImageRepositoryCustomImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public Optional<Image> findThumbnailByArticle(Article article) {
        Image thumbnail = queryFactory
                .selectFrom(image)
                .where(image.article.eq(article), image.isThumbnail.eq(true))
                .fetchFirst();

        return Optional.ofNullable(thumbnail);
    }
}
