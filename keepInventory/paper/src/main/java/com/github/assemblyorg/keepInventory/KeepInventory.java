package com.github.assemblyorg.keepInventory;

import com.github.assemblyorg.keepInventory.configs.ConfigManager;
import com.github.assemblyorg.keepInventory.listeners.ListenerManager;
import com.github.assemblyorg.keepInventory.permissions.PermissionManager;
import com.github.assemblyorg.keepInventory.utils.UtilManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class KeepInventory extends JavaPlugin {

    @Override
    public void onEnable() {
        ConfigManager configManager = new ConfigManager(this);
        UtilManager utilManager = new UtilManager(this);

        new ListenerManager(this, utilManager.keepInventoryState()).registerListeners();
        new PermissionManager(this).registerPermissions();

        getLogger().info("plugin enabled");
    }

    @Override
    public void onDisable() {
        getLogger().info("plugin disabled");
    }

}
