package com.github.assemblyorg.keepInventory.config;

import com.github.assemblyorg.keepInventory.KeepInventory;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

public abstract class Config {

    private final String resourcePath;
    private final File configFile;
    private final FileConfiguration configDefault;
    private FileConfiguration config;

    public Config(@NotNull String resourcePath) {
        this.resourcePath = resourcePath;
        this.configFile = new File(KeepInventory.instance().getDataFolder(), resourcePath);
        this.configDefault = YamlConfiguration.loadConfiguration(new InputStreamReader(Objects.requireNonNull(KeepInventory.instance().getResource(resourcePath)), StandardCharsets.UTF_8));

        try {
            reload();
        } catch (IOException | InvalidConfigurationException e) {
            throw new RuntimeException(e);
        }
    }

    public void reload() throws IOException, InvalidConfigurationException {
        File parentFile = configFile.getParentFile();
        if (!parentFile.exists()) parentFile.mkdirs();
        if (!configFile.exists()) KeepInventory.instance().saveResource(resourcePath, false);

        // https://github.com/tchristofferson/Config-Updater/ <- need to rewrite for this project

        config = YamlConfiguration.loadConfiguration(configFile);
    }

    public void save() {
        try {
            config.save(configFile);
        } catch (IOException e) {
            KeepInventory.instance().getComponentLogger().error("Could not save config to {}", configFile.getName());
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
