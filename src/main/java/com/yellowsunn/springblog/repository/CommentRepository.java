package com.yellowsunn.springblog.repository;

import com.yellowsunn.springblog.domain.entity.Article;
import com.yellowsunn.springblog.domain.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    long countByArticle(Article article);
}
