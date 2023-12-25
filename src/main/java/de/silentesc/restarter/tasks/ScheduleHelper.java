package de.silentesc.restarter.tasks;

import de.silentesc.restarter.Main;

import java.time.LocalDateTime;

public class ScheduleHelper {
    private final LocalDateTime nowDateTime = LocalDateTime.now();
    private final LocalDateTime restartDateTime = Main.getINSTANCE().getConfigUtils().getRestartTime();

    LocalDateTime getNowDateTime() {
        return nowDateTime;
    }

    LocalDateTime getRestartDateTime() {
        return restartDateTime;
    }
}
