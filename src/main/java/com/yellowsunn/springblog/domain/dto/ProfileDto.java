package com.yellowsunn.springblog.domain.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Data
@Builder
@Getter
public class ProfileDto {

    private String profileImage;
    private String profileText;
}
