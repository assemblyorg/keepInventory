package com.github.assemblyorg.keepInventory.commands.keepInventory;

import com.github.assemblyorg.keepInventory.commands.Command;
import com.github.assemblyorg.keepInventory.commands.keepInventory.subCommands.action.OnSubCommand;
import com.github.assemblyorg.keepInventory.commands.keepInventory.subCommands.SubCommand;
import com.github.assemblyorg.keepInventory.configs.ConfigManager;
import com.github.assemblyorg.keepInventory.configs.MainConfig;
import com.github.assemblyorg.keepInventory.utils.UtilManager;
import com.mojang.brigadier.tree.LiteralCommandNode;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import io.papermc.paper.command.brigadier.Commands;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import org.jspecify.annotations.NonNull;

import java.util.List;

public class KeepInventoryCommand implements Command {

    private final MainConfig mainConfig;
    private final List<SubCommand> subCommands;

    public KeepInventoryCommand(JavaPlugin plugin, ConfigManager configManager, UtilManager utilManager) {
        this.mainConfig = configManager.mainConfig();
        this.subCommands = List.of(
            new OnSubCommand(plugin, configManager, utilManager)
        );
    }

    @Override
    public @NonNull LiteralCommandNode<CommandSourceStack> commandNode() {
        var root = Commands.literal("keepInventory");
        for (SubCommand subCommand : subCommands) for (var argument : subCommand.argument()) root.then(argument);
        return root.build();
    }

    @Override
    public @NotNull List<String> aliases() {
        return mainConfig.commandAliases();
    }

}
