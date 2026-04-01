package com.github.assemblyorg.keepInventory.configs;

import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class MainConfig extends Config {

    public MainConfig(@NotNull JavaPlugin plugin, @NotNull String resourcePath) {
        super(plugin, resourcePath);
    }

    public @NotNull String keyName() {
        return get().getString("key_name" , "keepInventory");
    }

    public boolean defaultKeepInventoryState() {
        return get().getBoolean("default_keep_inventory_state", false);
    }

    public List<String> commandAliases() {
        return get().getStringList("command_aliases");
    }

}
