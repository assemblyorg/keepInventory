package com.github.assemblyorg.keepInventory.configs;

import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

public class MessagesConfig extends Config {

    public MessagesConfig(@NotNull JavaPlugin plugin, @NotNull String resourcePath) {
        super(plugin, resourcePath);
    }

    public @NotNull String command_only_players() {
        String path = "command.only_players";
        return get().getString(path, getDefault().getString(path, ""));
    }

    public @NotNull String command_on_message() {
        String path = "command.on.message";
        return get().getString(path, getDefault().getString(path, ""));
    }

    public @NotNull String command_on_already() {
        String path = "command.on.already";
        return get().getString(path, getDefault().getString(path, ""));
    }

    public @NotNull String command_off_message() {
        String path = "command.off.message";
        return get().getString(path, getDefault().getString(path, ""));
    }

    public @NotNull String command_off_already() {
        String path = "command.off.already";
        return get().getString(path, getDefault().getString(path, ""));
    }

    public @NotNull String command_check_message() {
        String path = "command.check.message";
        return get().getString(path, getDefault().getString(path, ""));
    }

    public @NotNull String command_check_status_enabled() {
        String path = "command.check.status.enabled";
        return get().getString(path, getDefault().getString(path, ""));
    }

    public @NotNull String command_check_status_disabled() {
        String path = "command.check.status.disabled";
        return get().getString(path, getDefault().getString(path, ""));
    }

}
