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

    // 현재 카테고리 이름
    private String category;

    // 상위 카테고리 이름
    private String baseCategory;

    @JsonInclude(Include.NON_EMPTY)
    private Long order;

    private List<ArticleDto> articles;

    @Builder

    public CategoryDto(Long totalElements, Integer totalPages, Integer pageNumber, Boolean isFirst, Boolean isLast, Boolean hasNext, Boolean hasPrevious, Long id, String category, String baseCategory, Long order, List<ArticleDto> articles) {
        super(totalElements, totalPages, pageNumber, isFirst, isLast, hasNext, hasPrevious);
        this.id = id;
        this.category = category;
        this.baseCategory = baseCategory;
        this.order = order;
        this.articles = articles;
    }
}
