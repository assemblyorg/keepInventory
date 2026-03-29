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

    public Config(@NotNull JavaPlugin plugin, @NotNull String resourcePath) {
        this.plugin = plugin;
        this.resourcePath = resourcePath;
        this.configFile = new File(plugin.getDataFolder(), resourcePath);
        reload();
    }

    public void reload() {
        File parentFile = configFile.getParentFile();
        if (!parentFile.exists()) parentFile.mkdirs();
        if (!configFile.exists()) plugin.saveResource(resourcePath, false);
        this.config = YamlConfiguration.loadConfiguration(configFile);
    }

    public void save() {
        if (config == null) return;
        try {
            config.save(configFile);
//            config.options().copyDefaults(true);
//            config.options().parseComments(true);
        } catch (IOException e) {
            plugin.getLogger().severe("Could not save config to " + configFile.getName());
        }
    }

    protected FileConfiguration get() {
        if (config == null) reload();
        return config;
    }

    protected void set(@NotNull String path, @Nullable Object value) {
        set(path, value, false);
    }

    protected void set(@NotNull String path, @Nullable Object value, boolean save) {
        this.config.set(path, value);
        if (save) save();
    }

}
