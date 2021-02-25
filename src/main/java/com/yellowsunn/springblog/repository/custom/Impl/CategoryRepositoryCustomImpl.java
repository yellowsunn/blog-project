package com.yellowsunn.springblog.repository.custom.Impl;


import com.querydsl.jpa.impl.JPAQueryFactory;
import com.yellowsunn.springblog.domain.entity.Category;
import com.yellowsunn.springblog.repository.custom.CategoryRepositoryCustom;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

import static com.yellowsunn.springblog.domain.entity.QCategory.category;

@Transactional(readOnly = true)
public class CategoryRepositoryCustomImpl implements CategoryRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public CategoryRepositoryCustomImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public List<Category> findChildCategories(Category baseCategory) {
        return queryFactory.selectFrom(category)
                .where(category.parentCategory.eq(baseCategory))
                .fetch();
    }
}
