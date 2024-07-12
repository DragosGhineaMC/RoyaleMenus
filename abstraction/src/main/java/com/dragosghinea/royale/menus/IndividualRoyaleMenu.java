package com.dragosghinea.royale.menus;

import org.bukkit.entity.Player;

import java.util.Map;

public abstract class IndividualRoyaleMenu extends RoyaleMenu {
    protected final Map<Integer, RoyaleMenuItem> items;
    protected final RoyaleMenuItem backgroundItem;

    public IndividualRoyaleMenu(Map<Integer, RoyaleMenuItem> items) {
        this(items, RoyaleMenuItem.EMPTY);
    }

    public IndividualRoyaleMenu(Map<Integer, RoyaleMenuItem> items, RoyaleMenuItem backgroundItem) {
        this.items = items;
        this.backgroundItem = backgroundItem;
    }

    @Override
    public boolean openMenu(Player player) {
        if (player != getInventoryHolder()) {
            return false;
        }

        player.openInventory(getInventory());
        return true;
    }

    @Override
    public RoyaleMenuItem getBackgroundItem() {
        return backgroundItem;
    }

    public abstract Player getInventoryHolder();

}
