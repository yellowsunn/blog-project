package com.yellowsunn.springblog.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

import static com.fasterxml.jackson.annotation.JsonInclude.*;

@Data
@Builder
public class ArticleDto {

    private Long id;
    private String title;
    @JsonInclude(Include.NON_EMPTY)
    private String writer;

    @JsonInclude(Include.NON_EMPTY)
    private String content;
    @JsonInclude(Include.NON_EMPTY)
    private String summary;
    @JsonInclude(Include.NON_EMPTY)
    private Long commentCount;

    private String thumbnail;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy.MM.dd", timezone = "Asia/Seoul", locale = "ko")
    private LocalDate simpleDate;

    @JsonInclude(Include.NON_EMPTY)
    private Long like;

    @JsonInclude(Include.NON_EMPTY)
    private Long categoryId;
    @JsonInclude(Include.NON_EMPTY)
    private String parentCategory;
    @JsonInclude(Include.NON_EMPTY)
    private String category;
}
