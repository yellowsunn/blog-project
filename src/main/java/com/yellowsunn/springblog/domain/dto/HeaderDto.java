package com.yellowsunn.springblog.domain.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class HeaderDto {

    private String title;
    private String slogunTitle;
    private String slogunText;
}
