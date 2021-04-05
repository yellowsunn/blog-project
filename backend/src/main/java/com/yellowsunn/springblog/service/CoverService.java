package com.yellowsunn.springblog.service;

import com.yellowsunn.springblog.domain.dto.CoverCategoryIdDto;
import com.yellowsunn.springblog.domain.dto.MainDto;
import com.yellowsunn.springblog.domain.dto.HeaderDto;
import com.yellowsunn.springblog.domain.dto.ProfileDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.multipart.MultipartFile;

public interface CoverService {

    HttpStatus updateHeader(HeaderDto headerDto);

    HeaderDto findHeader();

    MainDto findMainInfo();

    HttpStatus updateProfile(ProfileDto profileDto, MultipartFile profileFile);

    ProfileDto findProfile();

    CoverCategoryIdDto findCoverCategoryId();

    HttpStatus updateCoverCategoryId(CoverCategoryIdDto categoryIdDto);
}
