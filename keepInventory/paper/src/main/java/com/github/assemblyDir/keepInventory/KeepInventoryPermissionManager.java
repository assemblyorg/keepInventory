package com.github.assemblyDir.keepInventory;

import org.bukkit.permissions.Permission;
import org.bukkit.permissions.PermissionDefault;

public final class KeepInventoryPermissionManager {

    KeepInventoryPermissionManager(KeepInventory plugin) {
        for (Permissions value : Permissions.values()) plugin.getServer().getPluginManager().addPermission(value.permission);
    }

    enum Permissions {
        COMMAND_SELF("keepinventory.command.self", PermissionDefault.TRUE),
        COMMAND_OTHER("keepinventory.command.other", PermissionDefault.OP);

        public final String node;
        public final PermissionDefault defaultPermission;
        public final Permission permission;

        Permissions(String node, PermissionDefault defaultPermission) {
            this.node = node;
            this.defaultPermission = defaultPermission;
            this.permission = new Permission(node, defaultPermission);
        }

    }

}
