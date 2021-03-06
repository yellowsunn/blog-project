package com.yellowsunn.springblog.domain.entity;

import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor @Builder
@Getter
public class Category {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Long id;

    @Column(nullable = false)
    private String name;

    @ColumnDefault("0")
    @Column(name = "orders")
    private long order;

    @OneToMany(mappedBy = "category")
    private List<Article> articles = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_category_id")
    private Category parentCategory;

    @OneToMany(mappedBy = "parentCategory")
    private List<Category> childCategories = new ArrayList<>();

    public void changeName(String name) {
        this.name = name;
    }

    public void changeOrder(long order) {
        this.order = order;
    }

    public void changeParentCategory(Category parentCategory) {
        this.parentCategory = parentCategory;
    }
}
