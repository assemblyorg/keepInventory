package com.github.assemblyorg.keepInventory.configs;

import com.github.assemblyorg.keepInventory.KeepInventory;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

public class MessagesConfig {

    private final KeepInventory plugin;
    private final String configName;
    private final File file;

    public MessagesConfig(KeepInventory plugin, String configName) {
        this.plugin = plugin;
        this.configName = configName;
        this.file = new File(plugin.getDataFolder(), configName);
    }

    public void saveDefaultConfig() {
        if (!file.exists()) saveConfig();
    }

    public void saveConfig() {
        saveConfig(false);
    }

    public void saveConfig(boolean replace) {
        plugin.saveResource(configName, replace);
    }

    public YamlConfiguration getConfig() {
        return YamlConfiguration.loadConfiguration(file);
    }

}
