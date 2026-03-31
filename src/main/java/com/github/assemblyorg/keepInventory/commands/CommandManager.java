package com.github.assemblyorg.keepInventory.commands;

import com.github.assemblyorg.keepInventory.commands.keepInventoryCommand.KeepInventoryCommand;
import com.github.assemblyorg.keepInventory.configs.ConfigManager;
import com.github.assemblyorg.keepInventory.utils.UtilManager;
import io.papermc.paper.command.brigadier.Commands;
import io.papermc.paper.plugin.lifecycle.event.types.LifecycleEvents;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public final class CommandManager {

    private final JavaPlugin plugin;
    private final List<Command> commands = new ArrayList<>();

    public CommandManager(@NotNull JavaPlugin plugin, @NotNull ConfigManager configManager, @NotNull UtilManager utilManager) {
        this.plugin = plugin;

        commands.add(new KeepInventoryCommand(plugin, configManager, utilManager));
    }

    public void registerCommands() {
        plugin.getLifecycleManager().registerEventHandler(LifecycleEvents.COMMANDS, ctx -> {
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
    /keepinventory config reload
    /keepinventory config set key value
    /keepinventory config restore

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
