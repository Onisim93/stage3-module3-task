package com.mjc.school.controller.command;

import com.mjc.school.controller.impl.TagController;
import com.mjc.school.controller.util.MessageHelper;

import static com.mjc.school.controller.command.CommandConstants.*;

public class GetAllTagsByNewsIdCommand implements Command{

    private final TagController controller;

    public GetAllTagsByNewsIdCommand(TagController controller) {
        this.controller = controller;
    }

    @Override
    public void execute() {
        MessageHelper.printMessage(ENTER_ID);
        Long newsId = MessageHelper.readLong();

        MessageHelper.printMessage(controller.readAllByNewsId(newsId).toString());
    }
}
