package com.github.assemblyDir.keepInventory;

import com.github.assemblyDir.keepInventory.api.KeepInventoryDeathEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

public final class KeepInventoryListener implements Listener {

    public static void register(@NotNull JavaPlugin instance) {
        instance.getServer().getPluginManager().registerEvents(new KeepInventoryListener(), instance);
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent event) {
        boolean keepInventoryEnabled = KeepInventoryUtil.keepInventory(event.getPlayer().getPersistentDataContainer());
        new KeepInventoryDeathEvent(event, keepInventoryEnabled).callEvent();
        if (keepInventoryEnabled) {
            event.setKeepInventory(true);
            event.setKeepLevel(true);
            event.getDrops().clear();
            event.setDroppedExp(0);
        }
    }

}
