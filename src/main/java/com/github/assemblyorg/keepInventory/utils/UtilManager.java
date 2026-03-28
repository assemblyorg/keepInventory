package com.github.assemblyorg.keepInventory.utils;

import com.github.assemblyorg.keepInventory.KeepInventory;
import com.github.assemblyorg.keepInventory.configs.ConfigManager;
import org.jetbrains.annotations.NotNull;

public final class UtilManager {

    private final StateToggle stateToggle;

    public UtilManager(@NotNull KeepInventory plugin, @NotNull ConfigManager configManager) {
        this.stateToggle = new StateToggle(plugin, configManager.mainConfig());
    }

    @NotNull
    public StateToggle stateToggle() {
        return stateToggle;
    }

}
