package com.yellowsunn.springblog.repository;

import com.yellowsunn.springblog.domain.entity.Category;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.jdbc.Sql;

import static org.assertj.core.api.Assertions.assertThat;

@Sql("/sample_dataset/category.sql")
@DataJpaTest
class CategoryRepositoryTest {

    @Autowired CategoryRepository categoryRepository;
    @Autowired TestEntityManager em;

    @Test
    void setChildCategory() {
        Category category = categoryRepository.findById(1L).get();
        categoryRepository.save(Category.builder()
                .name("child category")
                .parentCategory(category)
                .build());

        em.flush();
        em.clear();

        category = categoryRepository.findById(1L).get();
        Category child = category.getChildCategories().get(0);
        assertThat(child.getName()).isEqualTo("child category");
        assertThat(child.getParentCategory().getId()).isEqualTo(1L);
    }
}