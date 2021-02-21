package com.yellowsunn.springblog.domain.entity;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class Image {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "image_id")
    private Long id;

    @Column(nullable = false)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "article_id")
    private Article article;

    private boolean isThumbnail;
}
