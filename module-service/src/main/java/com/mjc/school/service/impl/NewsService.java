package com.mjc.school.service.impl;

import com.mjc.school.repository.BaseRepository;
import com.mjc.school.service.BaseService;
import com.mjc.school.service.aspect.annotation.EntityValidate;
import com.mjc.school.service.dto.NewsDto;
import com.mjc.school.service.mapper.NewsMapper;
import com.mjc.school.repository.model.NewsModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class NewsService implements BaseService<NewsDto, Long> {

    private final BaseRepository<NewsModel, Long> repository;

    @Autowired
    public NewsService(BaseRepository<NewsModel, Long> repository) {
        this.repository = repository;
    }

    @Override
    public List<NewsDto> readAll() {
        return NewsMapper.INSTANCE.toListDto(repository.readAll());
    }

    @Override
    public NewsDto readById(Long id) {
        return NewsMapper.INSTANCE.toDto(repository.readById(id).orElseThrow());
    }

    @Override
    @Transactional
    @EntityValidate
    public NewsDto create(NewsDto createRequest) {
        NewsModel model = NewsMapper.INSTANCE.toModel(createRequest);
        return NewsMapper.INSTANCE.toDto(repository.create(model));
    }

    @Override
    @Transactional
    @EntityValidate
    public NewsDto update(NewsDto updateRequest) {
        NewsModel model = NewsMapper.INSTANCE.toModel(updateRequest);
        return NewsMapper.INSTANCE.toDto(repository.update(model));
    }

    @Override
    @Transactional
    public boolean deleteById(Long id) {
        return repository.deleteById(id);
    }
}
