package com.yellowsunn.springblog.service;

import com.yellowsunn.springblog.domain.dto.CommentDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public interface CommentService {

    Page<CommentDto> findByArticleId(Long articleId, Pageable pageable);

    ResponseEntity<CommentDto> upload(CommentDto commentDto, String ipAddr);

    HttpStatus delete(CommentDto commentDto);

    Long countByArticleId(Long articleId);
}
