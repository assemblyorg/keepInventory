package com.github.assemblyorg.keepInventory.configs;

import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

public class MainConfig extends Config {

    public MainConfig(@NotNull JavaPlugin plugin, @NotNull String resourcePath) {
        super(plugin, resourcePath);
    }

    public @NotNull String keyName() {
        return get().getString("key_name" , "keepInventory");
    }

    public void keyName(@NotNull String keyName) {
        set("key_name", keyName);
    }

    public boolean defaultKeepInventoryState() {
        return get().getBoolean("default_keep_inventory_state", false);
    }

    public void defaultKeepInventoryState(boolean defaultKeepInventoryState) {
        set("default_keep_inventory_state", defaultKeepInventoryState);
    }

}
