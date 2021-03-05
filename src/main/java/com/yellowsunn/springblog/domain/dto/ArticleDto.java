package com.yellowsunn.springblog.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.time.LocalDateTime;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;

@Getter @Setter
@ToString
@EqualsAndHashCode(callSuper = false)
public class ArticleDto extends PageDto {

    private Long id;
    private String title;
    @JsonInclude(NON_EMPTY)
    private String writer;

    @JsonInclude(NON_EMPTY)
    private String content;
    @JsonInclude(NON_EMPTY)
    private String summary;
    @JsonInclude(NON_EMPTY)
    private Long commentCount;
    @JsonInclude(NON_EMPTY)
    private String thumbnail;

    @JsonInclude(NON_EMPTY)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy.MM.dd", timezone = "Asia/Seoul", locale = "ko")
    private LocalDateTime simpleDate;
    @JsonInclude(NON_EMPTY)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy.MM.dd. HH:mm", timezone = "Asia/Seoul", locale = "ko")
    private LocalDateTime date;

    @JsonInclude(NON_EMPTY)
    private Long like;
    @JsonInclude(NON_EMPTY)
    private Boolean isAlreadyLike;

    @JsonInclude(NON_EMPTY)
    private Long categoryId;
    @JsonInclude(NON_EMPTY)
    private String parentCategory;
    @JsonInclude(NON_EMPTY)
    private String category;

    @JsonInclude(NON_EMPTY)
    private ArticleDto before;
    @JsonInclude(NON_EMPTY)
    private ArticleDto after;

    @Builder

    public ArticleDto(Long totalElements, Integer totalPages, Integer pageNumber, Boolean isFirst, Boolean isLast, Boolean hasNext, Boolean hasPrevious, Long id, String title, String writer, String content, String summary, Long commentCount, String thumbnail, LocalDateTime simpleDate, LocalDateTime date, Long like, Boolean isAlreadyLike, Long categoryId, String parentCategory, String category, ArticleDto before, ArticleDto after) {
        super(totalElements, totalPages, pageNumber, isFirst, isLast, hasNext, hasPrevious);
        this.id = id;
        this.title = title;
        this.writer = writer;
        this.content = content;
        this.summary = summary;
        this.commentCount = commentCount;
        this.thumbnail = thumbnail;
        this.simpleDate = simpleDate;
        this.date = date;
        this.like = like;
        this.isAlreadyLike = isAlreadyLike;
        this.categoryId = categoryId;
        this.parentCategory = parentCategory;
        this.category = category;
        this.before = before;
        this.after = after;
    }
}
