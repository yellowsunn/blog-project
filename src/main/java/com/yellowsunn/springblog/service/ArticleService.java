package com.yellowsunn.springblog.service;

import com.querydsl.core.Tuple;
import com.yellowsunn.springblog.domain.dto.ArticleDto;
import com.yellowsunn.springblog.domain.dto.AsideArticlesDto;
import com.yellowsunn.springblog.repository.CategoryRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ArticleService {

    ResponseEntity<Long> createArticle(ArticleDto articleDto, MultipartFile thumbnailFile, List<MultipartFile> imageFiles);

    HttpStatus updateArticle(ArticleDto articleDto, MultipartFile thumbnailFile, List<MultipartFile> imageFiles);

    ArticleDto findArticle(Long articleId, String sessionId);

    HttpStatus deleteArticle(Long articleId);

    // 페이지 번호로 게시글 찾기
    Long findArticleIdByPage(Long categoryId, int pageNumber);

    AsideArticlesDto findAsideArticles();

    HttpStatus updateLike(Long articleId, String sessionId);

    // 간단한 게시글로 변환
    ArticleDto changeSimple(CategoryRepository categoryRepository, Tuple tuple);
}
