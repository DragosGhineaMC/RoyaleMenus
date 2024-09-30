package com.dragosghinea.royale.menus.item.click.action.impl;

import com.dragosghinea.royale.internal.utils.messages.MessageSender;
import com.dragosghinea.royale.internal.utils.messages.StringMessageProcessorChain;
import com.dragosghinea.royale.internal.utils.messages.impl.StringMessageProcessorChainImpl;
import com.dragosghinea.royale.internal.utils.messages.impl.processor.ColorMessageProcessorImpl;
import com.dragosghinea.royale.internal.utils.messages.impl.processor.PlaceholderAPIMessageProcessorImpl;
import com.dragosghinea.royale.internal.utils.messages.impl.sender.PlainMessageSenderImpl;
import com.dragosghinea.royale.menus.item.click.action.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.bukkit.Bukkit;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

// necessary mapping to avoid cyclic dependencies inside the click requirements
@AllArgsConstructor
public class ClickActionNoRequirementsMappingImpl implements ClickActionMapping {
    private final StringMessageProcessorChain messageProcessorChain;
    private final MessageSender messageSender;
    @Getter
    private final ClickActionMappingStorage clickActionMappingStorage;

    public ClickActionNoRequirementsMappingImpl() {
        this(
                new StringMessageProcessorChainImpl(),
                new PlainMessageSenderImpl(),
                new ClickActionMappingStorageImpl()
        );
        messageProcessorChain.addProcessor(new ColorMessageProcessorImpl());
        if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
            messageProcessorChain.addProcessor(new PlaceholderAPIMessageProcessorImpl());
        }

        clickActionMappingStorage.registerClickActionMapping("[message]", (argument) -> new MessageClickAction(messageSender, argument));
        clickActionMappingStorage.registerClickActionMapping("[menu_close]", (argument) -> new CloseClickAction());
        clickActionMappingStorage.registerClickActionMapping("[command]", (argument) -> new CommandClickAction(messageProcessorChain, argument));
        clickActionMappingStorage.registerClickActionMapping("[console_command]", (argument) -> new ConsoleCommandClickAction(messageProcessorChain, argument));
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
        Function<String, ClickAction> clickActionFunction = clickActionMappingStorage.getClickActionMapping(clickActionCfg.getActionType());

        if (clickActionFunction == null) {
            clickActionFunction = ClickActionGlobalDefinitions.INSTANCE.getDefaultClickActionMappings().getOrDefault(clickActionCfg.getActionType(), null);
        }

        if (clickActionFunction == null) {
            throw new IllegalArgumentException("Unknown click action type: " + clickActionCfg.getActionType());
        }


        return clickActionFunction.apply(clickActionCfg.getArgument());
    }

}
