package net.solryn.islandhide.managers;

import net.solryn.islandhide.IslandHide;
import net.solryn.islandhide.listeners.CommandListener;
import org.bukkit.event.HandlerList;

import java.util.List;
import java.util.regex.Pattern;

public class CommandManager extends Manager {

    private CommandListener listener;

    public CommandManager(IslandHide plugin) {
        super(plugin);
    }

    @Override
    public void onEnable() {
        if (listener == null) {
            listener = new CommandListener(plugin);
        }
        plugin.getServer().getPluginManager().registerEvents(listener, plugin);
        plugin.getLogger().info("CommandManager enabled.");
    }

    @Override
    public void onDisable() {
        if (listener != null) {
            HandlerList.unregisterAll(listener);
        }
    }

    @Override
    public void reload() {
        // No cache to reload, listener uses config directly
    }
    
    public boolean isCommandBlocked(String command) {
        if (!plugin.getConfigManager().isCommandHidingEnabled()) return false;

        List<String> list = plugin.getConfigManager().getConfig().getStringList("hiding.commands.list");
        String mode = plugin.getConfigManager().getConfig().getString("hiding.commands.mode", "BLACKLIST").toUpperCase();

        boolean match = false;
        for (String pattern : list) {
            try {
                if (Pattern.compile(pattern, Pattern.CASE_INSENSITIVE).matcher(command).find()) {
                    match = true;
                    break;
                }
            } catch (Exception e) {
                if (command.toLowerCase().startsWith(pattern.toLowerCase())) {
                    match = true;
                    break;
                }
            }
        }

        if (mode.equals("WHITELIST")) {
            return !match;
        } else {
            return match;
        }
    }
}
