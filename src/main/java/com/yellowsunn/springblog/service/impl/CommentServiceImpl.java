package com.yellowsunn.springblog.service.impl;

import com.yellowsunn.springblog.domain.dto.CommentDto;
import com.yellowsunn.springblog.domain.entity.Article;
import com.yellowsunn.springblog.domain.entity.Comment;
import com.yellowsunn.springblog.repository.ArticleRepository;
import com.yellowsunn.springblog.repository.CommentRepository;
import com.yellowsunn.springblog.repository.CoverRepository;
import com.yellowsunn.springblog.service.CommentService;
import com.yellowsunn.springblog.service.Common;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final Common common;
    private final ArticleRepository articleRepository;
    private final CommentRepository commentRepository;
    private final PasswordEncoder passwordEncoder;

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
                        .isManager(comment.isManager())
                        .date(comment.getDate())
                        .subComment(comment.getSubComment().stream().map(subComment ->
                                CommentDto.builder()
                                        .commentId(subComment.getId())
                                        .name(subComment.getName())
                                        .content(subComment.getContent())
                                        .isManager(subComment.isManager())
                                        .date(subComment.getDate())
                                        .build()
                        ).collect(Collectors.toList()))
                        .build()
        );
    }

    @Transactional
    @Override
    public ResponseEntity<CommentDto> upload(CommentDto commentDto, String ipAddr) {
        Long articleId = commentDto.getArticleId();
        Optional<Article> articleOptional = articleRepository.findById(articleId);
        if (articleOptional.isEmpty()) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        Comment.CommentBuilder builder = Comment.builder()
                .article(articleOptional.get());

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth instanceof UsernamePasswordAuthenticationToken) {
            builder.name(auth.getName())
                    .isManager(true);
        } else if (auth instanceof AnonymousAuthenticationToken) {
            builder.name(commentDto.getName())
                    .password(passwordEncoder.encode(commentDto.getPassword()))
                    .ipAddr(ipAddr);
        }
        builder.content(common.replaceNewLine(commentDto.getContent()));

        Long parentCommentId = commentDto.getParentCommentId();
        if (parentCommentId != null) {
            Optional<Comment> commentOptional = commentRepository.findById(parentCommentId);
            if (commentOptional.isEmpty()) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

            builder.mainComment(commentOptional.get());
        }

        Comment comment = builder.build();
        Comment saveComment = commentRepository.save(comment);

        // orderNumber : '메인 댓글 Id'_'서브 댓글 Id'
        String orderNumber = String.format("%09d", saveComment.getId());
        if (saveComment.getMainComment() != null) {
            orderNumber = String.format("%09d", saveComment.getMainComment().getId()) + "_" + orderNumber;
        }
        // orderNumber 업데이트
        comment.updateOrderNumber(orderNumber);

        CommentDto dto = CommentDto.builder()
                .commentId(saveComment.getId())
                .name(saveComment.getName())
                .content(saveComment.getContent())
                .isManager(saveComment.isManager())
                .date(saveComment.getDate())
                .parentCommentId(saveComment.getMainComment() != null ? saveComment.getMainComment().getId() : null)
                .build();

        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    @Transactional
    @Override
    public HttpStatus delete(CommentDto commentDto) {
        Optional<Comment> commentOptional = commentRepository.findById(commentDto.getCommentId());
        if (commentOptional.isEmpty()) return HttpStatus.BAD_REQUEST;
        Comment comment = commentOptional.get();

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        // 관리자는 바로 삭제 가능
        if (auth instanceof UsernamePasswordAuthenticationToken) {
            commentRepository.delete(comment);
            return HttpStatus.OK;
        } else if (auth instanceof AnonymousAuthenticationToken) {
            if (passwordEncoder.matches(commentDto.getPassword(), comment.getPassword())) {
                commentRepository.delete(comment);
                return HttpStatus.OK;
            } else {
                return HttpStatus.FORBIDDEN;
            }
        }

        return HttpStatus.INTERNAL_SERVER_ERROR;
    }

    @Override
    public Long countByArticleId(Long articleId) {
        Optional<Article> articleOptional = articleRepository.findById(articleId);
        if (articleOptional.isEmpty()) return null;

        return commentRepository.countByArticle(articleOptional.get());
    }
}
