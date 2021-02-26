package com.yellowsunn.springblog.controller.api;

import com.yellowsunn.springblog.domain.dto.ArticleDto;
import com.yellowsunn.springblog.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@CrossOrigin(value = "*")
public class ArticleController {
    private final ArticleService articleService;

    @GetMapping("/article/{articleId}")
    public ArticleDto findArticle(@PathVariable(value = "articleId") Long articleId) {
        return articleService.findArticle(articleId);
    }
}
