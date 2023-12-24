package de.silentesc.restarter.utils;

import de.silentesc.restarter.Main;
import org.bukkit.command.CommandSender;

public class ShortMessages {
    public void notPermitted(CommandSender sender) {
        this.sendMessageToSender(sender, "You are not permitted to do this.");
    }
    public void sendMessageToSender(CommandSender sender, String message) {
        sender.sendMessage(Main.getINSTANCE().getPrefix() + message);
    }
}
