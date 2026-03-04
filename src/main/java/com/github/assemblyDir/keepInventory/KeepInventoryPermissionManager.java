package com.github.assemblyDir.keepInventory;

import org.bukkit.permissions.Permission;

import java.util.List;

public class KeepInventoryPermissionManager {

    private static final List<Permission> permissions = List.of(
            new Permission("keepinventory.command.self"),
            new Permission("keepinventory.command.other")
    );

    public static void permissions(KeepInventory instance) {
        for (Permission perm : permissions) instance.getServer().getPluginManager().addPermission(perm);
    }

}
