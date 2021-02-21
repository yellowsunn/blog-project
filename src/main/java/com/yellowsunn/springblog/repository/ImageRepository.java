package com.yellowsunn.springblog.repository;

import com.yellowsunn.springblog.domain.entity.Image;
import com.yellowsunn.springblog.repository.custom.ImageRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, Long>, ImageRepositoryCustom {
}
