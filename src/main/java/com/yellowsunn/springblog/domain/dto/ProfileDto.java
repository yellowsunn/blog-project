package com.yellowsunn.springblog.domain.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProfileDto {

    String profileImage;
    String profileText;
}
