package com.github.assemblyorg.keepInventory.utils;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.minimessage.tag.resolver.Placeholder;
import net.kyori.adventure.text.minimessage.tag.resolver.TagResolver;

import java.util.Map;

public class MiniMessages {

    private final MiniMessage miniMessage;

    public MiniMessages() {
        this.miniMessage = MiniMessage.miniMessage();
    }

    public Component deserialize(String message) {
        return miniMessage.deserialize(message);
    }

    public Component deserialize(String message, Map<String, String> placeholders) {
        TagResolver[] tagResolvers = placeholders.entrySet().stream()
                .map(entry -> Placeholder.parsed(entry.getKey(), entry.getValue()))
                .toArray(TagResolver[]::new);
        return miniMessage.deserialize(message, tagResolvers);
    }

}
