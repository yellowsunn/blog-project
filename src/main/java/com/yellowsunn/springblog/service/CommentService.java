package com.yellowsunn.springblog.service;

import com.yellowsunn.springblog.domain.dto.CommentDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CommentService {

    Page<CommentDto> findByArticleId(Long articleId, Pageable pageable);

    Long countByArticleId(Long articleId);
}
