package com.yellowsunn.springblog.repository;

import com.yellowsunn.springblog.domain.entity.Category;
import com.yellowsunn.springblog.domain.entity.Cover;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.jdbc.Sql;

import static org.assertj.core.api.Assertions.assertThat;

@Sql({
        "/sample_dataset/category.sql",
        "/sample_dataset/article.sql",
        "/sample_dataset/cover.sql"
})
@DataJpaTest
class CoverRepositoryTest {

    @Autowired CoverRepository coverRepository;
    @Autowired TestEntityManager em;

    @Test
    void findCover() {
        Cover cover = coverRepository.findAll().get(0);

        assertThat(cover.getTitle()).isEqualTo("문화재청");
        assertThat(cover.getSlogunTitle()).isEqualTo("어제를 담아 내일에 전합니다.");
        assertThat(cover.getSlogunText()).isEqualTo("과거와 현재, 시간과 사람 사이에 다리를 놓아<br>우리 시대의 문화를 만들어갑니다.");
        assertThat(cover.getCategory().getName()).isEqualTo("고궁의 밤");
        assertThat(cover.getCoverCategory().getArticles().get(0).getTitle()).isEqualTo("고궁의 밤 - 경복궁");
    }

    @Test
    void update() {
        Cover cover = coverRepository.findAll().get(0);
        cover.setTitle("제목");
        cover.setCategory(em.find(Category.class, 2L));
        em.flush();
        em.clear();

        cover = coverRepository.findAll().get(0);
        assertThat(cover.getTitle()).isEqualTo("제목");
        assertThat(cover.getCategory().getName()).isEqualTo("행사 안내");
    }
}