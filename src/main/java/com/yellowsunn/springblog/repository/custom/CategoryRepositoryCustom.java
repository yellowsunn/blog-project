package com.yellowsunn.springblog.repository.custom;

import com.querydsl.core.Tuple;

import java.util.Queue;

public interface CategoryRepositoryCustom {

    Queue<Tuple> findCategoryList();
}
