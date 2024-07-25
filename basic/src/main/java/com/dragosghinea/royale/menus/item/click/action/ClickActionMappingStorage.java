package com.dragosghinea.royale.menus.item.click.action;

import java.util.function.Function;

public interface ClickActionMappingStorage {

    Function<String, ClickAction> getClickActionMapping(String clickActionType);

    void registerClickActionMapping(String clickActionType, Function<String, ClickAction> clickActionMapping);

    void unregisterClickActionMapping(String clickActionType);

}
