package com.github.assemblyorg.keepInventory.utils;

import com.github.assemblyorg.keepInventory.KeepInventory;
import com.github.assemblyorg.keepInventory.configs.ConfigManager;

public final class UtilManager {

    private final StateToggle stateToggle;

    public UtilManager(KeepInventory plugin, ConfigManager configManager) {
        this.stateToggle = new StateToggle(plugin, configManager.mainConfig());
    }

    public StateToggle stateToggle() {
        return stateToggle;
    }

}
