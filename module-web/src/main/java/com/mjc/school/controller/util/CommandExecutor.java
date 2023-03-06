package com.mjc.school.controller.util;

import com.mjc.school.controller.command.*;
import com.mjc.school.controller.impl.AuthorController;
import com.mjc.school.controller.impl.NewsController;
import com.mjc.school.controller.impl.TagController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CommandExecutor {

    private final NewsController newsController;
    private final AuthorController authorController;
    private final TagController tagController;

    @Autowired
    public CommandExecutor(NewsController newsController, AuthorController authorController, TagController tagController) {
        this.newsController = newsController;
        this.authorController = authorController;
        this.tagController = tagController;
    }

    public void getAndExecuteNextCommand() {
        int action = (int) MessageHelper.readLong();
        try {
            MenuCommand menuCommand = MenuCommand.values()[action];
            Command command = null;
            switch (menuCommand) {
                case GET_ALL_NEWS -> command = new GetAllNewsCommand(newsController);
                case GET_ALL_AUTHORS -> command = new GetAllAuthorsCommand(authorController);
                case GET_ALL_TAGS -> command = new GetAllTagsCommand(tagController);
                case CREATE_NEWS -> command = new CreateNewsCommand(newsController);
                case CREATE_AUTHOR -> command = new CreateAuthorCommand(authorController);
                case CREATE_TAG -> command = new CreateTagCommand(tagController);
                case DELETE_NEWS -> command = new DeleteNewsByIdCommand(newsController);
                case DELETE_AUTHOR -> command = new DeleteAuthorByIdCommand(authorController);
                case DELETE_TAG -> command = new DeleteTagCommand(tagController);
                case UPDATE_NEWS -> command = new UpdateNewsCommand(newsController);
                case UPDATE_AUTHOR -> command = new UpdateAuthorCommand(authorController);
                case UPDATE_TAG -> command = new UpdateTagCommand(tagController);
                case GET_NEWS_BY_ID -> command = new GetNewsByIdCommand(newsController);
                case GET_AUTHOR_BY_ID -> command = new GetAuthorByIdCommand(authorController);
                case GET_TAG_BY_ID -> command = new GetTagByIdCommand(tagController);
                case GET_TAGS_BY_NEWS_ID -> command = new GetAllTagsByNewsIdCommand(tagController);
                case GET_AUTHOR_BY_NEWS_ID -> command = new GetAuthorByNewsIdCommand(authorController);
                case GET_NEWS_BY_PARAMETERS -> command = new GetAllNewsByParameters(newsController);
                case EXIT -> command = new ExitCommand();
            }
            command.execute();
        }
        catch (Exception e) {
            MessageHelper.printMessage("Invalid command.");
        }
    }

}
