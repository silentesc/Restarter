package de.silentesc.restarter.utils;

import org.bukkit.Bukkit;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

public class ConfigUtils {
    private FileConfig config;

    public ConfigUtils() {
        config = new FileConfig("config.yaml");
    }

    public void reloadConfig() {
        config = new FileConfig("config.yaml");
    }

    public LocalDateTime getRestartTime() {
        // Default value
        LocalDateTime defaultRestartDateTime = LocalTime.parse("04:00").atDate(LocalDate.now());
        LocalDateTime restartDateTime;

        // Get object and check for null
        Object restartTimeObj = config.get("restart-time");
        if (restartTimeObj == null)
            restartDateTime = defaultRestartDateTime;
        else {
            // Try to parse time from config
            try {
                restartDateTime = LocalTime.parse(restartTimeObj.toString()).atDate(LocalDate.now());
            } catch (DateTimeParseException e) {
                Bukkit.getLogger().log(Level.WARNING, "Error parsing time, check if config is correct");
                restartDateTime = defaultRestartDateTime;
            }
        }

        // If the restart was today, move restart to next day
        if (ChronoUnit.SECONDS.between(LocalDateTime.now(), restartDateTime) <= 0)
            restartDateTime = restartDateTime.plusDays(1L);

        // Return value
        return restartDateTime;
    }

    public boolean showTitle() {
        return config.getBoolean("show-title");
    }

    public String getTitleMessageTemplate() {
        // Default value
        String defaultTitleMessage = "Server restarts in %time%";

        // Get object and check for null
        Object titleMessageObj = config.get("title-message");
        if (titleMessageObj == null) return defaultTitleMessage;

        // Return value
        return titleMessageObj.toString();
    }

    public List<LocalDateTime> getTitleDateTimes() {
        // List
        List<LocalDateTime> titleTimes = new ArrayList<>();

        // Check for null
        List<Integer> intervals = config.getIntegerList("title-message-interval");

        // Get times and add them to list
        for (Integer interval : intervals) {
            LocalDateTime intervalTime = getRestartTime().minusMinutes(interval);
            titleTimes.add(intervalTime);
        }

        // Return value
        return titleTimes;
    }

    public boolean showMessage() {
        return config.getBoolean("send-message");
    }

    public String getChatMessageTemplate() {
        // Default value
        String defaultChatMessage = "Server restarts in %time%";

        // Get object and check for null
        Object chatMessageObj = config.get("chat-message");
        if (chatMessageObj == null) return defaultChatMessage;

        // Return value
        return chatMessageObj.toString();
    }

    public List<LocalDateTime> getChatDateTimes() {
        // List
        List<LocalDateTime> chatTimes = new ArrayList<>();

        // Check for null
        List<Integer> intervals = config.getIntegerList("chat-message-interval");

        // Get times and add them to list
        for (Integer interval : intervals) {
            LocalDateTime intervalTime = getRestartTime().minusMinutes(interval);
            chatTimes.add(intervalTime);
        }

        // Return value
        return chatTimes;
    }
}
