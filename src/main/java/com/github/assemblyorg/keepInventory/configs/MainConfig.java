package com.github.assemblyorg.keepInventory.configs;

import com.github.assemblyorg.keepInventory.KeepInventory;

public class MainConfig extends Config {

    public MainConfig(KeepInventory plugin, String resourcePath) {
        super(plugin, resourcePath);
    }

    public String keyName() {
        return get().getString("key_name" , "keepInventory");
    }

    public boolean defaultKeepInventoryState() {
        return get().getBoolean("default_keep_inventory_state", false);
    }

}
