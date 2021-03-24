package com.yellowsunn.springblog.domain.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = false)
public class CommentHistoryDto extends PageDto {
    private List<CommentDto> list;

    @Builder
    public CommentHistoryDto(Long totalElements, Integer totalPages, Integer pageNumber, Boolean isFirst, Boolean isLast, Boolean hasNext, Boolean hasPrevious, List<CommentDto> list) {
        super(totalElements, totalPages, pageNumber, isFirst, isLast, hasNext, hasPrevious);
        this.list = list;
    }
}
