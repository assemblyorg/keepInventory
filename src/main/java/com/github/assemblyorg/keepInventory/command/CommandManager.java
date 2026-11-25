package com.github.assemblyorg.keepInventory.command;

import com.github.assemblyorg.keepInventory.KeepInventory;
import com.github.assemblyorg.keepInventory.command.keepInventory.KeepInventoryCommand;
import io.papermc.paper.command.brigadier.Commands;
import io.papermc.paper.plugin.lifecycle.event.types.LifecycleEvents;

public final class CommandManager {

    private static final Command[] commands = {
            new KeepInventoryCommand()
    };

    public static void registerCommands() {
        KeepInventory.instance().getLifecycleManager().registerEventHandler(LifecycleEvents.COMMANDS, ctx -> {
            Commands registrar = ctx.registrar();
            for (Command command : commands) {
                registrar.register(command.commandNode(), command.description(), command.aliases());
            }
        });
    }

    /*
    TODO Command manager

    /keepinventory on
    /keepinventory on player
    /keepinventory off
    /keepinventory off player
    /keepinventory check
    /keepinventory check player
    /keepinventory config reload
    /keepInventory config get key
    /keepinventory config set key value

    CommandManager:
    RootCommand:
        OnSubcommand
        OffSubcommand
        CheckSubcommand
        ReloadSubcommand
        ConfigSubcommand:
            ConfigSetSubcommand
            ConfigSaveSubcommand
            ConfigRestoreSubcommand

    UtilManager:
        StateToggle
        MessageGetter (String -> Component)
     */

}
