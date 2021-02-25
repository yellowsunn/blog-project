package com.yellowsunn.springblog.repository;

import com.yellowsunn.springblog.domain.entity.Category;
import com.yellowsunn.springblog.repository.custom.CategoryRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long>, CategoryRepositoryCustom {
}
