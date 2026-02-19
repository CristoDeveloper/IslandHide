package net.solryn.islandhide;

import net.solryn.islandhide.managers.BrandManager;
import net.solryn.islandhide.managers.CommandManager;
import net.solryn.islandhide.managers.ConfigManager;
import net.solryn.islandhide.managers.IslandSecurityManager;
import net.solryn.islandhide.managers.Manager;
import net.solryn.islandhide.managers.PluginsManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public class IslandHide extends JavaPlugin {

    private final List<Manager> managers = new ArrayList<>();
    
    private ConfigManager configManager;
    private CommandManager commandManager;
    private PluginsManager pluginsManager;
    private BrandManager brandManager;
    private IslandSecurityManager securityManager;

    @Override
    public void onEnable() {
        net.solryn.islandhide.api.IslandHideAPI.setPlugin(this);

        this.configManager = new ConfigManager(this);
        this.commandManager = new CommandManager(this);
        this.pluginsManager = new PluginsManager(this);
        this.brandManager = new BrandManager(this);
        this.securityManager = new IslandSecurityManager(this);

        managers.add(configManager);
        managers.add(commandManager);
        managers.add(pluginsManager);
        managers.add(brandManager);
        managers.add(securityManager);

        for (Manager manager : managers) {
            manager.onEnable();
        }
        
        getLogger().info("IslandHide enabled successfully! v" + getPluginMeta().getVersion());
    }

    @Override
    public void onDisable() {
        for (Manager manager : managers) {
            manager.onDisable();
        }
        getLogger().info("IslandHide disabled.");
    }
    
    public ConfigManager getConfigManager() { return configManager; }
    public CommandManager getCommandManager() { return commandManager; }
    public PluginsManager getPluginsManager() { return pluginsManager; }
    public BrandManager getBrandManager() { return brandManager; }
    public IslandSecurityManager getSecurityManager() { return securityManager; }
}
