package com.yellowsunn.springblog.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;

@RestController
public class ImageController {

    @Value("${file.upload.directory}")
    private String uploadPath;

    @GetMapping(value = "/image/{imageName}")
    public ResponseEntity<byte[]> showImage(@PathVariable("imageName") String imageName) throws IOException {
        try {
            FileInputStream fis = new FileInputStream(uploadPath + imageName);
            byte[] bytes = fis.readAllBytes();
            fis.close();
            return ResponseEntity.ok()
                    .cacheControl(CacheControl.maxAge(Duration.ofDays(7L)))
                    .contentType(getImageMediaType(imageName))
                    .body(bytes);
        } catch (FileNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    private MediaType getImageMediaType(String imageName) {
        int pos = imageName.lastIndexOf('.');
        String imageType = imageName.substring(pos + 1);
        if ("gif".equalsIgnoreCase(imageType)) {
            return MediaType.IMAGE_GIF;
        } else if ("png".equalsIgnoreCase(imageType)) {
            return MediaType.IMAGE_PNG;
        } else {
            return MediaType.IMAGE_JPEG;
        }
    }
}
