package com.yellowsunn.springblog.service.impl;

import com.yellowsunn.springblog.domain.entity.Image;
import com.yellowsunn.springblog.repository.ImageRepository;
import com.yellowsunn.springblog.service.Common;
import com.yellowsunn.springblog.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@Service
public class ImageServiceImpl implements ImageService {

    private final ImageRepository imageRepository;
    private final Common common;

    @Override
    public Image uploadImage(MultipartFile imageFile) {
        if (imageFile == null) return null;

        Image image = Image.builder()
                .name(imageFile.getOriginalFilename())
                .build();
        Image saveImage = imageRepository.save(image);

        boolean isUpload = common.uploadImageFile(imageFile);
        if (!isUpload) {
            throw new IllegalStateException();
        }

        return saveImage;
    }
}
