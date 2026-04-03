package com.github.assemblyorg.keepInventory.commands.keepInventory.subCommands;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.builder.ArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public interface SubCommand {

    @NotNull List<ArgumentBuilder<CommandSourceStack, ?>> argument();

    default int execute(CommandContext<CommandSourceStack> ctx) {
        return Command.SINGLE_SUCCESS;
    }

}
