package com.yellowsunn.springblog.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor @Builder
public class CoverCategoryIdDto {

    private Long articleCategoryId;
    private Long categoryId;
}
