package com.dragosghinea.royale.menus.item.click.action;

import com.dragosghinea.royale.menus.RoyaleMenu;
import com.dragosghinea.royale.menus.item.click.requirement.ClickRequirement;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;

import java.util.List;

@Getter
public class ClickActionGroup {

    protected ClickType clickType;

    @Setter
    protected ClickRequirement clickRequirement;

    protected List<ClickAction> actions;

    public ClickActionGroup(List<ClickAction> clickActions) {
        this(null, clickActions);
    }

    public ClickActionGroup(ClickType clickType, List<ClickAction> clickActions) {
        this.clickType = clickType;
        actions = clickActions;
    }

    public boolean click(RoyaleMenu menu, InventoryClickEvent event) {
        if (clickType != null && event.getClick() != clickType) {
            return false;
        }

        if (clickRequirement != null && !clickRequirement.hasRequirement(menu, event)) {
            clickRequirement.getOnDenyActions().forEach(action -> action.click(menu, event));
            return false;
        }

        for (ClickAction action : actions) {
            action.click(menu, event);
        }

        return true;
    }

}
