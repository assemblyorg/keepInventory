package com.github.assemblyorg.keepInventory;

import com.github.assemblyorg.keepInventory.configs.ConfigManager;
import com.github.assemblyorg.keepInventory.listeners.ListenerManager;
import com.github.assemblyorg.keepInventory.utils.UtilManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class KeepInventory extends JavaPlugin {

    @Override
    public void onEnable() {
        var configManager = new ConfigManager(this);
        var utilManager = new UtilManager(this, configManager);
        var listenerManager = new ListenerManager(this, configManager, utilManager);

        listenerManager.registerListeners();

        getLogger().info("Plugin keepInventory v2.0.0 enabled");
    }

    @Override
    public void onDisable() {
        getLogger().info("Plugin keepInventory v2.0.0 disabled");
    }

}
