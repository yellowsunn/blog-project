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

    private String parentCategory;

    @JsonInclude(Include.NON_EMPTY)
    private Long order;

    private List<ArticleDto> articles;

    @Builder
    public CategoryDto(Long totalElements, Integer totalPages, Integer pageNumber, Boolean isFirst, Boolean isLast, Boolean hasNext, Boolean hasPrevious, Long id, String category, String parentCategory, Long order, List<ArticleDto> articles) {
        super(totalElements, totalPages, pageNumber, isFirst, isLast, hasNext, hasPrevious);
        this.id = id;
        this.category = category;
        this.parentCategory = parentCategory;
        this.order = order;
        this.articles = articles;
    }
}
