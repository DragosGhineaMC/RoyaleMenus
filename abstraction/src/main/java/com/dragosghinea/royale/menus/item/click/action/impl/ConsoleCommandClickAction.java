package com.dragosghinea.royale.menus.item.click.action.impl;

import com.dragosghinea.royale.internal.utils.messages.MessageProcessor;
import com.dragosghinea.royale.internal.utils.messages.StringMessageProcessorChain;
import com.dragosghinea.royale.menus.item.click.action.ClickAction;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.event.inventory.ClickType;

@Getter
public class ConsoleCommandClickAction extends ClickAction {

    private final String argument;
    private final StringMessageProcessorChain messageProcessorChain;

    public ConsoleCommandClickAction(StringMessageProcessorChain messageProcessorChain, String argument) {
        this(messageProcessorChain, argument, null);
    }

    public ConsoleCommandClickAction(StringMessageProcessorChain messageProcessorChain, String argument, ClickType clickType) {
        super((menu, event) -> {
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), messageProcessorChain.processMessage(Bukkit.getConsoleSender(), argument));
        }, clickType);

        this.argument = argument;
        this.messageProcessorChain = messageProcessorChain;
    }
}
