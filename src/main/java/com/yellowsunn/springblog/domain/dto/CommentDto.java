package com.yellowsunn.springblog.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;

@Data
@Builder
public class CommentDto {

    private Long commentId;
    private String name;
    private String content;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy.MM.dd. HH:mm", timezone = "Asia/Seoul", locale = "ko")
    private LocalDateTime date;

    @JsonInclude(NON_EMPTY)
    private Long parentCommentId;

    @JsonInclude(NON_EMPTY)
    private List<CommentDto> subComment;
}
