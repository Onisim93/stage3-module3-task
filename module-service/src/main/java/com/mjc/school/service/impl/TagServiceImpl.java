package com.mjc.school.service.impl;

import com.mjc.school.repository.BaseRepository;
import com.mjc.school.repository.model.NewsModel;
import com.mjc.school.service.BaseService;
import com.mjc.school.service.TagService;
import com.mjc.school.service.aspect.annotation.EntityValidate;
import com.mjc.school.service.dto.TagDto;
import com.mjc.school.repository.model.TagModel;
import com.mjc.school.service.exception.NoSuchEntityException;
import com.mjc.school.service.exception.ServiceErrorCode;
import org.springframework.stereotype.Service;

import java.util.List;
import static com.mjc.school.service.mapper.TagMapper.INSTANCE;

@Service
public class TagServiceImpl implements TagService {

    private final BaseRepository<TagModel, Long> repository;
    private final BaseRepository<NewsModel, Long> newsRepository;

    public TagServiceImpl(BaseRepository<TagModel, Long> repository, BaseRepository<NewsModel, Long> newsRepository) {
        this.repository = repository;
        this.newsRepository = newsRepository;
    }

    @Override
    public List<TagDto> readAll() {
        return INSTANCE.toListDto(repository.readAll());
    }

    @Override
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
    @EntityValidate
    public TagDto create(TagDto createRequest) {
        TagModel model = INSTANCE.toModel(createRequest);
        return INSTANCE.toDto(repository.create(model));
    }

    @Override
    @EntityValidate
    public TagDto update(TagDto updateRequest) {
        TagModel model = INSTANCE.toModel(updateRequest);
        return INSTANCE.toDto(repository.update(model));
    }

    @Override
    public boolean deleteById(Long id) {
        return repository.deleteById(id);
    }
}
