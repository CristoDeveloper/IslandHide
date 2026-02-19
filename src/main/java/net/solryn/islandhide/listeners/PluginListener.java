package net.solryn.islandhide.listeners;

import com.destroystokyo.paper.event.server.AsyncTabCompleteEvent;
import net.solryn.islandhide.IslandHide;
import net.solryn.islandhide.managers.ConfigManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

public class PluginListener implements Listener {

    private final IslandHide plugin;

    public PluginListener(IslandHide plugin) {
        this.plugin = plugin;
    }

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onTabComplete(AsyncTabCompleteEvent event) {
        if (!(event.getSender() instanceof Player player)) return;
        if (player.hasPermission("islandhide.bypass.plugins")) return;

        ConfigManager config = plugin.getConfigManager();
        if (!config.isPluginHidingEnabled()) return;
        if (!config.getConfig().getBoolean("hiding.plugins.block-tab-complete", true)) return;

        String buffer = event.getBuffer();
        
        String commandBase = buffer.split(" ")[0].toLowerCase();
        if (isPluginQueryCommand(commandBase)) {
            event.setCancelled(true);
            return;
        }
    }

    private boolean isPluginQueryCommand(String cmd) {
        if (!cmd.startsWith("/")) cmd = "/" + cmd;
        
        return cmd.equals("/ver") || cmd.equals("/version") || 
               cmd.equals("/about") || cmd.equals("/pl") || 
               cmd.equals("/plugins") || cmd.equals("/bukkit:ver") ||
               cmd.equals("/bukkit:version") || cmd.equals("/bukkit:about") ||
               cmd.equals("/bukkit:pl") || cmd.equals("/bukkit:plugins") ||
               cmd.equals("/icanhasbukkit");
    }
}
