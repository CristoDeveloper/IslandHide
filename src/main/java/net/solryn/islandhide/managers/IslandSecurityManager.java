package net.solryn.islandhide.managers;

import net.solryn.islandhide.IslandHide;
import net.solryn.islandhide.listeners.SecurityListener;
import org.bukkit.event.HandlerList;

public class IslandSecurityManager extends Manager {

    private SecurityListener listener;

    public IslandSecurityManager(IslandHide plugin) {
        super(plugin);
    }

    @Override
    public void onEnable() {
        if (listener == null) {
            listener = new SecurityListener(plugin);
        }
        plugin.getServer().getPluginManager().registerEvents(listener, plugin);
        plugin.getLogger().info("SecurityManager enabled.");
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
