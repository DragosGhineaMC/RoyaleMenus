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

@Getter
public class ClickRequirement {

    protected final BiPredicate<RoyaleMenu, InventoryClickEvent> clickRequirement;

    @Setter
    protected List<ClickAction> onDenyActions = Collections.emptyList();

    public ClickRequirement(BiPredicate<RoyaleMenu, InventoryClickEvent> clickRequirement) {
        this.clickRequirement = clickRequirement;
    }

    public boolean hasRequirement(RoyaleMenu menu, InventoryClickEvent event) {
        return clickRequirement.test(menu, event);
    }

}
