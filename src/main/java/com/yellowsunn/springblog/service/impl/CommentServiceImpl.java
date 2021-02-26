package com.yellowsunn.springblog.service.impl;

import com.yellowsunn.springblog.domain.dto.CommentDto;
import com.yellowsunn.springblog.domain.entity.Article;
import com.yellowsunn.springblog.domain.entity.Comment;
import com.yellowsunn.springblog.repository.ArticleRepository;
import com.yellowsunn.springblog.repository.CommentRepository;
import com.yellowsunn.springblog.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final ArticleRepository articleRepository;
    private final CommentRepository commentRepository;

    @Override
    public Page<CommentDto> findByArticleId(Long articleId, Pageable pageable) {
        Optional<Article> articleOptional = articleRepository.findById(articleId);
        if (articleOptional.isEmpty()) return null;

        Page<Comment> commentPage = commentRepository.findByArticle(articleOptional.get(), pageable);
        return commentPage.map(comment ->
                CommentDto.builder()
                        .commentId(comment.getId())
                        .parentCommentId(comment.getMainComment() != null ? comment.getMainComment().getId() : null)
                        .name(comment.getName())
                        .content(comment.getContent())
                        .date(comment.getDate())
                        .subComment(comment.getSubComment().stream().map(subComment ->
                                CommentDto.builder()
                                        .commentId(subComment.getId())
                                        .name(subComment.getName())
                                        .content(subComment.getContent())
                                        .date(subComment.getDate())
                                        .build()
                        ).collect(Collectors.toList()))
                        .build()
        );
    }

    @Override
    public Long countByArticleId(Long articleId) {
        Optional<Article> articleOptional = articleRepository.findById(articleId);
        if (articleOptional.isEmpty()) return null;

        return commentRepository.countByArticle(articleOptional.get());
    }
}
