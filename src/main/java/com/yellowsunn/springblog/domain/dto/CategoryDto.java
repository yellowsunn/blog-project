package com.yellowsunn.springblog.domain.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import java.util.List;

import static com.fasterxml.jackson.annotation.JsonInclude.*;

@Data
@Builder
public class CategoryDto {

    private Long id;
    // 상위 카테고리 이름
    private String name;

    @JsonInclude(Include.NON_EMPTY)
    private Long order;

    private List<ArticleDto> articles;
}
