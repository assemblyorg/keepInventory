package com.github.assemblyorg.keepInventory.command.keepInventory;

import com.github.assemblyorg.keepInventory.command.SubCommand;
import com.github.assemblyorg.keepInventory.config.MainConfig;
import com.github.assemblyorg.keepInventory.config.MessagesConfig;
import com.github.assemblyorg.keepInventory.permission.Permissions;
import com.mojang.brigadier.Command;
import com.mojang.brigadier.builder.ArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import io.papermc.paper.command.brigadier.Commands;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.minimessage.tag.resolver.Placeholder;
import net.kyori.adventure.text.minimessage.tag.resolver.TagResolver;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.InvalidConfigurationException;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.time.Clock;
import java.util.List;

public class ReloadSubCommand implements SubCommand {

    @Override
    public @NotNull List<ArgumentBuilder<CommandSourceStack, ?>> argument() {
        return List.of(
                Commands.literal("reload")
                        .requires(ctx -> ctx.getSender().hasPermission(Permissions.RELOAD.permission()))
                        .executes(this::execute)
        );
    }

    @Override
    public int execute(CommandContext<CommandSourceStack> ctx) {
        CommandSender commandSender = ctx.getSource().getSender();

        long timestamp = Clock.systemUTC().millis();
        Component message = Component.empty();

        try {
            MainConfig.instance().reload();
            MessagesConfig.instance().reload();

            TagResolver[] tagResolvers = {
                    Placeholder.unparsed("sender", commandSender.getName()),
                    Placeholder.unparsed("duration", String.valueOf(Clock.systemUTC().millis() - timestamp))
            };

            message = MiniMessage.miniMessage().deserialize(
                    MessagesConfig.instance().command_reload_message(),
                    tagResolvers
            );
        } catch (IOException | InvalidConfigurationException e) {
            TagResolver[] tagResolvers = {
                    Placeholder.unparsed("sender", commandSender.getName()),
                    Placeholder.unparsed("duration", String.valueOf(Clock.systemUTC().millis() - timestamp)),
                    Placeholder.unparsed("error", e.getMessage())
            };

            message = MiniMessage.miniMessage().deserialize(
                    MessagesConfig.instance().command_reload_error_message(),
                    tagResolvers
            );
        } finally {
            commandSender.sendMessage(message);
        }

        return Command.SINGLE_SUCCESS;
    }

}
