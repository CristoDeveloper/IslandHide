package net.solryn.islandhide.api;

import net.solryn.islandhide.IslandHide;

/**
 * Public API for IslandHide plugin.
 * This class provides access to plugin functionalities for external developers.
 */
public class IslandHideAPI {

    private static IslandHide plugin;

    /**
     * Internal use only. Sets the plugin instance.
     * @param instance The plugin instance.
     */
    public static void setPlugin(IslandHide instance) {
        plugin = instance;
    }

    /**
     * Checks if a command is currently hidden by the plugin configuration.
     * @param command The command to check (e.g., "/plugins").
     * @return true if hidden, false otherwise.
     */
    public static boolean isCommandHidden(String command) {
        if (plugin == null) return false;
        return plugin.getCommandManager().isCommandBlocked(command);
    }
    
    /**
     * Reloads the plugin configuration.
     */
    public static void reload() {
        if (plugin != null) {
            plugin.getConfigManager().reload();
        }
    }
}
