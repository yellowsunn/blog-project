package com.yellowsunn.springblog.service;

import com.yellowsunn.springblog.domain.dto.MainDto;
import com.yellowsunn.springblog.domain.dto.HeaderDto;

public interface CoverService {

    HeaderDto findHeader();

    MainDto findMainInfo();
}
