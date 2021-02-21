package com.yellowsunn.springblog.domain.entity;

import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDate;
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
    private LocalDate date;

    @ColumnDefault("0")
    @Column(name = "article_like")
    private long like;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "thumbnail_id")
    private Image thumbnailImage;

    @OneToMany(mappedBy = "article", cascade = CascadeType.REMOVE)
    private List<Image> images = new ArrayList<>();

    public void addLike() {
        like += 1;
    }

    public void revertLike() {
        like -= 1;
        if (like < 0) like = 0;
    }

    public void changeTitle(String title) {
        this.title = title;
    }

    public void changeContent(String content) {
        this.content = content;
    }
}
