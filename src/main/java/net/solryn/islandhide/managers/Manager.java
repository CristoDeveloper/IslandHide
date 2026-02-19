package net.solryn.islandhide.managers;

import net.solryn.islandhide.IslandHide;

public abstract class Manager {
    protected final IslandHide plugin;

    public Manager(IslandHide plugin) {
        this.plugin = plugin;
    }

    public abstract void onEnable();
    public abstract void onDisable();
    public abstract void reload();
}
