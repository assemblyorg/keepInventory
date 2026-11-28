package com.github.assemblyorg.keepInventory.config;

import java.util.List;

public class MainConfig extends Config {

    private static final MainConfig INSTANCE = new MainConfig("config.yml");

    private MainConfig(String resourcePath) {
        super(resourcePath);
    }

    public static MainConfig instance() {
        return INSTANCE;
    }

    public String key_name() {
        String path = "key_name";
        return get().getString(path , getDefault().getString(path));
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
