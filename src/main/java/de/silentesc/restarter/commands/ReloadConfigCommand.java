package de.silentesc.restarter.commands;

import de.silentesc.restarter.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class ReloadConfigCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        // Check permission
        if (!commandSender.hasPermission("restarter.reload")) {
            Main.getINSTANCE().getShortMessages().notPermitted(commandSender);
            return true;
        }

        // Reload Config
        Main.getINSTANCE().getConfigUtils().reloadConfig();

        // Send message
        Main.getINSTANCE().getShortMessages().sendMessageToSender(commandSender, "Config has been reloaded");

        return true;
    }
}
