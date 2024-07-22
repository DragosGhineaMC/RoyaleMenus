package com.dragosghinea.royale.menus.item.click.action.impl;

import com.dragosghinea.royale.internal.utils.messages.MessageSender;
import com.dragosghinea.royale.menus.item.click.action.ClickAction;
import lombok.Getter;
import org.bukkit.event.inventory.ClickType;

@Getter
public class MessageClickAction extends ClickAction {

    private final String argument;
    private final MessageSender messageSender;

    public MessageClickAction(MessageSender messageSender, String argument) {
        this(messageSender, argument, null);
    }

    public MessageClickAction(MessageSender messageSender, String argument, ClickType clickType) {
        super((menu, event) -> {
            messageSender.sendMessage(event.getWhoClicked(), argument);
        }, clickType);

        this.messageSender = messageSender;
        this.argument = argument;
    }
}
