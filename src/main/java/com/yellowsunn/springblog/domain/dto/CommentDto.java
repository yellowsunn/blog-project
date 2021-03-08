package com.yellowsunn.springblog.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;

@Data
@NoArgsConstructor
@AllArgsConstructor @Builder
public class CommentDto {

    private Long commentId;
    private String name;
    private String content;
    @JsonInclude(NON_EMPTY)
    private String password;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy.MM.dd. HH:mm", timezone = "Asia/Seoul", locale = "ko")
    private LocalDateTime date;

    @JsonInclude(NON_EMPTY)
    private Long articleId;

    @JsonInclude(NON_EMPTY)
    private Long parentCommentId;

    private List<CommentDto> subComment;
}
