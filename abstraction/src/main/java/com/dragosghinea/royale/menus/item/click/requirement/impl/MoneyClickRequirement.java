package com.dragosghinea.royale.menus.item.click.requirement.impl;

import com.dragosghinea.royale.currencies.Currency;
import com.dragosghinea.royale.menus.RoyaleMenu;
import com.dragosghinea.royale.menus.item.click.requirement.ClickRequirement;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;

import java.math.BigDecimal;
import java.util.function.BiPredicate;

public class MoneyClickRequirement extends ClickRequirement {

    private final String argument;
    private final BigDecimal amountToHave;

    private final Currency currency;

    public MoneyClickRequirement(Currency currency, String argument, BiPredicate<RoyaleMenu, InventoryClickEvent> clickRequirement) {
        this(currency, argument, clickRequirement, null);
    }

    public MoneyClickRequirement(Currency currency, String argument, BiPredicate<RoyaleMenu, InventoryClickEvent> clickRequirement, ClickType clickType) {
        super(clickRequirement, clickType);
        this.argument = argument;
        this.amountToHave = BigDecimal.valueOf(Double.parseDouble(argument));

        this.currency = currency;
    }

    @Override
    public boolean hasRequirement(RoyaleMenu menu, InventoryClickEvent event) {
        if (currency.getAmount(event.getWhoClicked().getUniqueId().toString()).compareTo(amountToHave) < 0) {
            return false;
        }

        return super.hasRequirement(menu, event);
    }
}
