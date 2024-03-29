package de.silentesc.restarter.tasks;

import de.silentesc.restarter.Main;
import org.bukkit.Bukkit;

import java.time.temporal.ChronoUnit;

public class RestartScheduler extends ScheduleHelper {
    public void scheduleRestart() {
        // Time to next restart in seconds
        long secondsUntilRestart = ChronoUnit.SECONDS.between(getNowDateTime(), getRestartDateTime());

        // Schedule restart
        Bukkit.getScheduler().runTaskLater(
                Main.getINSTANCE(),
                () -> Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "restart"),
                ((long) 20 * secondsUntilRestart)
        );
    }
}
