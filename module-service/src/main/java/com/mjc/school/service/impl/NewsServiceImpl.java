package com.mjc.school.service.impl;

import com.mjc.school.repository.BaseRepository;
import com.mjc.school.repository.model.TagModel;
import com.mjc.school.service.BaseService;
import com.mjc.school.service.NewsService;
import com.mjc.school.service.aspect.annotation.EntityValidate;
import com.mjc.school.service.dto.NewsDto;
import com.mjc.school.service.dto.TagDto;
import com.mjc.school.service.exception.NoSuchEntityException;
import com.mjc.school.service.exception.ServiceErrorCode;
import com.mjc.school.service.mapper.NewsMapper;
import com.mjc.school.repository.model.NewsModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class NewsServiceImpl implements NewsService {

    private final BaseRepository<NewsModel, Long> repository;
    private final BaseRepository<TagModel, Long> tagRepository;

    @Autowired
    public NewsServiceImpl(BaseRepository<NewsModel, Long> repository, BaseRepository<TagModel, Long> tagRepository) {
        this.repository = repository;
        this.tagRepository = tagRepository;
    }

    @Override
    public List<NewsDto> readAll() {
        return NewsMapper.INSTANCE.toListDto(repository.readAll());
    }

    @Override
    public List<NewsDto> readAllByParameters(List<String> tagNames, List<Long> tagIds, String authorName, String title, String content) {
        List<NewsModel> news = repository.readAll();

        return NewsMapper.INSTANCE.toListDto(news.stream().filter(newsModel -> entityFilter(newsModel, tagNames, tagIds, authorName, title, content)).collect(Collectors.toList()));

    }

    private boolean entityFilter(NewsModel entity, List<String> tagNames, List<Long> tagIds, String authorName, String title, String content) {
        boolean isAdd = true;
        if (tagNames != null && !tagNames.isEmpty()) {
            if (!new HashSet<>(entity.getTags().stream().map(TagModel::getName).toList()).containsAll(tagNames)) {
                isAdd = false;
            }
        }
        if (tagIds != null && !tagIds.isEmpty()) {
            if (!new HashSet<>(entity.getTags().stream().map(TagModel::getId).toList()).containsAll(tagIds)) {
                isAdd = false;
            }
        }
        if (authorName != null && !authorName.isBlank()) {
            if (!entity.getAuthor().getName().equalsIgnoreCase(authorName)) {
                isAdd = false;
            }
        }
        if (title != null && !title.isBlank()) {
            if (!entity.getTitle().equalsIgnoreCase(title)) {
                isAdd = false;
            }
        }
        if (content != null && !content.isBlank()) {
            if (!entity.getContent().equalsIgnoreCase(content)) {
                isAdd = false;
            }
        }
        return isAdd;
    }

    @Override
    public NewsDto readById(Long id) {
        return NewsMapper.INSTANCE.toDto(repository
                .readById(id)
                .orElseThrow(()->new NoSuchEntityException(String.format(ServiceErrorCode.NEWS_ID_DOES_NOT_EXIST.getMessage(), id))));
    }

    @Override
    @EntityValidate
    public NewsDto create(NewsDto createRequest) {
        isTagsExists(createRequest.getTagList());
        NewsModel model = NewsMapper.INSTANCE.toModel(createRequest);
        return NewsMapper.INSTANCE.toDto(repository.create(model));
    }

    @Override
    @EntityValidate
    public NewsDto update(NewsDto updateRequest) {
        isTagsExists(updateRequest.getTagList());
        NewsModel model = NewsMapper.INSTANCE.toModel(updateRequest);
        return NewsMapper.INSTANCE.toDto(repository.update(model));
    }

    @Override
    public boolean deleteById(Long id) {
        return repository.deleteById(id);
    }

    private void isTagsExists(List<TagDto> tagList) {
        tagList.forEach(tag -> {
            if (!tagRepository.existById(tag.getId())) {
                throw new NoSuchEntityException(String.format(ServiceErrorCode.TAG_ID_DOES_NOT_EXIST.getMessage(), tag.getId()));
            }
        });
    }
}
