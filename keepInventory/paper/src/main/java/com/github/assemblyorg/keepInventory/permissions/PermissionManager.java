package com.github.assemblyorg.keepInventory.permissions;

import com.github.assemblyorg.keepInventory.KeepInventory;

public class PermissionManager {

    private final KeepInventory plugin;
    private final Permissions[] permissionsList;

    public PermissionManager(KeepInventory plugin) {
        this.plugin = plugin;
        this.permissionsList = Permissions.values();
    }

    public void registerPermissions() {
        for (Permissions value : permissionsList) {
            plugin.getServer().getPluginManager().addPermission(value.permission);
        }
    }

}
