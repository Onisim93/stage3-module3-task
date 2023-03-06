package com.mjc.school.controller.command.util;

import com.mjc.school.controller.util.MessageHelper;

import static com.mjc.school.controller.command.CommandConstants.*;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CommandHelper {

    public static List<Long> readTagIds() {
        try {
            MessageHelper.printMessage(ENTER_TAG_IDs);
            List<String> tagIds = Arrays.stream(MessageHelper.readString().split(",")).toList();
            if (tagIds.isEmpty()) {
                return new ArrayList<>();
            }
            return tagIds.stream().map(s->Long.parseLong(s.trim())).collect(Collectors.toList());
        }
        catch (NumberFormatException e) {
            MessageHelper.printMessage("Tag id must be a number, try again.");
            return readTagIds();
        }
    }

    public static List<String> readTagNames() {
        return Arrays.stream(MessageHelper.readString().split(",")).map(String::trim).toList();
    }

}
