package com.github.assemblyorg.keepInventory.configs;

import com.github.assemblyorg.keepInventory.KeepInventory;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public abstract class Config {

    private final KeepInventory plugin;
    private final String resourcePath;
    private final File configFile;
    private FileConfiguration config;

    public Config(KeepInventory plugin, String resourcePath) {
        this.plugin = plugin;
        this.resourcePath = resourcePath;
        this.configFile = new File(plugin.getDataFolder(), resourcePath);
        reload();
    }

    public void reload() {
        if (!plugin.getDataFolder().exists()) plugin.getDataFolder().mkdirs();
        if (!configFile.exists()) plugin.saveResource(resourcePath, false);
        this.config = YamlConfiguration.loadConfiguration(configFile);
    }

    public void save() {
        try {
            config.save(configFile);
        } catch (IOException e) {
            plugin.getLogger().severe("Could not save config to " + configFile.getName());
        }
    }

    public FileConfiguration get() {
        if (config == null) reload();
        return config;
    }

}
