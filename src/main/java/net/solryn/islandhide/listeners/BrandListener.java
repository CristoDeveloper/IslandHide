package net.solryn.islandhide.listeners;

import com.destroystokyo.paper.event.server.PaperServerListPingEvent;
import net.solryn.islandhide.IslandHide;
import net.solryn.islandhide.managers.ConfigManager;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class BrandListener implements Listener {

    private final IslandHide plugin;

    public BrandListener(IslandHide plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPing(PaperServerListPingEvent event) {
        ConfigManager config = plugin.getConfigManager();
        if (!config.isBrandHidingEnabled()) return;

        if (config.getConfig().getBoolean("hiding.server-list.enabled")) {
            String motd = config.getConfig().getString("hiding.server-list.motd", "");
            if (!motd.isEmpty()) {
                event.motd(LegacyComponentSerializer.legacyAmpersand().deserialize(motd));
            }
            
            if (config.getConfig().getBoolean("hiding.server-list.hide-players")) {
                event.setNumPlayers(0);
                event.setMaxPlayers(0);
                event.getListedPlayers().clear();
            } else {
                int fakeMax = config.getConfig().getInt("hiding.server-list.fake-max-players", -1);
                if (fakeMax != -1) {
                    event.setMaxPlayers(fakeMax);
                }
            }
            
            String version = config.getConfig().getString("hiding.brand.value", "Paper 1.21");
            event.setVersion(version);
            event.setProtocolVersion(event.getClient().getProtocolVersion());
        }
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        ConfigManager config = plugin.getConfigManager();
        if (!config.isBrandHidingEnabled()) return;

        String brand = config.getConfig().getString("hiding.brand.value", "Paper");
        
        plugin.getServer().getScheduler().runTaskLater(plugin, () -> {
            org.bukkit.entity.Player player = event.getPlayer();
            if (!player.isOnline()) return;
            
            try {
                ByteArrayOutputStream out = new ByteArrayOutputStream();
                byte[] brandBytes = brand.getBytes(StandardCharsets.UTF_8);
                writeVarInt(out, brandBytes.length);
                out.write(brandBytes);
                
                player.sendPluginMessage(plugin, "minecraft:brand", out.toByteArray());
            } catch (Exception e) {
                plugin.getLogger().warning("Failed to send brand plugin message: " + e.getMessage());
            }
        }, 5L); 
    }
    
    private void writeVarInt(OutputStream out, int value) throws IOException {
        while ((value & -128) != 0) {
            out.write(value & 127 | 128);
            value >>>= 7;
        }
        out.write(value);
    }
}
