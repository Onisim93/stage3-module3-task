package com.mjc.school.controller.impl;

import com.mjc.school.controller.BaseController;
import com.mjc.school.service.dto.NewsDto;
import com.mjc.school.service.impl.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class NewsController implements BaseController<NewsDto, Long> {

    private final NewsService service;

    @Autowired
    public NewsController(NewsService service) {
        this.service = service;
    }

    @Override
    public List<NewsDto> readAll() {
        return service.readAll();
    }

    public List<NewsDto> readAllByParameters(List<String> tagNames, List<Long> tagIds, String authorName, String title, String content) {
        return service.readAllByParameters(tagNames, tagIds, authorName, title, content);
    }

    @Override
    public NewsDto readById(Long id) {
        return service.readById(id);
    }

    @Override
    public NewsDto create(NewsDto createRequest) {
        return service.create(createRequest);
    }

    @Override
    public NewsDto update(NewsDto updateRequest) {
        return service.update(updateRequest);
    }

    @Override
    public boolean deleteById(Long id) {
        return service.deleteById(id);
    }
}
