package com.github.assemblyorg.keepInventory.configs;

import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class MainConfig extends Config {

    public MainConfig(@NotNull JavaPlugin plugin, @NotNull String resourcePath) {
        super(plugin, resourcePath);
    }

    public @NotNull String key_name() {
        String path = "key_name";
        return get().getString(path , getDefault().getString(path, ""));
    }

    public boolean default_keep_inventory_state() {
        String path = "default_keep_inventory_state";
        return get().getBoolean(path, getDefault().getBoolean(path));
    }

    public List<String> command_aliases() {
        String path = "command_aliases";
        return get().getStringList(path);
    }

}
