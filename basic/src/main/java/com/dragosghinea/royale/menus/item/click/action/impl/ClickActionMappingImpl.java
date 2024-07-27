package com.dragosghinea.royale.menus.item.click.action.impl;

import com.dragosghinea.royale.menus.item.click.action.*;
import com.dragosghinea.royale.menus.item.click.requirement.ClickRequirement;
import com.dragosghinea.royale.menus.item.click.requirement.ClickRequirementMapping;
import com.dragosghinea.royale.menus.item.click.requirement.impl.ClickRequirementMappingImpl;
import lombok.NoArgsConstructor;

import java.util.Map;
import java.util.function.Function;

@NoArgsConstructor
public class ClickActionMappingImpl implements ClickActionMapping {

    private final ClickActionNoRequirementsMappingImpl clickActionNoRequirementsMapping = new ClickActionNoRequirementsMappingImpl();
    private final ClickRequirementMapping clickRequirementMapping = new ClickRequirementMappingImpl();

    public ClickActionMappingImpl(Map<String, Function<String, ClickAction>> extraClickActionMappings) {
        extraClickActionMappings
                .forEach(
                        (clickActionType, clickActionFunction) -> clickActionNoRequirementsMapping
                                .getClickActionMappingStorage()
                                .registerClickActionMapping(clickActionType, clickActionFunction)
                );
    }

    @Override
    public ClickAction mapFromConfig(ClickActionCfg clickActionCfg) {
        return clickActionNoRequirementsMapping.mapFromConfig(clickActionCfg);
    }

    @Override
    public ClickActionGroup mapFromConfig(ClickActionGroupCfg clickActionCfg) {
        ClickActionGroup toReturn = clickActionNoRequirementsMapping.mapFromConfig(clickActionCfg);

        if (clickActionCfg.getClickRequirement() != null) {
            ClickRequirement mappedClickRequirement = clickRequirementMapping.mapFromConfig(clickActionCfg.getClickRequirement());
            toReturn.setClickRequirement(mappedClickRequirement);
        } else if (clickActionCfg.getClickRequirementsExpression() != null) {
            ClickRequirement mappedClickRequirement = clickRequirementMapping.computeFromConfigHolder(clickActionCfg.getClickRequirementsExpression());
            toReturn.setClickRequirement(mappedClickRequirement);
        }

        return toReturn;
    }
}
