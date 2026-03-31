package com.github.assemblyorg.keepInventory.utils;

import com.github.assemblyorg.keepInventory.configs.ConfigManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

public final class UtilManager {

    private final StateToggle stateToggle;

    public UtilManager(@NotNull JavaPlugin plugin, @NotNull ConfigManager configManager) {
        this.stateToggle = new StateToggle(plugin, configManager.mainConfig());
    }

    public @NotNull StateToggle stateToggle() {
        return stateToggle;
    }

}
