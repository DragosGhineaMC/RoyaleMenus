package com.dragosghinea.royale.menus.item.click.action.impl;

import com.dragosghinea.royale.internal.utils.messages.StringMessageProcessorChain;
import com.dragosghinea.royale.menus.item.click.action.ClickAction;
import lombok.Getter;
import org.bukkit.Bukkit;

@Getter
public class CommandClickAction extends ClickAction {

    private final String argument;

    public CommandClickAction(StringMessageProcessorChain messageProcessorChain, String argument) {
        super((menu, event) -> Bukkit.dispatchCommand(event.getWhoClicked(), messageProcessorChain.processMessage(event.getWhoClicked(), argument)));

        this.argument = argument;
    }
}
