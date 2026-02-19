package net.solryn.islandhide.listeners;

import net.solryn.islandhide.IslandHide;
import net.solryn.islandhide.managers.ConfigManager;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRegisterChannelEvent;

import java.util.List;

public class SecurityListener implements Listener {

    private final IslandHide plugin;

    public SecurityListener(IslandHide plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onChannelRegister(PlayerRegisterChannelEvent event) {
        ConfigManager config = plugin.getConfigManager();
        if (!config.isSecurityEnabled()) return;

        List<String> blocked = config.getConfig().getStringList("security.blocked-channels");
        String channel = event.getChannel();

        if (blocked.contains(channel)) {
            plugin.getLogger().warning("Player " + event.getPlayer().getName() + " registered blocked channel: " + channel);
            
            plugin.getServer().getScheduler().runTask(plugin, () -> {
                if (event.getPlayer().isOnline()) {
                    event.getPlayer().kick(Component.text("Illegal client modifications detected.", NamedTextColor.RED));
                }
            });
        }
    }
}
