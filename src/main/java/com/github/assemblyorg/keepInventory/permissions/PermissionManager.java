package com.github.assemblyorg.keepInventory.permissions;

import org.bukkit.plugin.java.JavaPlugin;

public final class PermissionManager {

    private final JavaPlugin plugin;

    public PermissionManager(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    public void registerPermissions() {
        for (Permissions permission : Permissions.values()) {
            plugin.getServer().getPluginManager().addPermission(permission.permission());
        }
    }

}
