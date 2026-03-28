package com.github.assemblyorg.keepInventory.configs;

import com.github.assemblyorg.keepInventory.KeepInventory;
import org.jetbrains.annotations.NotNull;

public class MainConfig extends Config {

    public MainConfig(@NotNull KeepInventory plugin, @NotNull String resourcePath) {
        super(plugin, resourcePath);
    }

    @NotNull
    public String keyName() {
        return get().getString("key_name" , "keepInventory");
    }

    public boolean defaultKeepInventoryState() {
        return get().getBoolean("default_keep_inventory_state", false);
    }

}
