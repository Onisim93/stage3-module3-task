package com.mjc.school.service.impl;

import com.mjc.school.repository.BaseRepository;
import com.mjc.school.service.BaseService;
import com.mjc.school.service.aspect.annotation.EntityValidate;
import com.mjc.school.service.dto.AuthorDto;
import com.mjc.school.repository.model.AuthorModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.mjc.school.service.mapper.AuthorMapper.INSTANCE;

@Service
public class AuthorService implements BaseService<AuthorDto, Long> {

    private final BaseRepository<AuthorModel, Long> repository;

    @Autowired
    public AuthorService(BaseRepository<AuthorModel, Long> repository) {
        this.repository = repository;
    }

    @Override
    public List<AuthorDto> readAll() {
        return INSTANCE.toListDto(repository.readAll());
    }

    @Override
    public AuthorDto readById(Long id) {
        AuthorModel model = repository.readById(id).orElseThrow();
        return INSTANCE.toDto(model);
    }

    @Override
    @Transactional
    @EntityValidate
    public AuthorDto create(AuthorDto createRequest) {
        AuthorModel model = INSTANCE.toModel(createRequest);
        AuthorModel created = repository.create(model);
        return INSTANCE.toDto(created);
    }

    @Override
    @Transactional
    @EntityValidate
    public AuthorDto update(AuthorDto updateRequest) {
        AuthorModel model = INSTANCE.toModel(updateRequest);
        return INSTANCE.toDto(repository.update(model));
    }

    @Override
    @Transactional
    public boolean deleteById(Long id) {
        return repository.deleteById(id);
    }
}
