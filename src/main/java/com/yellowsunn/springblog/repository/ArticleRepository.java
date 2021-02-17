package com.yellowsunn.springblog.repository;

import com.yellowsunn.springblog.domain.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article, Long> {
}
