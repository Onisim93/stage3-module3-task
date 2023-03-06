package com.mjc.school.service.impl;

import com.mjc.school.repository.BaseRepository;
import com.mjc.school.repository.model.NewsModel;
import com.mjc.school.service.BaseService;
import com.mjc.school.service.aspect.annotation.EntityValidate;
import com.mjc.school.service.dto.AuthorDto;
import com.mjc.school.repository.model.AuthorModel;
import com.mjc.school.service.exception.NoSuchEntityException;
import com.mjc.school.service.exception.ServiceErrorCode;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.mjc.school.service.mapper.AuthorMapper.INSTANCE;

@Service
public class AuthorService implements BaseService<AuthorDto, Long> {

    private final BaseRepository<AuthorModel, Long> repository;
    private final BaseRepository<NewsModel, Long> newsRepository;

    public AuthorService(BaseRepository<AuthorModel, Long> repository, BaseRepository<NewsModel, Long> newsRepository) {
        this.repository = repository;
        this.newsRepository = newsRepository;
    }

    @Override
    public List<AuthorDto> readAll() {
        return INSTANCE.toListDto(repository.readAll());
    }

    @Override
    public AuthorDto readById(Long id) {
        AuthorModel model = repository.readById(id)
                .orElseThrow(()->new NoSuchEntityException(String.format(ServiceErrorCode.AUTHOR_ID_DOES_NOT_EXIST.getMessage(), id)));
        return INSTANCE.toDto(model);
    }

    public AuthorDto readByNewsId(Long newsId) {
        NewsModel newsModel = newsRepository.readById(newsId)
                .orElseThrow(()->new NoSuchEntityException(String.format(ServiceErrorCode.NEWS_ID_DOES_NOT_EXIST.getMessage(), newsId)));
        return INSTANCE.toDto(newsModel.getAuthor());
    }

    @Override
    @EntityValidate
    public AuthorDto create(AuthorDto createRequest) {
        AuthorModel model = INSTANCE.toModel(createRequest);
        AuthorModel created = repository.create(model);
        return INSTANCE.toDto(created);
    }

    @Override
    @EntityValidate
    public AuthorDto update(AuthorDto updateRequest) {
        AuthorModel model = INSTANCE.toModel(updateRequest);
        return INSTANCE.toDto(repository.update(model));
    }

    @Override
    public boolean deleteById(Long id) {
        return repository.deleteById(id);
    }
}
