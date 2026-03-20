package com.github.assemblyDir.keepInventory;

import org.bukkit.plugin.java.JavaPlugin;

public final class KeepInventory extends JavaPlugin {

    @Override
    public void onEnable() {
        KeepInventoryPermissionManager permissionManager = new KeepInventoryPermissionManager(this);
        KeepInventoryStateManager stateManager = new KeepInventoryStateManager(this);

        new KeepInventoryListener(this, stateManager);
        new KeepInventoryCommand(this, permissionManager, stateManager);

        getLogger().info("plugin enabled");
    }

    @Override
    public void onDisable() {
        getLogger().info("plugin disabled");
    }

}
