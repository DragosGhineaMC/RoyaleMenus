package com.dragosghinea.royale.menus.item.click.action.impl;

import com.dragosghinea.royale.internal.utils.messages.MessageSender;
import com.dragosghinea.royale.internal.utils.messages.StringMessageProcessorChain;
import com.dragosghinea.royale.internal.utils.messages.impl.StringMessageProcessorChainImpl;
import com.dragosghinea.royale.internal.utils.messages.impl.processor.ColorMessageProcessorImpl;
import com.dragosghinea.royale.internal.utils.messages.impl.processor.PlaceholderAPIMessageProcessorImpl;
import com.dragosghinea.royale.internal.utils.messages.impl.sender.PlainMessageSenderImpl;
import com.dragosghinea.royale.menus.item.click.action.ClickAction;
import com.dragosghinea.royale.menus.item.click.action.ClickActionCfg;
import com.dragosghinea.royale.menus.item.click.action.ClickActionMapping;
import com.dragosghinea.royale.menus.item.click.action.ClickActionTypes;
import lombok.AllArgsConstructor;
import org.bukkit.Bukkit;
import org.bukkit.event.inventory.ClickType;

// necessary mapping to avoid cyclic dependencies inside the click requirements
@AllArgsConstructor
public class ClickActionNoRequirementsMappingImpl implements ClickActionMapping {
    private final StringMessageProcessorChain messageProcessorChain;
    private final MessageSender messageSender;

    public ClickActionNoRequirementsMappingImpl() {
        this(
                new StringMessageProcessorChainImpl(),
                new PlainMessageSenderImpl()
        );
        messageProcessorChain.addProcessor(new ColorMessageProcessorImpl());
        if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
            messageProcessorChain.addProcessor(new PlaceholderAPIMessageProcessorImpl());
        }
    }

    @Override
    public ClickAction mapFromConfig(ClickActionCfg clickActionCfg) {
        ClickAction clickAction = null;

        ClickType clickType = clickActionCfg.getClickType() == null ? null : ClickType.valueOf(clickActionCfg.getClickType().toUpperCase());

        ClickActionTypes clickActionType = clickActionCfg.getActionType();
        switch (clickActionType) {
            case MESSAGE:
                clickAction = new MessageClickAction(messageSender, clickActionCfg.getArgument(), clickType);
                break;
            case MENU_CLOSE:
                clickAction = new CloseClickAction(clickType);
                break;
            case COMMAND:
                clickAction = new CommandClickAction(messageProcessorChain, clickActionCfg.getArgument(), clickType);
                break;
            case CONSOLE_COMMAND:
                clickAction = new ConsoleCommandClickAction(messageProcessorChain, clickActionCfg.getArgument(), clickType);
                break;
            case UNKNOWN:
                throw new IllegalArgumentException("Unknown click action type: " + clickActionCfg.getActionType());
        }

        return clickAction;
    }

}
