package com.mjc.school.controller.impl;

import com.mjc.school.controller.BaseController;
import com.mjc.school.service.BaseService;
import com.mjc.school.service.dto.TagDto;
import com.mjc.school.service.impl.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class TagController implements BaseController<TagDto, Long> {

    private final BaseService<TagDto,Long> service;

    @Autowired
    public TagController(BaseService<TagDto, Long> service) {
        this.service = service;
    }

    @Override
    public List<TagDto> readAll() {
        return service.readAll();
    }

    public List<TagDto> readAllByNewsId(Long newsId) {
        return ((TagService)service).readAllByNewsId(newsId);
    }

    @Override
    public TagDto readById(Long id) {
        return service.readById(id);
    }

    @Override
    public TagDto create(TagDto createRequest) {
        return service.create(createRequest);
    }

    @Override
    public TagDto update(TagDto updateRequest) {
        return service.update(updateRequest);
    }

    @Override
    public boolean deleteById(Long id) {
        return service.deleteById(id);
    }
}
