package com.dragosghinea.royale.menus.dupe;

import com.dragosghinea.royale.menus.marker.MarkerManager;
import com.dragosghinea.royale.menus.marker.RoyaleItemMarker;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

// https://github.com/HelpChat/DeluxeMenus/blob/main/src/main/java/com/extendedclip/deluxemenus/dupe/DupeFixer.java
public class DupePrevent implements Listener {
    private final Plugin plugin;
    private final RoyaleItemMarker marker;
    private final boolean warningMessages;

    public DupePrevent(Plugin plugin, boolean warningMessages) {
        this.plugin = plugin;
        marker = MarkerManager.getAvailableMarker(plugin);
        this.warningMessages = warningMessages;
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    public void unregister() {
        HandlerList.unregisterAll(this);
    }

    @EventHandler
    private void onPickup(final EntityPickupItemEvent event) {
        if (!marker.isMarked(event.getItem().getItemStack())) {
            return;
        }

        if (warningMessages)
            plugin.getLogger().info("RoyaleMenus item was picked up by an entity (" + event.getEntity().getName() + "). Removing it.");

        event.getItem().remove();
    }

    @EventHandler
    private void onDrop(final PlayerDropItemEvent event) {
        if (!marker.isMarked(event.getItemDrop().getItemStack())) {
            return;
        }

        if (warningMessages)
            plugin.getLogger().info("RoyaleMenus item was dropped by a player (" + event.getPlayer().getName() + "). Removing it.");

        event.getItemDrop().remove();
    }

    @EventHandler
    private void onLogin(final PlayerLoginEvent event) {
        plugin.getServer().getScheduler().runTaskLater(
                plugin,
                () -> {
                    for (final ItemStack itemStack : event.getPlayer().getInventory().getContents()) {
                        if (itemStack == null) continue;
                        if (!marker.isMarked(itemStack)) continue;

                        if (warningMessages)
                            plugin.getLogger().info("Player " + event.getPlayer().getName() + "logged in with a RoyaleMenus item in their inventory. Removing it.");

                        event.getPlayer().getInventory().remove(itemStack);
                    }
                },
                10L
        );
    }
}
