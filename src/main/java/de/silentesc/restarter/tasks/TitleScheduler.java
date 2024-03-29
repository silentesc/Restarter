package de.silentesc.restarter.tasks;

import de.silentesc.restarter.Main;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class TitleScheduler extends ScheduleHelper {
    public void scheduleTitles() {
        // Checks
        if (!Main.getINSTANCE().getConfigUtils().showTitle()) return;

        // title message template
        final String titleMessageTemplate = Main.getINSTANCE().getConfigUtils().getTitleMessageTemplate();

        // Iterate through times
        for (LocalDateTime time : Main.getINSTANCE().getConfigUtils().getTitleDateTimes()) {
            startScheduler(titleMessageTemplate, time);
        }
    }

    private void startScheduler(String titleMessageTemplate, LocalDateTime time) {
        // Check for events in the past and ignore them
        long secondsUntilTitle = ChronoUnit.SECONDS.between(getNowDateTime(), time);
        if (secondsUntilTitle <= 0) return;

        // Setting schedule
        Bukkit.getScheduler().runTaskLaterAsynchronously(Main.getINSTANCE(), () -> {
            // Show title to all online users
            for (Player p : Bukkit.getOnlinePlayers()) {
                long minutes = Math.abs(ChronoUnit.MINUTES.between(getRestartDateTime(), time));
                String titleMessage = titleMessageTemplate.replaceAll("%time%", String.valueOf(minutes));
                p.sendTitle(titleMessage, "", 10, 60, 10);
            }
        }, ((long) 20 * secondsUntilTitle));
    }
}
