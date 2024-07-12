package com.dragosghinea.royale.menus.marker;

import com.dragosghinea.royale.internal.utils.VersionUtils;
import org.bukkit.plugin.Plugin;

public class MarkerManager {
    private static final String MARK = "royale_menus_marker";

    private static volatile RoyaleItemMarker marker;

    public static RoyaleItemMarker getAvailableMarker(Plugin plugin) {
        if (marker == null) {
            synchronized (MarkerManager.class) {
                if (marker == null) {
                    marker = generate(plugin);
                }
            }
        }

        return marker;
    }

    private static RoyaleItemMarker generate(Plugin plugin) {
        if (VersionUtils.IS_PDC_VERSION)
            return new PDCMarker(plugin, MARK);
        else
            return new NMSMarker(MARK);
    }
}
