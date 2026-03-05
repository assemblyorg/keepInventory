package com.github.assemblyDir.keepInventory;

import org.bukkit.permissions.Permission;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Map;

public final class KeepInventoryPermissions {

    private static final List<Permission> permissions = List.of(
            new Permission("keepinventory.command.self", Permission.DEFAULT_PERMISSION),
            new Permission("keepinventory.command.other", Permission.DEFAULT_PERMISSION, Map.of("keepinventory.command.self", true))
    );

    public static void register(@NotNull JavaPlugin instance) {
        for (Permission permission : permissions) instance.getServer().getPluginManager().addPermission(permission);
    }

}
