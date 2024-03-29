package de.silentesc.restarter.tasks;

import de.silentesc.restarter.Main;
import org.bukkit.Bukkit;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class MessageScheduler extends ScheduleHelper {
    public void scheduleMessages() {
        // Checks
        if (!Main.getINSTANCE().getConfigUtils().showMessage()) return;

        // chat message template
        final String chatMessageTemplate = Main.getINSTANCE().getConfigUtils().getChatMessageTemplate();

        // Iterate through times
        for (LocalDateTime time : Main.getINSTANCE().getConfigUtils().getChatDateTimes()) {
            startScheduler(chatMessageTemplate, time);
        }
    }

    private void startScheduler(String chatMessageTemplate, LocalDateTime time) {
        // Check for events in the past and ignore them
        long secondsUntilMessage = ChronoUnit.SECONDS.between(getNowDateTime(), time);
        if (secondsUntilMessage <= 0) return;

        // Setting schedule
        Bukkit.getScheduler().runTaskLaterAsynchronously(Main.getINSTANCE(), () -> {
            // Send message
            long minutes = Math.abs(ChronoUnit.MINUTES.between(getRestartDateTime(), time));
            String chatMessage = chatMessageTemplate.replaceAll("%time%", String.valueOf(minutes));
            Bukkit.broadcastMessage(Main.getINSTANCE().getPrefix() + chatMessage);
        }, ((long) 20 * secondsUntilMessage));
    }
}
