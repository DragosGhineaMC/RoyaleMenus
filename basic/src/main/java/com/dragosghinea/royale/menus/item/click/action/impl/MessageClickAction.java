package com.dragosghinea.royale.menus.item.click.action.impl;

import com.dragosghinea.royale.internal.utils.messages.MessageSender;
import com.dragosghinea.royale.internal.utils.messages.StringMessageProcessorChain;
import com.dragosghinea.royale.menus.item.click.action.ClickAction;
import lombok.Getter;

@Getter
public class MessageClickAction extends ClickAction {

    private final String argument;
    private final MessageSender messageSender;
    private final StringMessageProcessorChain messageProcessorChain;

    public MessageClickAction(MessageSender messageSender, String argument) {
        super((menu, event) -> messageSender.sendMessage(event.getWhoClicked(), argument));

        this.messageSender = messageSender;
        this.messageProcessorChain = null;
        this.argument = argument;
    }

    public MessageClickAction(MessageSender messageSender, StringMessageProcessorChain messageProcessorChain, String argument) {
        super((menu, event) -> messageSender.sendMessage(event.getWhoClicked(), messageProcessorChain.processMessage(event.getWhoClicked(), argument)));

        this.messageSender = messageSender;
        this.messageProcessorChain = messageProcessorChain;
        this.argument = argument;
    }
}
