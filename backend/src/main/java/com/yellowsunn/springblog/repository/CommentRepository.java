package com.yellowsunn.springblog.repository;

import com.yellowsunn.springblog.domain.entity.Article;
import com.yellowsunn.springblog.domain.entity.Comment;
import com.yellowsunn.springblog.repository.custom.CommentRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long>, CommentRepositoryCustom {

    long countByArticle(Article article);
}
