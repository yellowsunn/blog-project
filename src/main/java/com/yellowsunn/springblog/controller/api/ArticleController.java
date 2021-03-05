package com.yellowsunn.springblog.controller.api;

import com.yellowsunn.springblog.domain.dto.ArticleDto;
import com.yellowsunn.springblog.domain.dto.AsideArticlesDto;
import com.yellowsunn.springblog.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@CrossOrigin(value = "*")
public class ArticleController {
    private final ArticleService articleService;

    @GetMapping("/article/{articleId}")
    public ArticleDto findArticle(@PathVariable(value = "articleId") Long articleId) {
        // 익명 사용자의 세션 아이디
        SecurityContext context = SecurityContextHolder.getContext();
        String sessionId = ((WebAuthenticationDetails) context.getAuthentication().getDetails()).getSessionId();

        return articleService.findArticle(articleId, sessionId);
    }

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
        // 익명 사용자의 세션 아이디
        SecurityContext context = SecurityContextHolder.getContext();
        String sessionId = ((WebAuthenticationDetails) context.getAuthentication().getDetails()).getSessionId();

        HttpStatus httpStatus = articleService.updateLike(articleId, sessionId);
        return new ResponseEntity<>(httpStatus);
    }
}
