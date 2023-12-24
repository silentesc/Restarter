package de.silentesc.restarter;

import de.silentesc.restarter.commands.ReloadConfigCommand;
import de.silentesc.restarter.utils.ConfigUtils;
import de.silentesc.restarter.utils.ShortMessages;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.Objects;

public final class Main extends JavaPlugin {
    private static Main INSTANCE;
    private String prefix;
    private ShortMessages shortMessages;
    private ConfigUtils configUtils;

    public Main() {
        INSTANCE = this;
    }

    @Override
    public void onEnable() {
        loadConfig();
        initialize();
        register();
    }

    private void initialize() {
        this.prefix = "§7[§eRestarter§7] ";
        this.shortMessages = new ShortMessages();
        this.configUtils = new ConfigUtils();
    }

    private void loadConfig() {
        String configPath = "config.yaml";
        File file = new File(this.getDataFolder().getPath() + "/" + configPath);
        if (!file.exists()) saveResource(configPath, false);
    }

    private void register() {
        // Commands
        Objects.requireNonNull(Bukkit.getPluginCommand("restarter_reloadcfg")).setExecutor(new ReloadConfigCommand());
    }

    // Getter
    public static Main getINSTANCE() {
        return INSTANCE;
    }
    public String getPrefix() {
        return this.prefix;
    }
    public ShortMessages getShortMessages() {
        return this.shortMessages;
    }
    public ConfigUtils getConfigUtils() {
        return this.configUtils;
    }
}
