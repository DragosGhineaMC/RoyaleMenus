package com.dragosghinea.royale.menus;

import com.dragosghinea.royale.menus.item.RoyaleMenuItem;
import org.bukkit.entity.Player;

import java.util.Map;

public abstract class GlobalRoyaleMenu extends RoyaleMenu{

    protected final Map<Integer, RoyaleMenuItem> items;
    protected final RoyaleMenuItem backgroundItem;

    public GlobalRoyaleMenu(Map<Integer, RoyaleMenuItem> items) {
        this(items, RoyaleMenuItem.EMPTY);
    }

    public GlobalRoyaleMenu(Map<Integer, RoyaleMenuItem> items, RoyaleMenuItem backgroundItem) {
        this.items = items;
        this.backgroundItem = backgroundItem;
    }

    @Override
    public boolean openMenu(Player player) {
        player.openInventory(getInventory());
        return true;
    }

    @Override
    public RoyaleMenuItem getBackgroundItem() {
        return backgroundItem;
    }

}
