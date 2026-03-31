package com.github.assemblyorg.keepInventory.commands.keepInventoryCommand;

import com.github.assemblyorg.keepInventory.commands.Command;
import com.github.assemblyorg.keepInventory.commands.keepInventoryCommand.subcommands.OnSubCommand;
import com.github.assemblyorg.keepInventory.commands.keepInventoryCommand.subcommands.SubCommand;
import com.github.assemblyorg.keepInventory.configs.ConfigManager;
import com.github.assemblyorg.keepInventory.configs.MainConfig;
import com.github.assemblyorg.keepInventory.utils.UtilManager;
import com.mojang.brigadier.builder.ArgumentBuilder;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.tree.LiteralCommandNode;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import io.papermc.paper.command.brigadier.Commands;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import org.jspecify.annotations.NonNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class KeepInventoryCommand implements Command {

    private final MainConfig mainConfig;
    private final List<SubCommand> subCommands = new ArrayList<>();

    public KeepInventoryCommand(JavaPlugin plugin, ConfigManager configManager, UtilManager utilManager) {
        this.mainConfig = configManager.mainConfig();

        subCommands.add(new OnSubCommand(plugin, configManager, utilManager));
    }

    @Override
    public @NonNull LiteralCommandNode<CommandSourceStack> commandNode() {
        var root = Commands.literal("keepInventory");
        for (SubCommand subCommand : subCommands) for (var argument : subCommand.argumentBuilder()) root.then(argument);
        return root.build();
    }

    @Override
    public @NotNull Collection<String> aliases() {
        return mainConfig.commandAliases();
    }

}
