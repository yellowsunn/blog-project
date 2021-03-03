package com.yellowsunn.springblog.domain.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class CategoryListDto {
    private List<CategoryDto> list;
    long total;
}
