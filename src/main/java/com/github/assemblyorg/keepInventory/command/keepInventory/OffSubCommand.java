package com.github.assemblyorg.keepInventory.command.keepInventory;

import com.github.assemblyorg.keepInventory.command.SubCommand;
import com.github.assemblyorg.keepInventory.config.MessagesConfig;
import com.github.assemblyorg.keepInventory.permission.Permissions;
import com.github.assemblyorg.keepInventory.util.CommandUtils;
import com.github.assemblyorg.keepInventory.util.StateToggleUtils;
import com.mojang.brigadier.Command;
import com.mojang.brigadier.builder.ArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import io.papermc.paper.command.brigadier.Commands;
import io.papermc.paper.command.brigadier.argument.ArgumentTypes;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.minimessage.tag.resolver.Placeholder;
import net.kyori.adventure.text.minimessage.tag.resolver.TagResolver;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class OffSubCommand implements SubCommand {

    @Override
    public @NotNull List<ArgumentBuilder<CommandSourceStack, ?>> argument() {
        return List.of(
                Commands.literal("off")
                        .requires(ctx -> ctx.getSender().hasPermission(Permissions.COMMAND_STATE_OFF.permission()))
                        .executes(this::execute)
                        .then(Commands.argument("players", ArgumentTypes.players())
                                .requires(ctx -> ctx.getSender().hasPermission(Permissions.COMMAND_OTHER.permission()))
                                .executes(this::execute)
                        )
        );
    }

    @Override
    public int execute(CommandContext<CommandSourceStack> ctx) {
        CommandSender commandSender = ctx.getSource().getSender();
        List<Player> targets = CommandUtils.resolvePlayers(ctx, "players");

        if (CommandUtils.checkTargetsIsNull(commandSender, targets)) {
            return Command.SINGLE_SUCCESS;
        }

        for (Player target : targets) {
            TagResolver[] tagResolvers = {
                    Placeholder.unparsed("sender", commandSender.getName()),
                    Placeholder.unparsed("target", target.getName()),
            };
            if (!StateToggleUtils.keepInventory(target)) {
                target.sendMessage(MiniMessage.miniMessage().deserialize(
                        MessagesConfig.instance().command_off_already(),
                        tagResolvers
                ));
                if (!target.equals(commandSender)) commandSender.sendMessage(MiniMessage.miniMessage().deserialize(
                        MessagesConfig.instance().command_off_already_for_sender(),
                        tagResolvers
                ));
            } else {
                target.sendMessage(MiniMessage.miniMessage().deserialize(
                        MessagesConfig.instance().command_off_message(),
                        tagResolvers
                ));
                if (!target.equals(commandSender)) commandSender.sendMessage(MiniMessage.miniMessage().deserialize(
                        MessagesConfig.instance().command_off_message_for_sender(),
                        tagResolvers
                ));
                StateToggleUtils.keepInventory(target, false);
            }
        }

        return Command.SINGLE_SUCCESS;
    }
}
