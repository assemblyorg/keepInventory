package com.github.assemblyorg.keepInventory.permissions;

import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

public final class PermissionManager {

    private final JavaPlugin plugin;

    public PermissionManager(@NotNull JavaPlugin plugin) {
        this.plugin = plugin;
    }

    public void registerPermissions() {
        for (Permissions permission : Permissions.values()) {
            plugin.getServer().getPluginManager().addPermission(permission.permission());
        }
    }

}
