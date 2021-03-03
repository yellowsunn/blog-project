package com.yellowsunn.springblog.service;

import com.yellowsunn.springblog.domain.dto.MainDto;
import com.yellowsunn.springblog.domain.dto.HeaderDto;
import com.yellowsunn.springblog.domain.dto.ProfileDto;

public interface CoverService {

    HeaderDto findHeader();

    MainDto findMainInfo();

    ProfileDto findProfile();
}
