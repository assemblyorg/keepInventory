package com.github.assemblyDir.keepInventory;

import org.bukkit.plugin.java.JavaPlugin;

public final class KeepInventory extends JavaPlugin {

    @Override
    public void onEnable() {
        KeepInventoryNamespacedKey.register(this);
        KeepInventoryPermissions.register(this);
        KeepInventoryListener.register(this);
        KeepInventoryCommand.register(this);
        getLogger().info("plugin enabled");
    }

    @Override
    public void onDisable() {
        getLogger().info("plugin disabled");
    }

}
