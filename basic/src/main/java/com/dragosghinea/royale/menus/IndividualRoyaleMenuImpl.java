package com.dragosghinea.royale.menus;

import com.dragosghinea.royale.menus.item.RoyaleMenuItem;
import com.dragosghinea.royale.menus.listener.RoyaleInventoryIndividualListenerImpl;
import com.dragosghinea.royale.menus.locking.RoyaleMenuLocking;
import com.dragosghinea.royale.menus.locking.RoyaleMenuLockingImpl;
import lombok.Setter;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.plugin.Plugin;

import java.util.Map;

public class IndividualRoyaleMenuImpl extends IndividualRoyaleMenu {
    private final Player player;
    private RoyaleInventoryListener listener;
    private Inventory inventory;

    private final RoyaleMenuLocking locking = new RoyaleMenuLockingImpl();
    @Setter
    private String clickingLockMessage;

    private void createInventory(String title, RoyaleInventoryType inventoryType, Plugin plugin) {
        if (inventory != null)
            return;

        if (inventoryType.getInventoryType().equals(InventoryType.CHEST))
            inventory = Bukkit.createInventory(player, inventoryType.getSize(), title);
        else
            inventory = Bukkit.createInventory(player, inventoryType.getInventoryType(), title);

        triggerBackground();
        listener = new RoyaleInventoryIndividualListenerImpl(plugin, this);
    }

    public IndividualRoyaleMenuImpl(Plugin plugin, Player player, String title, RoyaleInventoryType inventoryType, Map<Integer, RoyaleMenuItem> items) {
        this(plugin, player, title, inventoryType, items, RoyaleMenuItem.EMPTY);
    }

    public IndividualRoyaleMenuImpl(Plugin plugin, Player player, String title, RoyaleInventoryType inventoryType, Map<Integer, RoyaleMenuItem> items, RoyaleMenuItem backgroundItem) {
        super(items, backgroundItem);
        this.player = player;
        createInventory(title, inventoryType, plugin);
    }

    @Override
    public Player getInventoryHolder() {
        return player;
    }

    @Override
    public Inventory getInventory() {
        return inventory;
    }

    @Override
    public void lockClicking() {
        locking.lockClicking();
    }

    @Override
    public void unlockClicking() {
        locking.unlockClicking();
    }

    @Override
    public boolean isClickingLocked() {
        return locking.isLocked();
    }

    @Override
    public String getClickingLockMessage() {
        return clickingLockMessage;
    }

    @Override
    public void triggerContentUpdate() {
        for (Map.Entry<Integer, RoyaleMenuItem> entry : getAllItems()) {
            inventory.setItem(entry.getKey(), entry.getValue().getItemMarked(player));
        }
    }

    @Override
    public void setItem(int slot, RoyaleMenuItem item) {
        items.put(slot, item);
    }

    @Override
    public RoyaleMenuItem getItem(int slot) {
        return items.getOrDefault(slot, null);
    }

    @Override
    public Iterable<Map.Entry<Integer, RoyaleMenuItem>> getAllItems() {
        return items.entrySet();
    }

    @Override
    public RoyaleInventoryListener getListener() {
        return listener;
    }

    @Override
    public void cleanup() {
        items.clear();
        inventory.clear();
    }
}
