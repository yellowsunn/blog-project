package com.yellowsunn.springblog.controller.api;

import com.yellowsunn.springblog.domain.dto.ArticleDto;
import com.yellowsunn.springblog.domain.dto.AsideArticlesDto;
import com.yellowsunn.springblog.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@CrossOrigin(value = "*")
public class ArticleController {
    private final ArticleService articleService;

    @GetMapping("/article/{articleId}")
    public ArticleDto findArticle(@PathVariable(value = "articleId") Long articleId) {
        return articleService.findArticle(articleId);
    }

    @GetMapping("/article/find")
    public Long findArticleIdByPage(@RequestParam Long categoryId, @RequestParam int page) {
        return articleService.findArticleIdByPage(categoryId, page);
    }

    @GetMapping("/asideArticles")
    public AsideArticlesDto findAsideArticles() {
        return articleService.findAsideArticles();
    }
}
