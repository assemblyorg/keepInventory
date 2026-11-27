package com.github.assemblyorg.keepInventory.permission;

import com.github.assemblyorg.keepInventory.KeepInventory;
import org.bukkit.permissions.Permission;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

public enum Permissions {

    RELOAD(new Permission("keepinventory.reload", null, Permission.DEFAULT_PERMISSION)),
    COMMAND_USE(new Permission("keepinventory.command.use", null, Permission.DEFAULT_PERMISSION, null)),
    COMMAND_OTHER(new Permission("keepinventory.command.other", null, Permission.DEFAULT_PERMISSION, Map.of(COMMAND_USE.permission().getName(), true))),
    COMMAND_STATE_ON(new Permission("keepinventory.command.state.on", null, Permission.DEFAULT_PERMISSION, Map.of(COMMAND_USE.permission().getName(), true))),
    COMMAND_STATE_OFF(new Permission("keepinventory.command.state.off", null, Permission.DEFAULT_PERMISSION, Map.of(COMMAND_USE.permission().getName(), true))),
    COMMAND_STATE_CHECK(new Permission("keepinventory.command.state.check", null, Permission.DEFAULT_PERMISSION, Map.of(COMMAND_USE.permission().getName(), true)));

    private final @NotNull Permission permission;

    Permissions(@NotNull Permission permission) {
        this.permission = permission;
    }

    public @NotNull Permission permission() {
        return permission;
    }

    public static void registerPermissions() {
        var plugin = KeepInventory.instance();
        for (Permissions permission : Permissions.values())
            plugin.getServer().getPluginManager().addPermission(permission.permission());
    }

}
