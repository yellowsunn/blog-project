package com.yellowsunn.springblog.controller.api;

import com.yellowsunn.springblog.domain.dto.HeaderDto;
import com.yellowsunn.springblog.domain.dto.MainDto;
import com.yellowsunn.springblog.domain.dto.ProfileDto;
import com.yellowsunn.springblog.service.CoverService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;

@RestController
@RequiredArgsConstructor
public class CoverController {

    private final CoverService coverService;

    // 헤더 정보
    @GetMapping("/header")
    public HeaderDto findHeader() {
        return coverService.findHeader();
    }

    // 메인 페이지 정보
    @GetMapping("/")
    public MainDto findCover() {
        return coverService.findMainInfo();
    }

    // 프로필 정보
    @GetMapping("/profile")
    public ProfileDto findProfile() {
        return coverService.findProfile();
    }

    // 헤더 정보 수정
    @PutMapping("/admin/update/header")
    public ResponseEntity<?> updateHeader(@RequestBody HeaderDto headerDto) {
        HttpStatus httpStatus = coverService.updateHeader(headerDto);
        return new ResponseEntity<>(httpStatus);
    }

    // 프로필 정보 수정
}
