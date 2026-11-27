package com.github.assemblyorg.keepInventory.command.keepInventory;

import com.github.assemblyorg.keepInventory.command.Command;
import com.github.assemblyorg.keepInventory.command.SubCommand;
import com.github.assemblyorg.keepInventory.config.MainConfig;
import com.github.assemblyorg.keepInventory.permission.Permissions;
import com.mojang.brigadier.tree.LiteralCommandNode;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import io.papermc.paper.command.brigadier.Commands;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class KeepInventoryCommand implements Command {

    private static final SubCommand[] subCommands = {
            new OnSubCommand(),
            new OffSubCommand(),
            new CheckSubCommand(),
            new ReloadSubCommand()
    };

    @Override
    public @NotNull LiteralCommandNode<CommandSourceStack> commandNode() {
        var root = Commands.literal("keepInventory").requires(ctx -> ctx.getSender().hasPermission(Permissions.COMMAND_USE.permission()));
        for (SubCommand subCommand : subCommands) for (var argument : subCommand.argument()) root.then(argument);
        return root.build();
    }

    @Override
    public @NotNull List<String> aliases() {
        return MainConfig.instance().command_aliases();
    }

}
