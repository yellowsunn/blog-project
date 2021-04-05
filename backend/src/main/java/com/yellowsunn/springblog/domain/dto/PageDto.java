package com.yellowsunn.springblog.domain.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageDto {

    @JsonInclude(NON_EMPTY)
    private Long totalElements;

    @JsonInclude(NON_EMPTY)
    private Integer totalPages;
    @JsonInclude(NON_EMPTY)
    private Integer pageNumber;

    @JsonInclude(NON_EMPTY)
    private Boolean isFirst;
    @JsonInclude(NON_EMPTY)
    private Boolean isLast;

    @JsonInclude(NON_EMPTY)
    private Boolean hasNext;
    @JsonInclude(NON_EMPTY)
    private Boolean hasPrevious;
}
