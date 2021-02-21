package com.yellowsunn.springblog.controller.api;

import com.yellowsunn.springblog.domain.dto.CoverDto;
import com.yellowsunn.springblog.service.CoverService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TestController {

    private final CoverService coverService;

    @GetMapping(value = "/")
    public CoverDto findCover() {
        return coverService.findCover();
    }
}
