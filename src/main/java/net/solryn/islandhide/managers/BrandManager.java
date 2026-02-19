package net.solryn.islandhide.managers;

import net.solryn.islandhide.IslandHide;
import net.solryn.islandhide.listeners.BrandListener;
import org.bukkit.event.HandlerList;

public class BrandManager extends Manager {

    private BrandListener listener;

    public BrandManager(IslandHide plugin) {
        super(plugin);
    }

    @Override
    public void onEnable() {
        plugin.getServer().getMessenger().registerOutgoingPluginChannel(plugin, "minecraft:brand");
        
        if (listener == null) {
            listener = new BrandListener(plugin);
        }
        plugin.getServer().getPluginManager().registerEvents(listener, plugin);
        plugin.getLogger().info("BrandManager enabled.");
    }

    @Override
    public void onDisable() {
        if (listener != null) {
            HandlerList.unregisterAll(listener);
        }
        plugin.getServer().getMessenger().unregisterOutgoingPluginChannel(plugin, "minecraft:brand");
    }

    @Override
    public void reload() {
    }
}
