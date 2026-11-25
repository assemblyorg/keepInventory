package com.github.assemblyorg.keepInventory.listener;

import com.github.assemblyorg.keepInventory.util.StateToggleUtils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class PlayerDeathListener implements Listener {

    @EventHandler
    public void playerDeathEvent(PlayerDeathEvent event) {
        Player player = event.getPlayer();

        boolean keepInventoryEnabled = StateToggleUtils.keepInventory(player);
        if (keepInventoryEnabled) {
            event.setKeepInventory(true);
            event.setKeepLevel(true);
            event.getDrops().clear();
            event.setDroppedExp(0);
        }
    }

}
