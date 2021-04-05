package com.yellowsunn.springblog.repository.custom;

import com.yellowsunn.springblog.domain.entity.Article;
import com.yellowsunn.springblog.domain.entity.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CommentRepositoryCustom {

    Page<Comment> findByArticle(Article article, Pageable pageable);

    Page<Comment> findCustomAll(Pageable pageable);
}
