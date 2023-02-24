package com.mjc.school;

import com.mjc.school.model.BaseEntity;

import java.util.List;
import java.util.Optional;

public interface BaseRepository<E extends BaseEntity, K> {
    List<E> readAll();

    Optional<E> readById(K id);

    E create(E entity);

    E update(E entity);

    boolean deleteById(K id);

    boolean existById(K id);
}
