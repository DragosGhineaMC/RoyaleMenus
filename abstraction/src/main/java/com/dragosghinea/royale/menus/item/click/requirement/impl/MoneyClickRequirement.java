package com.dragosghinea.royale.menus.item.click.requirement.impl;

import com.dragosghinea.royale.currencies.Currency;
import com.dragosghinea.royale.menus.RoyaleMenu;
import com.dragosghinea.royale.menus.item.click.requirement.ClickRequirement;
import lombok.Getter;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;

import java.math.BigDecimal;

@Getter
public class MoneyClickRequirement extends ClickRequirement {

    private final String argument;
    private final BigDecimal amountToHave;

    private final Currency currency;

    public MoneyClickRequirement(Currency currency, String argument) {
        this(currency, argument, null);
    }

    public MoneyClickRequirement(Currency currency, String argument, ClickType clickType) {
        super(
                (menu, event) -> currency.getAmount(event.getWhoClicked().getUniqueId().toString()).compareTo(BigDecimal.valueOf(Double.parseDouble(argument))) >= 0,
                clickType
        );
        this.argument = argument;
        this.amountToHave = BigDecimal.valueOf(Double.parseDouble(argument));

        this.currency = currency;
    }
}
