package com.dragosghinea.royale.menus.item.click.action.impl;

import com.dragosghinea.royale.internal.utils.messages.MessageSender;
import com.dragosghinea.royale.internal.utils.messages.StringMessageProcessorChain;
import com.dragosghinea.royale.internal.utils.messages.impl.StringMessageProcessorChainImpl;
import com.dragosghinea.royale.internal.utils.messages.impl.processor.ColorMessageProcessorImpl;
import com.dragosghinea.royale.internal.utils.messages.impl.processor.PlaceholderAPIMessageProcessorImpl;
import com.dragosghinea.royale.internal.utils.messages.impl.sender.PlainMessageSenderImpl;
import com.dragosghinea.royale.menus.item.click.action.*;
import lombok.AllArgsConstructor;
import org.bukkit.Bukkit;
import org.bukkit.event.inventory.ClickType;

import java.util.List;
import java.util.stream.Collectors;

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
    public ClickActionGroup mapFromConfig(ClickActionGroupCfg clickActionGroupCfg) {
        List<ClickAction> actions = clickActionGroupCfg.getActions().stream()
                .map((strAction) -> {
                    ClickActionCfg clickActionCfg = new ClickActionCfg();
                    clickActionCfg.setClickActionString(strAction);
                    return mapFromConfig(clickActionCfg);
                })
                .collect(Collectors.toList());

        return new ClickActionGroup(clickActionGroupCfg.getClickType(), actions);
    }

    @Override
    public ClickAction mapFromConfig(ClickActionCfg clickActionCfg) {
        ClickAction clickAction = null;

        ClickActionTypes clickActionType = clickActionCfg.getActionType();
        switch (clickActionType) {
            case MESSAGE:
                clickAction = new MessageClickAction(messageSender, clickActionCfg.getArgument());
                break;
            case MENU_CLOSE:
                clickAction = new CloseClickAction();
                break;
            case COMMAND:
                clickAction = new CommandClickAction(messageProcessorChain, clickActionCfg.getArgument());
                break;
            case CONSOLE_COMMAND:
                clickAction = new ConsoleCommandClickAction(messageProcessorChain, clickActionCfg.getArgument());
                break;
            case UNKNOWN:
                throw new IllegalArgumentException("Unknown click action type: " + clickActionCfg.getActionType());
        }

        return clickAction;
    }

}
