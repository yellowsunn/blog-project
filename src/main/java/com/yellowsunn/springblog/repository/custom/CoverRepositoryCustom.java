package com.yellowsunn.springblog.repository.custom;

import com.querydsl.core.Tuple;

import java.util.Optional;

public interface CoverRepositoryCustom {

    Optional<Tuple> findMain();

    Optional<Tuple> findHeader();

    Optional<Tuple> findProfile();
}
