package com.yellowsunn.springblog.controller.api;

import com.yellowsunn.springblog.domain.dto.HeaderDto;
import com.yellowsunn.springblog.domain.dto.MainDto;
import com.yellowsunn.springblog.service.CoverService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@CrossOrigin(value = "*")
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
}
