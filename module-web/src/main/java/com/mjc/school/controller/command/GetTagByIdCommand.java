package com.mjc.school.controller.command;

import com.mjc.school.controller.BaseController;
import com.mjc.school.controller.util.MessageHelper;
import com.mjc.school.service.dto.TagDto;
import com.mjc.school.service.exception.NoSuchEntityException;
import com.mjc.school.service.exception.ValidatorException;
import org.springframework.beans.factory.annotation.Autowired;

import static com.mjc.school.controller.command.CommandConstants.*;


public class GetTagByIdCommand implements Command{

    private final BaseController<TagDto, Long> controller;

    public GetTagByIdCommand(BaseController<TagDto, Long> controller) {
        this.controller = controller;
    }

    @Override
    public void execute() {
        MessageHelper.printMessage(ENTER_ID);
        Long id = MessageHelper.readLong();

        try {
            TagDto tag = controller.readById(id);
            MessageHelper.printMessage(tag.toString());
        }
        catch (NoSuchEntityException | ValidatorException e) {
            MessageHelper.printMessage(e.getMessage());
        }

    }
}
