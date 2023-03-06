package com.mjc.school.controller.util;

import lombok.Getter;

@Getter
public enum MenuCommand {
    EXIT("0 - Exit."),
    CREATE_NEWS("1 - Create News."),
    CREATE_AUTHOR("2 - Create Author."),
    CREATE_TAG("3 - Create Tag."),
    GET_ALL_NEWS("4 - Get all news."),
    GET_ALL_AUTHORS("5 - Get all authors."),
    GET_ALL_TAGS("6 - Get all tags."),
    GET_NEWS_BY_ID("7 - Get News by id."),
    GET_AUTHOR_BY_ID("8 - Get Author by id."),
    GET_TAG_BY_ID("9 - Get Tag by id."),
    UPDATE_NEWS("10 - Update News."),
    UPDATE_AUTHOR("11 - Update Author."),
    UPDATE_TAG("12 - Update Tag."),
    DELETE_NEWS("13 - Delete News by id."),
    DELETE_AUTHOR("14 - Delete Author by id."),
    DELETE_TAG("15 - Delete Tag by id."),
    GET_AUTHOR_BY_NEWS_ID("16 - Get Author by News id."),
    GET_TAGS_BY_NEWS_ID("17 - Get Tags by News id."),
    GET_NEWS_BY_PARAMETERS("18 - Get News by some parameters.");


    private final String description;

    MenuCommand(String description) {
        this.description = description;
    }
}
