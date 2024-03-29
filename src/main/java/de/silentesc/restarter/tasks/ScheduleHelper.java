package de.silentesc.restarter.tasks;

import de.silentesc.restarter.Main;

import java.time.LocalDateTime;

public class ScheduleHelper {
    LocalDateTime getNowDateTime() {
        return LocalDateTime.now();
    }

    LocalDateTime getRestartDateTime() {
        return Main.getINSTANCE().getConfigUtils().getRestartTime();
    }
}
