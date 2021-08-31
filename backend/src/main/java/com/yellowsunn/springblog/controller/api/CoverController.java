package com.yellowsunn.springblog.controller.api;

import com.yellowsunn.springblog.domain.dto.CoverCategoryIdDto;
import com.yellowsunn.springblog.domain.dto.HeaderDto;
import com.yellowsunn.springblog.domain.dto.MainDto;
import com.yellowsunn.springblog.domain.dto.ProfileDto;
import com.yellowsunn.springblog.service.CoverService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class CoverController {

    private final CoverService coverService;

    // 헤더 정보
    @GetMapping("/header")
    public HeaderDto findHeader() {
        return coverService.findHeader();
    }

    // 메인 페이지 정보
    @GetMapping("/info")
    public MainDto findCover() {
        return coverService.findMainInfo();
    }

    // 프로필 정보
    @GetMapping("/profile")
    public ProfileDto findProfile() {
        return coverService.findProfile();
    }

    // 헤더 정보 수정
    @PutMapping("/header")
    public ResponseEntity<?> updateHeader(@RequestBody HeaderDto headerDto) {
        HttpStatus httpStatus = coverService.updateHeader(headerDto);
        return new ResponseEntity<>(httpStatus);
    }

    // 프로필 정보 수정
    @PutMapping(value = "/profile", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> updateProfile(
            ProfileDto profileDto,
            @RequestParam(value = "profileFile", required = false) MultipartFile profileFile) {
        HttpStatus httpStatus = coverService.updateProfile(profileDto, profileFile);
        return new ResponseEntity<>(httpStatus);
    }

    // 커버 카테고리 정보
    @GetMapping("/coverCategoryId")
    public CoverCategoryIdDto findCoverCategoryId() {
        return coverService.findCoverCategoryId();
    }

    // 커버 카테고리 수정
    @PutMapping("/coverCategoryId")
    public ResponseEntity<?> updateCoverCategoryId(@RequestBody CoverCategoryIdDto categoryIdDto) {
        HttpStatus httpStatus = coverService.updateCoverCategoryId(categoryIdDto);
        return new ResponseEntity<>(httpStatus);
    }
}
