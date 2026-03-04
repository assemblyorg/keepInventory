package com.github.assemblyDir.keepInventory;

import io.papermc.paper.plugin.lifecycle.event.types.LifecycleEvents;
import org.bukkit.plugin.java.JavaPlugin;

public final class KeepInventory extends JavaPlugin {

    private static KeepInventoryUtil util;

    @Override
    public void onEnable() {
        KeepInventoryUtil.namespacedKey(this);
        KeepInventoryPermissionManager.permissions(this);

        getServer().getPluginManager().registerEvents(new KeepInventoryListener(), this);
        getLifecycleManager().registerEventHandler(
                LifecycleEvents.COMMANDS,
                commands -> commands.registrar().register(KeepInventoryCommand.commandNode(), KeepInventoryCommand.description(), KeepInventoryCommand.aliases())
        );

        getLogger().info("plugin enabled");
    }

    @Override
    public void onDisable() {
        getLogger().info("plugin disabled");
    }

}
