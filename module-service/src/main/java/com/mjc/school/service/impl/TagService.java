package com.mjc.school.service.impl;

import com.mjc.school.repository.BaseRepository;
import com.mjc.school.repository.model.NewsModel;
import com.mjc.school.service.BaseService;
import com.mjc.school.service.aspect.annotation.EntityValidate;
import com.mjc.school.service.dto.TagDto;
import com.mjc.school.repository.model.TagModel;
import com.mjc.school.service.exception.NoSuchEntityException;
import com.mjc.school.service.exception.ServiceErrorCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import static com.mjc.school.service.mapper.TagMapper.INSTANCE;

@Service
public class TagService implements BaseService<TagDto, Long> {

    private final BaseRepository<TagModel, Long> repository;
    private final BaseRepository<NewsModel, Long> newsRepository;

    public TagService(BaseRepository<TagModel, Long> repository, BaseRepository<NewsModel, Long> newsRepository) {
        this.repository = repository;
        this.newsRepository = newsRepository;
    }

    @Override
    public List<TagDto> readAll() {
        return INSTANCE.toListDto(repository.readAll());
    }

    public List<TagDto> readAllByNewsId(Long newsId) {
        NewsModel model = newsRepository.readById(newsId)
                .orElseThrow(()->new NoSuchEntityException(String.format(ServiceErrorCode.NEWS_ID_DOES_NOT_EXIST.getMessage(), newsId)));
        return INSTANCE.toListDto(model.getTags());
    }

    @Override
    public TagDto readById(Long id) {
        return INSTANCE.toDto(repository
                .readById(id)
                .orElseThrow(()->new NoSuchEntityException(String.format(ServiceErrorCode.TAG_ID_DOES_NOT_EXIST.getMessage(), id))));
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
