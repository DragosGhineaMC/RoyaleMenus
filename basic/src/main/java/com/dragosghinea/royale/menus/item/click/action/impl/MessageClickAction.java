package com.dragosghinea.royale.menus.item.click.action.impl;

import com.dragosghinea.royale.internal.utils.messages.MessageSender;
import com.dragosghinea.royale.menus.item.click.action.ClickAction;
import lombok.Getter;

@Getter
public class MessageClickAction extends ClickAction {

    private final String argument;
    private final MessageSender messageSender;

    public MessageClickAction(MessageSender messageSender, String argument) {
        super((menu, event) -> messageSender.sendMessage(event.getWhoClicked(), argument));

        this.messageSender = messageSender;
        this.argument = argument;
    }
}
