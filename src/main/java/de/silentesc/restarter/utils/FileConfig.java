package de.silentesc.restarter.utils;

import de.silentesc.restarter.Main;
import org.bukkit.Bukkit;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;

public class FileConfig extends YamlConfiguration {
    private final String path;

    public FileConfig(String fileName) {
        this.path = Main.getINSTANCE().getDataFolder().getPath() + "/" + fileName;
        File file = new File(this.path);
        if (!file.exists())
            this.saveConfig();
        try {
            load(this.path);
        } catch (InvalidConfigurationException | IOException e) {
            Bukkit.getLogger().log(Level.SEVERE, String.format("Error while loading config inside FileConfig: %s", e));
        }
    }

    public void saveConfig() {
        try {
            save(this.path);
        } catch (IOException e) {
            Bukkit.getLogger().log(Level.SEVERE, String.format("Error while saving config inside FileConfig: %s", e));
        }
    }
}
