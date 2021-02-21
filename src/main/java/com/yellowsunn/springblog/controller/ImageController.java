package com.yellowsunn.springblog.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.FileInputStream;
import java.io.IOException;

@RestController
public class ImageController {

    @Value("${file.upload.directory}")
    private String uploadPath;

    @GetMapping(value = "/image/{imageName}", produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<byte[]> showImage(@PathVariable("imageName") String imageName) throws IOException {
        FileInputStream fis = new FileInputStream(uploadPath + imageName);
        byte[] bytes = fis.readAllBytes();
        fis.close();
        return new ResponseEntity<>(bytes, HttpStatus.OK);
    }
}
