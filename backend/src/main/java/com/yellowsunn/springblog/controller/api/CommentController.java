package com.yellowsunn.springblog.controller.api;

import com.yellowsunn.springblog.domain.dto.CommentDto;
import com.yellowsunn.springblog.domain.dto.CommentHistoryDto;
import com.yellowsunn.springblog.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    // 게시글의 댓글 조회
    @GetMapping("/comment/{articleId}")
    public Page<CommentDto> findComments(@PathVariable("articleId") Long articleId, Pageable pageable) {
        return commentService.findByArticleId(articleId, pageable);
    }

    // 게시글의 댓글 개수
    @GetMapping("/comment/count/{articleId}")
    public Long countComments(@PathVariable("articleId") Long articleId) {
        return commentService.countByArticleId(articleId);
    }

    // 댓글 업로드
    @PostMapping("/comment/upload")
    public ResponseEntity<CommentDto> commentUpload(@RequestBody CommentDto commentDto, HttpServletRequest request) {
        return commentService.upload(commentDto, request.getRemoteAddr());
    }

    // 댓글 삭제
    @DeleteMapping("/comment/delete")
    public ResponseEntity<?> commentDelete(@RequestBody CommentDto commentDto) {

        HttpStatus httpStatus = commentService.delete(commentDto);
        return new ResponseEntity<>(httpStatus);
    }

    // 댓글 기록 조회 (인증된 사용자만 보기위해 POST)
    @PostMapping("/admin/comment/history")
    public CommentHistoryDto findHistory(Pageable pageable) {
        return commentService.findHistory(pageable);
    }
}
