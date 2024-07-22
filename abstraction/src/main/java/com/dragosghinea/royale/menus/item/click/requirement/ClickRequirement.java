package com.dragosghinea.royale.menus.item.click.requirement;

import com.dragosghinea.royale.menus.RoyaleMenu;
import com.dragosghinea.royale.menus.events.RoyaleMenuClickEvent;
import com.dragosghinea.royale.menus.item.click.action.ClickAction;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Bukkit;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;

import java.util.Collections;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.BiPredicate;

public class ClickRequirement {

    @Getter
    protected final BiPredicate<RoyaleMenu, InventoryClickEvent> clickRequirement;

    protected final ClickType clickType;

    @Getter
    @Setter
    protected List<ClickAction> onDenyActions = Collections.emptyList();

    public ClickRequirement(BiPredicate<RoyaleMenu, InventoryClickEvent> clickRequirement) {
        this(clickRequirement, null);
    }

    public ClickRequirement(BiPredicate<RoyaleMenu, InventoryClickEvent> clickRequirement, ClickType clickType) {
        this.clickRequirement = clickRequirement;
        this.clickType = clickType;
    }

    public boolean hasRequirement(RoyaleMenu menu, InventoryClickEvent event) {
        if (this.clickType != null && !this.clickType.equals(event.getClick())) {
            return true;
        }

        return clickRequirement.test(menu, event);
    }

}
