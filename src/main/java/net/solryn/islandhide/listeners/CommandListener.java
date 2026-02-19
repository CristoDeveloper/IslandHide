package net.solryn.islandhide.listeners;

import net.solryn.islandhide.IslandHide;
import net.solryn.islandhide.managers.ConfigManager;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerCommandSendEvent;

import java.util.Collection;

public class CommandListener implements Listener {

    private final IslandHide plugin;

    public CommandListener(IslandHide plugin) {
        this.plugin = plugin;
    }

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onCommand(PlayerCommandPreprocessEvent event) {
        Player player = event.getPlayer();
        if (player.hasPermission("islandhide.bypass.commands")) return;

        ConfigManager config = plugin.getConfigManager();
        if (!config.isCommandHidingEnabled()) return;

        String message = event.getMessage();

        if (plugin.getCommandManager().isCommandBlocked(message)) {
            event.setCancelled(true);
            String blockMsg = config.getConfig().getString("hiding.commands.block-message", "");
            if (!blockMsg.isEmpty()) {
                player.sendMessage(LegacyComponentSerializer.legacyAmpersand().deserialize(blockMsg));
            }
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onCommandSend(PlayerCommandSendEvent event) {
        if (event.getPlayer().hasPermission("islandhide.bypass.commands")) return;

        ConfigManager config = plugin.getConfigManager();
        if (!config.isCommandHidingEnabled()) return;

        Collection<String> commands = event.getCommands();
        commands.removeIf(cmd -> plugin.getCommandManager().isCommandBlocked("/" + cmd));
    }
}
