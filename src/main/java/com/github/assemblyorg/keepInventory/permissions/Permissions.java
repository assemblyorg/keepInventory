package com.github.assemblyorg.keepInventory.permissions;

import org.bukkit.permissions.Permission;
import org.bukkit.permissions.PermissionDefault;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Map;

public enum Permissions {

    COMMAND_SELF("keepinventory.command.self", null, Permission.DEFAULT_PERMISSION, null),
    COMMAND_OTHER("keepinventory.command.other", null, Permission.DEFAULT_PERMISSION, Map.of(COMMAND_SELF.id(), true));

    private final String id;
    private final String description;
    private final PermissionDefault permissionDefault;
    private final Map<String, Boolean> children;

    Permissions(@NotNull String id, @Nullable String description, @Nullable PermissionDefault permissionDefault, @Nullable Map<String, Boolean> children) {
        this.id = id;
        this.description = description;
        this.permissionDefault = permissionDefault;
        this.children = children;
    }

    public String id() {
        return id;
    }

    public String description() {
        return description;
    }

    public PermissionDefault permissionDefault() {
        return permissionDefault;
    }

    public Map<String, Boolean> children() {
        return children;
    }

    public Permission permission() {
        return new Permission(id, description, permissionDefault, children);
    }

}
