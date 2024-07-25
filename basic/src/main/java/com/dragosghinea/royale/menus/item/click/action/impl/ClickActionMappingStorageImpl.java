package com.dragosghinea.royale.menus.item.click.action.impl;

import com.dragosghinea.royale.menus.item.click.action.ClickAction;
import com.dragosghinea.royale.menus.item.click.action.ClickActionMappingStorage;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class ClickActionMappingStorageImpl implements ClickActionMappingStorage {

    private final Map<String, Function<String, ClickAction>> clickActionMappings = new HashMap<>();

    @Override
    public Function<String, ClickAction> getClickActionMapping(String clickActionType) {
        return clickActionMappings.get(clickActionType.toUpperCase());
    }

    @Override
    public void registerClickActionMapping(String clickActionType, Function<String, ClickAction> clickActionMapping) {
        clickActionMappings.put(clickActionType.toUpperCase(), clickActionMapping);
    }

    @Override
    public void unregisterClickActionMapping(String clickActionType) {
        clickActionMappings.remove(clickActionType.toUpperCase());
    }
}
