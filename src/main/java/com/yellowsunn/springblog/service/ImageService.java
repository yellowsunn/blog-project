package com.yellowsunn.springblog.service;

import com.yellowsunn.springblog.domain.entity.Image;
import org.springframework.web.multipart.MultipartFile;

public interface ImageService {
    Image uploadImage(MultipartFile imageFile);
}
