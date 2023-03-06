package com.mjc.school.controller.command;

import com.mjc.school.controller.impl.AuthorController;
import com.mjc.school.controller.util.MessageHelper;
import com.mjc.school.service.exception.NoSuchEntityException;
import com.mjc.school.service.exception.ValidatorException;

import static com.mjc.school.controller.command.CommandConstants.*;


public class GetAuthorByNewsIdCommand implements Command {

    private final AuthorController controller;

    public GetAuthorByNewsIdCommand(AuthorController controller) {
        this.controller = controller;
    }

    @Override
    public void execute() {
        MessageHelper.printMessage(ENTER_ID);
        Long newsId = MessageHelper.readLong();

        try {
            MessageHelper.printMessage(controller.readByNewsId(newsId).toString());
        }
        catch (NoSuchEntityException | ValidatorException e) {
            MessageHelper.printMessage(e.getMessage());
        }
    }
}
