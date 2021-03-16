package com.yellowsunn.springblog.controller.api;

import com.yellowsunn.springblog.domain.dto.ArticleDto;
import com.yellowsunn.springblog.domain.dto.AsideArticlesDto;
import com.yellowsunn.springblog.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ArticleController {
    private final ArticleService articleService;

    // 게시글 작성 (게시글 Id 반환)
    @PostMapping(value = "/article/create", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Long> upload(ArticleDto articleDto,
                                    @RequestParam(value = "thumbnailFile", required = false) MultipartFile thumbnailFile,
                                    @RequestParam(value = "imageFile", required = false) List<MultipartFile> imageFiles) {
        return articleService.createArticle(articleDto, thumbnailFile, imageFiles);
    }

    // 게시글 수정
    @PutMapping(value = "/article/update", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> update(ArticleDto articleDto,
                                    @RequestParam(value = "thumbnailFile", required = false) MultipartFile thumbnailFile,
                                    @RequestParam(value = "imageFile", required = false) List<MultipartFile> imageFiles) {
        HttpStatus httpStatus = articleService.updateArticle(articleDto, thumbnailFile, imageFiles);
        return new ResponseEntity<>(httpStatus);
    }

    // 게시글 조회
    @GetMapping("/article/{articleId}")
    public ArticleDto findArticle(@PathVariable(value = "articleId") Long articleId) {
        return articleService.findArticle(articleId, getSessionId());
    }

    // 게시글 삭제
    @DeleteMapping("/article/delete/{articleId}")
    public ResponseEntity<?> delete(@PathVariable(value = "articleId") Long articleId) {
        HttpStatus httpStatus = articleService.deleteArticle(articleId);
        return new ResponseEntity<>(httpStatus);
    }

    // 게시글 아이디 조회
    @GetMapping("/article/find")
    public Long findArticleIdByPage(@RequestParam Long categoryId, @RequestParam int page) {
        return articleService.findArticleIdByPage(categoryId, page);
    }

    @GetMapping("/asideArticles")
    public AsideArticlesDto findAsideArticles() {
        return articleService.findAsideArticles();
    }

    // Like 업데이트
    @PutMapping("/article/like/{articleId}")
    public ResponseEntity<?> updateLike(@PathVariable(value = "articleId") Long articleId) {
        HttpStatus httpStatus = articleService.updateLike(articleId, getSessionId());
        return new ResponseEntity<>(httpStatus);
    }

    private String getSessionId() {
        SecurityContext context = SecurityContextHolder.getContext();
        String sessionId = null;
        if (context.getAuthentication() instanceof UsernamePasswordAuthenticationToken) {
            sessionId = "0000";
        } else if (context.getAuthentication() instanceof AnonymousAuthenticationToken) {
            sessionId = ((WebAuthenticationDetails) context.getAuthentication().getDetails()).getSessionId();
        }

        return sessionId;
    }
}
