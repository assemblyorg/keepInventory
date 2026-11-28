package com.github.assemblyorg.keepInventory;

import com.github.assemblyorg.keepInventory.command.CommandManager;
import com.github.assemblyorg.keepInventory.listener.ListenerManager;
import com.github.assemblyorg.keepInventory.permission.Permissions;
import org.bukkit.plugin.java.JavaPlugin;

public final class KeepInventory extends JavaPlugin {

    private static final KeepInventory instance = new KeepInventory();

    public static KeepInventory instance() {
        return instance;
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

}
