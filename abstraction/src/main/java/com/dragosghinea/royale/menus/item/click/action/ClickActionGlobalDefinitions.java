package com.dragosghinea.royale.menus.item.click.action;

import lombok.Getter;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

@Getter
public enum ClickActionGlobalDefinitions {
    INSTANCE;

    private final Map<String, Function<String, ClickAction>> defaultClickActionMappings = new ConcurrentHashMap<>();

    public void registerDefaultClickActionMapping(String clickActionType, Function<String, ClickAction> clickActionMapping) {
        defaultClickActionMappings.compute(clickActionType.toUpperCase(), (key, oldValue) -> {
            if (oldValue != null)
                throw new IllegalArgumentException("Click action type " + clickActionType + " is already registered");

            return clickActionMapping;
        });
    }

    public void unregisterDefaultClickActionMapping(String clickActionType) {
        defaultClickActionMappings.remove(clickActionType.toUpperCase());
    }

}
