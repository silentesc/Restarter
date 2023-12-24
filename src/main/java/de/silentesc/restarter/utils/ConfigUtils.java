package de.silentesc.restarter.utils;

import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

public class ConfigUtils {
    private FileConfig config;

    public ConfigUtils() {
        config = new FileConfig("config.yaml");
    }

    public void reloadConfig() {
        config = new FileConfig("config.yaml");
    }

    public LocalTime getRestartTimeDecimal() {
        // Default value
        LocalTime defaultRestartTime = LocalTime.parse("04:00");

        // Get object and check for null
        Object restartTimeObj = config.get("restart-time");
        if (restartTimeObj == null) return defaultRestartTime;

        // Try to parse time from config
        LocalTime restartTime = null;
        try {
             restartTime = LocalTime.parse(restartTimeObj.toString());
        } catch (DateTimeParseException e) {
            System.out.println("Error parsing time, check if config is correct");
        }

        // Return value
        return (restartTime != null) ? restartTime : defaultRestartTime;
    }

    public boolean showTitle() {
        return config.getBoolean("show-title");
    }

    public String getTitleMessage() {
        // Default value
        String defaultTitleMessage = "Server restarts in %time%";

        // Get object and check for null
        Object titleMessageObj = config.get("title-message");
        if (titleMessageObj == null) return defaultTitleMessage;

        // Return value
        return titleMessageObj.toString();
    }

    public List<LocalTime> getTitleTimes() {
        // List
        List<LocalTime> titleTimes = new ArrayList<>();

        // Check for null
        List<Integer> intervals = config.getIntegerList("title-message-interval");

        // Get times and add them to list
        for (Integer interval : intervals) {
            LocalTime intervalTime = getRestartTimeDecimal().minusMinutes(interval);
            titleTimes.add(intervalTime);
        }

        // Return value
        return titleTimes;
    }

    public boolean showMessage() {
        return config.getBoolean("show-message");
    }

    public String getChatMessage() {
        // Default value
        String defaultChatMessage = "Server restarts in %time%";

        // Get object and check for null
        Object chatMessageObj = config.get("chat-message");
        if (chatMessageObj == null) return defaultChatMessage;

        // Return value
        return chatMessageObj.toString();
    }

    public List<LocalTime> getChatTimes() {
        // List
        List<LocalTime> chatTimes = new ArrayList<>();

        // Check for null
        List<Integer> intervals = config.getIntegerList("chat-message-interval");

        // Get times and add them to list
        for (Integer interval : intervals) {
            LocalTime intervalTime = getRestartTimeDecimal().minusMinutes(interval);
            chatTimes.add(intervalTime);
        }

        // Return value
        return chatTimes;
    }
}
