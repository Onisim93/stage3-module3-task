package com.mjc.school.service.impl;

import com.mjc.school.repository.BaseRepository;
import com.mjc.school.service.BaseService;
import com.mjc.school.service.aspect.annotation.EntityValidate;
import com.mjc.school.service.dto.TagDto;
import com.mjc.school.repository.model.TagModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import static com.mjc.school.service.mapper.TagMapper.INSTANCE;

@Service
public class TagService implements BaseService<TagDto, Long> {

    private final BaseRepository<TagModel, Long> repository;

    @Autowired
    public TagService(BaseRepository<TagModel, Long> repository) {
        this.repository = repository;
    }

    @Override
    public List<TagDto> readAll() {
        return INSTANCE.toListDto(repository.readAll());
    }

    @Override
    public TagDto readById(Long id) {
        return INSTANCE.toDto(repository.readById(id).orElseThrow());
    }

    @Override
    @Transactional
    @EntityValidate
    public TagDto create(TagDto createRequest) {
        TagModel model = INSTANCE.toModel(createRequest);
        return INSTANCE.toDto(repository.create(model));
    }

    @Override
    @Transactional
    @EntityValidate
    public TagDto update(TagDto updateRequest) {
        TagModel model = INSTANCE.toModel(updateRequest);
        return INSTANCE.toDto(repository.update(model));
    }

    @Override
    @Transactional
    public boolean deleteById(Long id) {
        return repository.deleteById(id);
    }
}
