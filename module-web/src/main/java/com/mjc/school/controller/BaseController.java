package com.mjc.school.controller;

import com.mjc.school.service.dto.BaseDto;

import java.util.List;

public interface BaseController<E extends BaseDto, K> {

    List<E> readAll();

    E readById(K id);

    E create(E createRequest);

    E update(E updateRequest);

    boolean deleteById(K id);
}
