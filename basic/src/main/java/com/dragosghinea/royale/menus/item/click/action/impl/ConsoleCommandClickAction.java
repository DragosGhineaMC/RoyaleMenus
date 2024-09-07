package com.dragosghinea.royale.menus.item.click.action.impl;

import com.dragosghinea.royale.internal.utils.messages.StringMessageProcessorChain;
import com.dragosghinea.royale.menus.item.click.action.ClickAction;
import lombok.Getter;
import org.bukkit.Bukkit;

@Getter
public class ConsoleCommandClickAction extends ClickAction {

    private final String argument;
    private final StringMessageProcessorChain messageProcessorChain;

    public ConsoleCommandClickAction(StringMessageProcessorChain messageProcessorChain, String argument) {
        super((menu, event) -> Bukkit.dispatchCommand(Bukkit.getConsoleSender(), messageProcessorChain.processMessage(Bukkit.getConsoleSender(), argument)));

        this.argument = argument;
        this.messageProcessorChain = messageProcessorChain;
    }
}
