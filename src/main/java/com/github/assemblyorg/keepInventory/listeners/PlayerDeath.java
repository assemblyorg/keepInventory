package com.github.assemblyorg.keepInventory.listeners;

import com.github.assemblyorg.keepInventory.utils.StateToggle;
import com.github.assemblyorg.keepInventory.utils.UtilManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.jetbrains.annotations.NotNull;

public class PlayerDeath implements Listener {

    private final StateToggle stateToggle;

    public PlayerDeath(@NotNull UtilManager utilManager) {
        this.stateToggle = utilManager.stateToggle();
    }

    @EventHandler
    public void playerDeathEvent(PlayerDeathEvent event) {
        Player player = event.getPlayer();

        boolean keepInventoryEnabled = stateToggle.keepInventory(player);
        if (keepInventoryEnabled) {
            event.setKeepInventory(true);
            event.setKeepLevel(true);
            event.getDrops().clear();
            event.setDroppedExp(0);
        }
    }

}
