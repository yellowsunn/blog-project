package com.yellowsunn.springblog.domain.entity;

import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor @Builder
@Getter
public class Article {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "article_id")
    private Long id;

    private String title;
    private String writer;
    @Column(columnDefinition = "mediumtext")
    private String content;

    @CreatedDate
    private LocalDateTime date;

    @ColumnDefault("0")
    private long hit;

    @ColumnDefault("0")
    @Column(name = "article_like")
    private long like;

    private String likeId; // like를 누른 세션아이디

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    @OneToMany(mappedBy = "article", cascade = CascadeType.REMOVE)
    private List<Comment> comments = new ArrayList<>();

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "thumbnail_id")
    private Image thumbnail;

    @OneToMany(mappedBy = "article", cascade = CascadeType.REMOVE)
    private List<Image> images = new ArrayList<>();

    public void updateHit() {
        hit += 1;
    }

    public void updateLike(String sessionId) {
        like += 1;
        likeId = sessionId;
    }

    public void rollBackLike() {
        like -= 1;
        if (like < 0) like = 0;
        likeId = null;
    }

    public void changeThumbnail(Image thumbnail) {
        this.thumbnail = thumbnail;
    }

    public void changeCategory(Category category) {
        this.category = category;
    }

    public void changeTitle(String title) {
        this.title = title;
    }

    public void changeContent(String content) {
        this.content = content;
    }
}
