package net.solryn.islandhide.managers;

import net.solryn.islandhide.IslandHide;
import net.solryn.islandhide.listeners.PluginListener;
import org.bukkit.event.HandlerList;

public class PluginsManager extends Manager {

    private PluginListener listener;

    public PluginsManager(IslandHide plugin) {
        super(plugin);
    }

    @Override
    public void onEnable() {
        if (listener == null) {
            listener = new PluginListener(plugin);
        }
        plugin.getServer().getPluginManager().registerEvents(listener, plugin);
        plugin.getLogger().info("PluginsManager enabled.");
    }

    @Override
    public void onDisable() {
        if (listener != null) {
            HandlerList.unregisterAll(listener);
        }
    }

    @Override
    public void reload() {
    }
}
