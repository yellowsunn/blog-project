package com.yellowsunn.springblog.domain.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
public class Cover {
    @Setter(AccessLevel.NONE)
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cover_id")
    private Long id;

    private String title;
    private String slogunTitle;
    private String slogunText;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cover_category_id")
    private Category coverCategory;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "profile_id")
    private Image profile;
    private String profileText;

    public void changeTitle(String title) {
        this.title = title;
    }

    public void changeSlogunTitle(String slogunTitle) {
        this.slogunTitle = slogunTitle;
    }

    public void changeSlogunText(String slogunText) {
        this.slogunText = slogunText;
    }
}
