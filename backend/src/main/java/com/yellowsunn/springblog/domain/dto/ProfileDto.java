package com.yellowsunn.springblog.domain.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor @Builder
@Getter
public class ProfileDto {

    private String profileImage;
    private String profileText;
}
