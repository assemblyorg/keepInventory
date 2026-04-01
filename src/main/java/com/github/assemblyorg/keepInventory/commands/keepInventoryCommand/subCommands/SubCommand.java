package com.github.assemblyorg.keepInventory.commands.keepInventoryCommand.subCommands;

import com.mojang.brigadier.builder.ArgumentBuilder;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public interface SubCommand {

    @NotNull List<ArgumentBuilder<CommandSourceStack, ?>> argument();

}
