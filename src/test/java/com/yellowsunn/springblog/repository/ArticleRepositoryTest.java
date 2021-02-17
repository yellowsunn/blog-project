package com.yellowsunn.springblog.repository;

import com.yellowsunn.springblog.domain.entity.Article;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@Sql({
        "/sample_dataset/category.sql",
        "/sample_dataset/article.sql",
})
@DataJpaTest
class ArticleRepositoryTest {

    @Autowired ArticleRepository articleRepository;
    @Autowired TestEntityManager em;

    @Test
    void findAll() {
        List<Article> articles = articleRepository.findAll();
        assertThat(articles.get(0).getTitle()).isEqualTo("고궁의 밤 - 경복궁");
        assertThat(articles.get(1).getTitle()).isEqualTo("세계에 활짝 열린 실감형 궁궐체험 앱 ‘창덕궁’");
    }

    @Test
    void update() {
        Article article = articleRepository.findById(1L).get();
        article.addLike();
        assertThat(article.getLike()).isEqualTo(1L);
        article.revertLike();
        assertThat(article.getLike()).isEqualTo(0L);

        article.changeTitle("title");
        article.changeContent("content");
        em.flush();
        em.clear();

        article = articleRepository.findById(1L).get();
        assertThat(article.getTitle()).isEqualTo("title");
        assertThat(article.getContent()).isEqualTo("content");
    }
}