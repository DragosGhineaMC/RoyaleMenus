package com.dragosghinea.royale.menus.item.click.action.impl;

import com.dragosghinea.royale.menus.item.click.action.ClickAction;
import com.dragosghinea.royale.menus.item.click.action.ClickActionCfg;
import com.dragosghinea.royale.menus.item.click.action.ClickActionMapping;

// necessary mapping to avoid cyclic dependencies inside the click requirements
public class ClickActionNoRequirementsMappingImpl implements ClickActionMapping {

    @Override
    public ClickAction mapFromConfig(ClickActionCfg clickActionCfg) {
        return null;
    }

}
