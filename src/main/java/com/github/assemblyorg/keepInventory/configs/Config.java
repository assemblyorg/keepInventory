package com.github.assemblyorg.keepInventory.configs;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.io.IOException;

public abstract class Config {

    private final JavaPlugin plugin;
    private final String resourcePath;
    private final File configFile;
    private FileConfiguration config;
    private FileConfiguration configDefault;

    public Config(@NotNull JavaPlugin plugin, @NotNull String resourcePath) {
        this.plugin = plugin;
        this.resourcePath = resourcePath;
        this.configFile = new File(plugin.getDataFolder(), resourcePath);
        reload();
        this.configDefault = config;
    }

    public void reload() {
        File parentFile = configFile.getParentFile();
        if (!parentFile.exists()) parentFile.mkdirs();
        if (!configFile.exists()) plugin.saveResource(resourcePath, false);

        // https://github.com/tchristofferson/Config-Updater/ <- need to rewrite for this project

        config = YamlConfiguration.loadConfiguration(configFile);
    }

    public void save() {
        try {
            config.save(configFile);
        } catch (IOException e) {
            plugin.getLogger().severe("Could not save config to " + configFile.getName());
        }
    }

    public FileConfiguration get() {
        return config;
    }

    public FileConfiguration getDefault() {
        return configDefault;
    }

    public void set(@NotNull String path, @Nullable Object value) {
        set(path, value, false);
    }

    public void set(@NotNull String path, @Nullable Object value, boolean save) {
        config.set(path, value);
        if (save) save();
    }

}
