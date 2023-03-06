package com.mjc.school.controller.command;

import com.mjc.school.controller.command.util.CommandHelper;
import com.mjc.school.controller.impl.NewsController;
import com.mjc.school.controller.util.MessageHelper;
import com.mjc.school.service.exception.NoSuchEntityException;
import com.mjc.school.service.exception.ValidatorException;

import java.util.List;

import static com.mjc.school.controller.command.CommandConstants.*;


public class GetAllNewsByParameters implements Command{

    private final NewsController controller;

    public GetAllNewsByParameters(NewsController controller) {
        this.controller = controller;
    }

    @Override
    public void execute() {
        MessageHelper.printMessage(ENTER_TAG_NAMES);
        List<String> tagNames = CommandHelper.readTagNames();
        MessageHelper.printMessage(ENTER_TAG_IDs);
        List<Long> tagIds = CommandHelper.readTagIds();
        MessageHelper.printMessage(ENTER_AUTHOR_NAME);
        String authorName = MessageHelper.readString();
        MessageHelper.printMessage(ENTER_TITLE);
        String title = MessageHelper.readString();
        MessageHelper.printMessage(ENTER_CONTENT);
        String content = MessageHelper.readString();

        try {
            MessageHelper.printMessage(controller.readAllByParameters(tagNames, tagIds, authorName, title, content).toString());
        }
        catch (NoSuchEntityException | ValidatorException e) {
            MessageHelper.printMessage(e.getMessage());
        }
    }
}
