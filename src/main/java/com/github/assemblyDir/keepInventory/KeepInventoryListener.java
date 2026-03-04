package com.github.assemblyDir.keepInventory;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class KeepInventoryListener implements Listener {

    @EventHandler
    public void onDeath(PlayerDeathEvent event) {
        if (KeepInventoryUtil.keepInventory(event.getPlayer().getPersistentDataContainer())) {
            event.setKeepInventory(true);
            event.setKeepLevel(true);
            event.getDrops().clear();
            event.setDroppedExp(0);
        }
    }

}
