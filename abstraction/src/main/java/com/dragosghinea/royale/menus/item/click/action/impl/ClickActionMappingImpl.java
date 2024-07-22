package com.dragosghinea.royale.menus.item.click.action.impl;

import com.dragosghinea.royale.menus.item.click.action.ClickAction;
import com.dragosghinea.royale.menus.item.click.action.ClickActionCfg;
import com.dragosghinea.royale.menus.item.click.action.ClickActionMapping;
import com.dragosghinea.royale.menus.item.click.requirement.ClickRequirement;
import com.dragosghinea.royale.menus.item.click.requirement.ClickRequirementMapping;
import com.dragosghinea.royale.menus.item.click.requirement.impl.ClickRequirementMappingImpl;

public class ClickActionMappingImpl implements ClickActionMapping {

    private final ClickActionMapping clickActionNoRequirementsMapping = new ClickActionNoRequirementsMappingImpl();
    private final ClickRequirementMapping clickRequirementMapping = new ClickRequirementMappingImpl();


    @Override
    public ClickAction mapFromConfig(ClickActionCfg clickActionCfg) {
        ClickAction toReturn = clickActionNoRequirementsMapping.mapFromConfig(clickActionCfg);

        if (clickActionCfg.getClickRequirement() != null) {
            ClickRequirement mappedClickRequirement = clickRequirementMapping.mapFromConfig(clickActionCfg.getClickRequirement());
            toReturn.setClickRequirement(mappedClickRequirement);
        }
        else if (clickActionCfg.getClickRequirementsExpression() != null) {
            ClickRequirement mappedClickRequirement = clickRequirementMapping.computeFromConfigHolder(clickActionCfg.getClickRequirementsExpression());
            toReturn.setClickRequirement(mappedClickRequirement);
        }

        return toReturn;
    }
}
