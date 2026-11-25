package com.github.assemblyorg.keepInventory.listener;

import com.github.assemblyorg.keepInventory.config.MainConfig;
import com.github.assemblyorg.keepInventory.util.StateToggleUtils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.persistence.PersistentDataContainer;

public class PlayerJoinListener implements Listener {

    @EventHandler
    public void playerJoinEvent(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        PersistentDataContainer persistentDataContainer = player.getPersistentDataContainer();

        boolean hasState = persistentDataContainer.has(StateToggleUtils.namespacedKey());
        if (!hasState) StateToggleUtils.keepInventory(player, MainConfig.instance().default_keep_inventory_state());
    }

}
