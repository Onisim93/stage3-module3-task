package com.mjc.school.controller.command;

import com.mjc.school.controller.BaseController;
import com.mjc.school.controller.util.MessageHelper;
import com.mjc.school.service.dto.TagDto;
import com.mjc.school.service.exception.NoSuchEntityException;
import com.mjc.school.service.exception.ValidatorException;

import static com.mjc.school.controller.command.CommandConstants.*;


public class UpdateTagCommand implements Command{

    private final BaseController<TagDto, Long> controller;

    public UpdateTagCommand(BaseController<TagDto, Long> controller) {
        this.controller = controller;
    }

    @Override
    public void execute() {
        MessageHelper.printMessage(ENTER_ID);
        Long id = MessageHelper.readLong();
        MessageHelper.printMessage(ENTER_NAME);
        String name = MessageHelper.readString();

        try {
            TagDto created = new TagDto(id, name);
            MessageHelper.printMessage(controller.update(created).toString());
        }
        catch (NoSuchEntityException | ValidatorException e) {
            MessageHelper.printMessage(e.getMessage());
        }
    }
}
