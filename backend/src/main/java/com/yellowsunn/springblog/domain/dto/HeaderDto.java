package com.yellowsunn.springblog.domain.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor @Builder
public class HeaderDto {

    private String title;
    private String slogunTitle;
    private String slogunText;
}
