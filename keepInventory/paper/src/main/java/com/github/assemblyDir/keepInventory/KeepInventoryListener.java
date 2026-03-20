package com.github.assemblyDir.keepInventory;

import com.github.assemblyDir.keepInventory.api.KeepInventoryDeathEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public final class KeepInventoryListener implements Listener {

    private final KeepInventoryStateManager stateManager;

    KeepInventoryListener(KeepInventory plugin, KeepInventoryStateManager stateManager) {
        this.stateManager = stateManager;

        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent event) {
        boolean keepInventoryEnabled = stateManager.keepInventory(event.getPlayer());
        if (keepInventoryEnabled) {
            event.setKeepInventory(true);
            event.setKeepLevel(true);
            event.getDrops().clear();
            event.setDroppedExp(0);
        }
        new KeepInventoryDeathEvent(event, keepInventoryEnabled).callEvent();
    }

}
