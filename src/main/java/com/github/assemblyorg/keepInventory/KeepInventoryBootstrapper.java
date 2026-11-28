package com.github.assemblyorg.keepInventory;

import io.papermc.paper.plugin.bootstrap.BootstrapContext;
import io.papermc.paper.plugin.bootstrap.PluginBootstrap;
import io.papermc.paper.plugin.bootstrap.PluginProviderContext;
import org.bukkit.plugin.java.JavaPlugin;
import org.jspecify.annotations.NonNull;

public class KeepInventoryBootstrapper implements PluginBootstrap {

    @Override
    public void bootstrap(BootstrapContext context) {

    }

    @Override
    public @NonNull JavaPlugin createPlugin(PluginProviderContext context) {
        return KeepInventory.instance();
    }

}
