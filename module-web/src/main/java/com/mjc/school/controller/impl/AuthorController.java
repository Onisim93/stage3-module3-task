package com.mjc.school.controller.impl;

import com.mjc.school.controller.BaseController;
import com.mjc.school.service.AuthorService;
import com.mjc.school.service.BaseService;
import com.mjc.school.service.dto.AuthorDto;
import com.mjc.school.service.impl.AuthorServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class AuthorController implements BaseController<AuthorDto, Long> {
    private final AuthorService service;

    @Autowired
    public AuthorController(AuthorService service) {
        this.service = service;
    }

    @Override
    public List<AuthorDto> readAll() {
        return service.readAll();
    }

    @Override
    public AuthorDto readById(Long id) {
        return service.readById(id);
    }

    public AuthorDto readByNewsId(Long newsId) {
        return service.readByNewsId(newsId);
    }

    @Override
    public AuthorDto create(AuthorDto createRequest) {
        return service.create(createRequest);
    }

    @Override
    public AuthorDto update(AuthorDto updateRequest) {
        return service.update(updateRequest);
    }

    @Override
    public boolean deleteById(Long id) {
        return service.deleteById(id);
    }

}
