package com.github.assemblyorg.keepInventory.commands.keepInventory.subCommands.action;

import com.github.assemblyorg.keepInventory.commands.keepInventory.subCommands.SubCommand;
import com.github.assemblyorg.keepInventory.configs.ConfigManager;
import com.github.assemblyorg.keepInventory.configs.MessagesConfig;
import com.github.assemblyorg.keepInventory.permissions.Permissions;
import com.github.assemblyorg.keepInventory.utils.CommandContextResolver;
import com.github.assemblyorg.keepInventory.utils.MiniMessages;
import com.github.assemblyorg.keepInventory.utils.StateToggle;
import com.github.assemblyorg.keepInventory.utils.UtilManager;
import com.mojang.brigadier.Command;
import com.mojang.brigadier.builder.ArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import io.papermc.paper.command.brigadier.Commands;
import io.papermc.paper.command.brigadier.argument.ArgumentTypes;
import net.kyori.adventure.text.Component;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Map;

public class OffSubCommand implements SubCommand {

    private final MessagesConfig messagesConfig;
    private final StateToggle stateToggle;
    private final CommandContextResolver commandContextResolver;
    private final MiniMessages miniMessages;

    public OffSubCommand(@NotNull ConfigManager configManager, @NotNull UtilManager utilManager) {
        this.messagesConfig = configManager.messagesConfig();
        this.stateToggle = utilManager.stateToggle();
        this.commandContextResolver = utilManager.commandContextResolver();
        this.miniMessages = utilManager.miniMessages();
    }

    @Override
    public @NotNull List<ArgumentBuilder<CommandSourceStack, ?>> argument() {
        return List.of(
                Commands.literal("off")
                        .requires(ctx -> ctx.getSender().hasPermission(Permissions.COMMAND_STATE_OFF.id()))
                        .executes(this::execute)
                        .then(Commands.argument("players", ArgumentTypes.players())
                                .requires(ctx -> ctx.getSender().hasPermission(Permissions.COMMAND_OTHER.id()))
                                .executes(this::execute)
                        )
        );
    }

    @Override
    public int execute(CommandContext<CommandSourceStack> ctx) {
        CommandSourceStack commandSourceStack = ctx.getSource();
        CommandSender commandSender = ctx.getSource().getSender();

        List<Player> targets = commandContextResolver.resolvePlayers(ctx, "players");

        if (targets == null) {
            String senderMessage = messagesConfig.command_only_players();
            Map<String, String> placeholders = Map.of(
                    "sender", commandSender.getName()
            );
            Component senderText = miniMessages.deserialize(senderMessage, placeholders);
            commandSender.sendMessage(senderText);
            return Command.SINGLE_SUCCESS;
        }

        for (Player target : targets) {
            Map<String, String> placeholders = Map.of(
                    "sender", commandSender.getName(),
                    "target", target.getName()
            );
            if (!stateToggle.keepInventory(target)) {
                String targetMessage = messagesConfig.command_off_already();
                String senderMessage = messagesConfig.command_off_already_for_sender();
                Component targetText = miniMessages.deserialize(targetMessage, placeholders);
                Component senderText = miniMessages.deserialize(senderMessage, placeholders);
                target.sendMessage(targetText);
                if (target.equals(commandSender)) target.sendMessage(targetText);
                else commandSender.sendMessage(senderText);
            } else {
                String targetMessage = messagesConfig.command_off_message();
                String senderMessage = messagesConfig.command_off_message_for_sender();
                Component targetText = miniMessages.deserialize(targetMessage, placeholders);
                Component senderText = miniMessages.deserialize(senderMessage, placeholders);
                target.sendMessage(targetText);
                if (!target.equals(commandSender)) commandSender.sendMessage(senderText);
                stateToggle.keepInventory(target, true);
            }
        }

        return Command.SINGLE_SUCCESS;
    }
}
