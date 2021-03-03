package com.yellowsunn.springblog.repository.custom;

import com.querydsl.core.Tuple;
import com.yellowsunn.springblog.domain.entity.Cover;

import java.util.Optional;

public interface CoverRepositoryCustom {

    Optional<Cover> findFirst();

    Optional<Tuple> findMain();

    Optional<Tuple> findHeader();

    Optional<Tuple> findProfile();
}
