package com.dragosghinea.royale.menus.config;

import com.dragosghinea.royale.menus.item.MenuItemStackCfg;
import com.dragosghinea.royale.menus.item.RoyaleMenuItem;

public interface RoyaleMenuItemMapper {

    RoyaleMenuItem mapFromConfig(MenuItemStackCfg cfg);

}
