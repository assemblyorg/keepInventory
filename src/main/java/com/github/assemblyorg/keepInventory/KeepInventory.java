package com.github.assemblyorg.keepInventory;

import com.github.assemblyorg.keepInventory.configs.ConfigManager;
import com.github.assemblyorg.keepInventory.listeners.ListenerManager;
import com.github.assemblyorg.keepInventory.utils.UtilManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class KeepInventory extends JavaPlugin {

    @Override
    public void onEnable() {
        ConfigManager configManager = new ConfigManager(this);
        UtilManager utilManager = new UtilManager(this, configManager);

        new ListenerManager(this, configManager, utilManager);

        getLogger().info("Plugin keepInventory v2.0.0 enabled");
    }

    @Override
    public void onDisable() {
        getLogger().info("Plugin keepInventory v2.0.0 disabled");
    }

}
