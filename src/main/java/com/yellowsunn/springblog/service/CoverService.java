package com.yellowsunn.springblog.service;

import com.yellowsunn.springblog.domain.dto.MainDto;
import com.yellowsunn.springblog.domain.dto.HeaderDto;
import com.yellowsunn.springblog.domain.dto.ProfileDto;
import org.springframework.http.HttpStatus;

public interface CoverService {

    HttpStatus updateHeader(HeaderDto headerDto);

    HeaderDto findHeader();

    MainDto findMainInfo();

    ProfileDto findProfile();
}
