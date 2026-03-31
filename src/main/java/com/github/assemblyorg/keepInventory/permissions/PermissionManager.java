package com.github.assemblyorg.keepInventory.permissions;

import org.bukkit.permissions.Permission;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public final class PermissionManager {

    private final JavaPlugin plugin;
    private final List<Permission> permissions = new ArrayList<>();

    public PermissionManager(JavaPlugin plugin) {
        this.plugin = plugin;

        permissions.add(new Permission(Permissions.COMMAND_SELF.id(), Permission.DEFAULT_PERMISSION));
        permissions.add(new Permission(Permissions.COMMAND_OTHER.id(), Permission.DEFAULT_PERMISSION, Map.of(Permissions.COMMAND_SELF.name(), true)));
    }

    public void registerPermissions() {
        for (Permission permission : permissions) plugin.getServer().getPluginManager().addPermission(permission);
    }

}
