package net.solryn.islandhide.managers;

import net.solryn.islandhide.IslandHide;
import org.bukkit.configuration.file.FileConfiguration;

public class ConfigManager extends Manager {

    public ConfigManager(IslandHide plugin) {
        super(plugin);
    }

    @Override
    public void onEnable() {
        plugin.saveDefaultConfig();
        plugin.reloadConfig();
    }

    @Override
    public void onDisable() {
    }

    @Override
    public void reload() {
        plugin.reloadConfig();
    }
    
    public FileConfiguration getConfig() {
        return plugin.getConfig();
    }
    
    public boolean isCommandHidingEnabled() {
        return getConfig().getBoolean("hiding.commands.enabled", true);
    }
    
    public boolean isPluginHidingEnabled() {
        return getConfig().getBoolean("hiding.plugins.enabled", true);
    }
    
    public boolean isBrandHidingEnabled() {
        return getConfig().getBoolean("hiding.brand.enabled", true);
    }
    
    public boolean isSecurityEnabled() {
        return getConfig().getBoolean("security.anti-mod-detection", true);
    }
}
