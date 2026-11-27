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

public class CheckSubCommand implements SubCommand {

    @Override
    public @NotNull List<ArgumentBuilder<CommandSourceStack, ?>> argument() {
        return List.of(
                Commands.literal("check")
                        .requires(ctx -> ctx.getSender().hasPermission(Permissions.COMMAND_STATE_CHECK.permission()))
                        .executes(this::execute)
                        .then(Commands.argument("player", ArgumentTypes.players())
                                .requires(ctx -> ctx.getSender().hasPermission(Permissions.COMMAND_OTHER.permission()))
                                .executes(this::execute)
                        )
        );
    }

    @Override
    public int execute(CommandContext<CommandSourceStack> ctx) {
        CommandSender commandSender = ctx.getSource().getSender();
        List<Player> targets = CommandUtils.resolvePlayers(ctx, "player");

        if (CommandUtils.checkTargetsIsNull(commandSender, targets)) {
            return Command.SINGLE_SUCCESS;
        }

        for (Player target : targets) {
            TagResolver[] tagResolvers = {
                    Placeholder.unparsed("sender", commandSender.getName()),
                    Placeholder.unparsed("target", target.getName()),
                    Placeholder.parsed("status", StateToggleUtils.keepInventory(target) ? MessagesConfig.instance().command_check_status_enabled() : MessagesConfig.instance().command_check_status_disabled())
            };
            if (commandSender.equals(target)) target.sendMessage(MiniMessage.miniMessage().deserialize(
                    MessagesConfig.instance().command_check_message(),
                    tagResolvers
            )); else commandSender.sendMessage(MiniMessage.miniMessage().deserialize(
                    MessagesConfig.instance().command_check_message_for_sender(),
                    tagResolvers
            ));
        }

        return Command.SINGLE_SUCCESS;
    }
}
