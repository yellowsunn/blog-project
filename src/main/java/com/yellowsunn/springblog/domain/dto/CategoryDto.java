package com.yellowsunn.springblog.domain.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.List;

import static com.fasterxml.jackson.annotation.JsonInclude.Include;

@Getter @Setter
@ToString
@EqualsAndHashCode(callSuper = false)
public class CategoryDto extends PageDto {
    private Long id;

    private String category;

    @JsonInclude(Include.NON_EMPTY)
    private String parentCategory;

    private List<CategoryDto> children;

    @JsonInclude(Include.NON_EMPTY)
    private Long order;

    @JsonInclude(Include.NON_EMPTY)
    private List<ArticleDto> articles;

    @JsonInclude(Include.NON_EMPTY)
    private Long totalArticles;

    @Builder
    public CategoryDto(Long totalElements, Integer totalPages, Integer pageNumber, Boolean isFirst, Boolean isLast, Boolean hasNext, Boolean hasPrevious, Long id, String category, String parentCategory, List<CategoryDto> children, Long order, List<ArticleDto> articles, Long totalArticles) {
        super(totalElements, totalPages, pageNumber, isFirst, isLast, hasNext, hasPrevious);
        this.id = id;
        this.category = category;
        this.parentCategory = parentCategory;
        this.children = children;
        this.order = order;
        this.articles = articles;
        this.totalArticles = totalArticles;
    }
}
