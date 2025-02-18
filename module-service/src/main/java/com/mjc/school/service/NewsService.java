package com.mjc.school.service;

import com.mjc.school.service.dto.NewsDto;

import java.util.List;

public interface NewsService extends BaseService<NewsDto, Long> {
    List<NewsDto> readAllByParameters(List<String> tagNames, List<Long> tagIds, String authorName, String title, String content);
}
