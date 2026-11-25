package com.github.assemblyorg.keepInventory;

import com.github.assemblyorg.keepInventory.command.CommandManager;
import com.github.assemblyorg.keepInventory.listener.ListenerManager;
import com.github.assemblyorg.keepInventory.permission.Permissions;
import org.bukkit.plugin.java.JavaPlugin;

public final class KeepInventory extends JavaPlugin {

    private static KeepInventory INSTANCE;

    public static KeepInventory instance() {
        assert INSTANCE != null;
        return INSTANCE;
    }

    @Override
    public void onLoad() {
        try {
            INSTANCE = this;
        } finally {
            getComponentLogger().info("Loading complete");
        }
    }

    @Override
    public void onEnable() {
        try {
            Permissions.registerPermissions();
            ListenerManager.registerListeners();
            CommandManager.registerCommands();
        } finally {
            getLogger().info("Enabling complete");
        }
    }

    @Override
    public void onDisable() {
        try {

        } finally {
            getLogger().info("Disabling complete");
        }
    }

}
