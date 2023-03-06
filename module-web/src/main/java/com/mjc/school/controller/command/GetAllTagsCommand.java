package com.mjc.school.controller.command;

import com.mjc.school.controller.BaseController;
import com.mjc.school.controller.util.MessageHelper;
import com.mjc.school.service.dto.TagDto;

import java.util.List;

public class GetAllTagsCommand implements Command{

    private final BaseController<TagDto, Long> controller;

    public GetAllTagsCommand(BaseController<TagDto, Long> controller) {
        this.controller = controller;
    }

    @Override
    public void execute() {
        MessageHelper.printMessage(controller.readAll().toString());
    }
}
