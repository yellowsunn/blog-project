package com.yellowsunn.springblog.domain.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CoverDto {

    private String title;
    private String slogunTitle;
    private String slogunText;

    // 커버에 보이는 게시글
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private ArticleDto cover;

    // 카테고리 목록
    private CategoryDto category;
}
