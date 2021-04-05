package com.yellowsunn.springblog.controller.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SecurityController {

    @GetMapping("/api/authority")
    public ResponseEntity<?> authority() {
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
