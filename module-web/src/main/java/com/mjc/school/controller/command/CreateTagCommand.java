package com.mjc.school.controller.command;

import com.mjc.school.controller.BaseController;
import com.mjc.school.controller.util.MessageHelper;
import com.mjc.school.service.dto.TagDto;
import com.mjc.school.service.exception.NoSuchEntityException;
import com.mjc.school.service.exception.ValidatorException;

import static com.mjc.school.controller.command.CommandConstants.*;


public class CreateTagCommand implements Command{

    private final BaseController<TagDto, Long> controller;

    public CreateTagCommand(BaseController<TagDto, Long> controller) {
        this.controller = controller;
    }

    @Override
    public void execute() {
        MessageHelper.printMessage(ENTER_NAME);
        String name = MessageHelper.readString();

        TagDto tag = new TagDto(name);

        try {
            TagDto created = controller.create(tag);
            MessageHelper.printMessage(created.toString());
        }
        catch (NoSuchEntityException | ValidatorException e) {
            MessageHelper.printMessage(e.getMessage());
        }

    }
}
