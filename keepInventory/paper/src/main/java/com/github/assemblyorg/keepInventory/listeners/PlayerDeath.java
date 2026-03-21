package com.github.assemblyorg.keepInventory.listeners;

import com.github.assemblyorg.keepInventory.api.KeepInventoryDeathEvent;
import com.github.assemblyorg.keepInventory.utils.KeepInventoryState;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class PlayerDeath implements Listener {

    private final KeepInventoryState keepInventoryState;

    public PlayerDeath(KeepInventoryState keepInventoryState) {
        this.keepInventoryState = keepInventoryState;
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent event) {
        boolean keepInventoryEnabled = keepInventoryState.get(event.getPlayer());

        if (keepInventoryEnabled) {
            event.setKeepInventory(true);
            event.setKeepLevel(true);
            event.getDrops().clear();
            event.setDroppedExp(0);
        }

        new KeepInventoryDeathEvent(event, keepInventoryEnabled).callEvent();
    }

}
