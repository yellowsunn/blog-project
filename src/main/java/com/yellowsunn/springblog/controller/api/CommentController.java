package com.yellowsunn.springblog.controller.api;

import com.yellowsunn.springblog.domain.dto.CommentDto;
import com.yellowsunn.springblog.repository.CommentRepository;
import com.yellowsunn.springblog.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@CrossOrigin("*")
public class CommentController {

    private final CommentService commentService;

    @GetMapping("/comment/{articleId}")
    public Page<CommentDto> findComments(@PathVariable("articleId") Long articleId, Pageable pageable) {
        return commentService.findByArticleId(articleId, pageable);
    }

    @GetMapping("/comment/count/{articleId}")
    public Long countComments(@PathVariable("articleId") Long articleId) {
        return commentService.countByArticleId(articleId);
    }
}
