package com.yellowsunn.springblog.repository;

import com.yellowsunn.springblog.domain.entity.Cover;
import com.yellowsunn.springblog.repository.custom.CoverRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoverRepository extends JpaRepository<Cover, Long>, CoverRepositoryCustom {
}
