package com.github.assemblyDir.keepInventory.api;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class KeepInventorySwitcherEvent extends Event {

    private static final HandlerList HANDLER_LIST = new HandlerList();
    private final Player player;
    private final CommandSender switchedBy;
    private final boolean keepInventoryState;

    public KeepInventorySwitcherEvent(Player player, CommandSender switchedBy, boolean keepInventoryState) {
        this.player = player;
        this.switchedBy = switchedBy;
        this.keepInventoryState = keepInventoryState;
    }

    public static HandlerList getHandlerList() {
        return HANDLER_LIST;
    }

    public Player getPlayer() {
        return player;
    }

    public CommandSender getSwitchedBy() {
        return switchedBy;
    }

    public boolean getKeepInventoryState() {
        return keepInventoryState;
    }

    @Override
    public HandlerList getHandlers() {
        return HANDLER_LIST;
    }
}
