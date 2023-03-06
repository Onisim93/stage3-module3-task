package com.mjc.school.controller.command;

import com.mjc.school.controller.BaseController;
import com.mjc.school.controller.command.util.CommandHelper;
import com.mjc.school.controller.util.MessageHelper;
import com.mjc.school.service.dto.NewsDto;
import com.mjc.school.service.exception.NoSuchEntityException;
import com.mjc.school.service.exception.ValidatorException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.mjc.school.controller.command.CommandConstants.*;

public class CreateNewsCommand implements Command{

    private final BaseController<NewsDto, Long> controller;

    public CreateNewsCommand(BaseController<NewsDto, Long> controller) {
        this.controller = controller;
    }

    @Override
    public void execute() {
        MessageHelper.printMessage(ENTER_TITLE);
        String title = MessageHelper.readString();
        MessageHelper.printMessage(ENTER_CONTENT);
        String content = MessageHelper.readString();
        MessageHelper.printMessage(ENTER_AUTHOR_ID);
        Long id = MessageHelper.readLong();

        List<Long> tagIds = CommandHelper.readTagIds();

        NewsDto newsDto = new NewsDto(title, content, id, tagIds);

        try {
            NewsDto created  = controller.create(newsDto);
            MessageHelper.printMessage(created.toString());
        }
        catch (NoSuchEntityException | ValidatorException e) {
            MessageHelper.printMessage(e.getMessage());
        }
    }
}
