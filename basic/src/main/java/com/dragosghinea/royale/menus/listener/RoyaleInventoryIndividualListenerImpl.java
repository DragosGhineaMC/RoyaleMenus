package com.dragosghinea.royale.menus.listener;

import com.dragosghinea.royale.internal.utils.messages.MessageSender;
import com.dragosghinea.royale.internal.utils.messages.StringMessageProcessorChain;
import com.dragosghinea.royale.internal.utils.messages.impl.StringMessageProcessorChainImpl;
import com.dragosghinea.royale.internal.utils.messages.impl.processor.ColorMessageProcessorImpl;
import com.dragosghinea.royale.internal.utils.messages.impl.processor.PlaceholderAPIMessageProcessorImpl;
import com.dragosghinea.royale.internal.utils.messages.impl.sender.PlainMessageSenderImpl;
import com.dragosghinea.royale.menus.IndividualRoyaleMenu;
import com.dragosghinea.royale.menus.RoyaleInventoryListener;
import com.dragosghinea.royale.menus.item.RoyaleMenuItem;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.plugin.Plugin;

public class RoyaleInventoryIndividualListenerImpl implements RoyaleInventoryListener {
    private final MessageSender messageSender = new PlainMessageSenderImpl();
    private final StringMessageProcessorChain messageProcessorChain = new StringMessageProcessorChainImpl();

    {
        messageProcessorChain.addProcessor(new ColorMessageProcessorImpl());
        if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
            messageProcessorChain.addProcessor(new PlaceholderAPIMessageProcessorImpl());
        }
    }

    private final IndividualRoyaleMenu individualRoyaleMenu;

    private boolean active = true;

    public RoyaleInventoryIndividualListenerImpl(Plugin plugin, IndividualRoyaleMenu individualRoyaleMenu) {
        this.individualRoyaleMenu = individualRoyaleMenu;
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @Override
    public Inventory getInventory() {
        return individualRoyaleMenu.getInventory();
    }

    @Override
    public synchronized void onInventoryClick(InventoryClickEvent event) {
        if (individualRoyaleMenu.isClickingLocked()) {
            if (individualRoyaleMenu.getClickingLockMessage() != null)
                messageSender.sendMessage(event.getWhoClicked(), messageProcessorChain.processMessage(event.getWhoClicked(), individualRoyaleMenu.getClickingLockMessage()));
            return;
        }

        RoyaleMenuItem item = individualRoyaleMenu.getItem(event.getSlot());
        if (item == null)
            return;

        item.getClickActions().forEach(action -> action.click(individualRoyaleMenu, event));
    }

    @Override
    public synchronized void onInventoryClose(InventoryCloseEvent event) {
        individualRoyaleMenu.cleanup();
    }

    @EventHandler
    public void onInventoryClickHandler(InventoryClickEvent event) {
        if (event.isCancelled()) {
            return;
        }

        if (!getInventory().equals(event.getInventory())) {
            return;
        }

        event.setCancelled(true);

        if (event.getSlot() < 0 || event.getCurrentItem() == null || event.getCurrentItem().getType().equals(Material.AIR)) {
            return;
        }

        onInventoryClick(event);
    }

    @EventHandler
    public void onInventoryCloseHandler(InventoryCloseEvent event) {
        if (!event.getPlayer().equals(individualRoyaleMenu.getInventoryHolder())) {
            return;
        }

        if (!getInventory().equals(event.getInventory())) {
            return;
        }

        onInventoryClose(event);
        close();
    }

    @Override
    public boolean isActive() {
        return active;
    }

    @Override
    public void close() {
        if (!isActive()) {
            return;
        }

        HandlerList.unregisterAll(this);
        active = false;
    }
}
