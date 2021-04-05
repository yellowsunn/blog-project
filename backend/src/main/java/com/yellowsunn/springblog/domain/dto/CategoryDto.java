package com.yellowsunn.springblog.domain.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

import static com.fasterxml.jackson.annotation.JsonInclude.Include;

@Getter @Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = false)
public class CategoryDto extends PageDto {
    private Long id;

    private String category;

    @JsonInclude(Include.NON_EMPTY)
    private Long parentId;

    @JsonInclude(Include.NON_EMPTY)
    private String parentCategory;

    @JsonInclude(Include.NON_EMPTY)
    private List<CategoryDto> children;

    private long order;

    @JsonInclude(Include.NON_EMPTY)
    private List<ArticleDto> articles;

    @JsonInclude(Include.NON_EMPTY)
    private Long totalArticles;

    @JsonInclude(Include.NON_NULL)
    private String search;

    @Builder
    public CategoryDto(Long totalElements, Integer totalPages, Integer pageNumber, Boolean isFirst, Boolean isLast, Boolean hasNext, Boolean hasPrevious, Long id, String category, Long parentId, String parentCategory, List<CategoryDto> children, long order, List<ArticleDto> articles, Long totalArticles, String search) {
        super(totalElements, totalPages, pageNumber, isFirst, isLast, hasNext, hasPrevious);
        this.id = id;
        this.category = category;
        this.parentId = parentId;
        this.parentCategory = parentCategory;
        this.children = children;
        this.order = order;
        this.articles = articles;
        this.totalArticles = totalArticles;
        this.search = search;
    }
}
