package com.yellowsunn.springblog.repository.custom;

import com.yellowsunn.springblog.domain.entity.Cover;

import java.util.Optional;

public interface CoverRepositoryCustom {

    Optional<Cover> findFirst();
}
