package org.alter.plugins.content.skills.woodcutting

import org.alter.api.cfg.Animation
import org.alter.rscm.RSCM.getRSCM

/**
 * @author Tom <rspsmods@gmail.com>
 */
enum class AxeType(val item: Int, val level: Int, val animation: Int) {
    BRONZE(item = getRSCM("items.bronze_axe"), level = 1, animation = Animation.WOODCUTTING_BRONZE_AXE),
    IRON(item = getRSCM("items.iron_axe"), level = 1, animation = Animation.WOODCUTTING_IRON_AXE),
    STEEL(item = getRSCM("items.steel_axe"), level = 6, animation = Animation.WOODCUTTING_STEEL_AXE),
    BLACK(item = getRSCM("items.black_axe"), level = 11, animation = Animation.WOODCUTTING_BLACK_AXE),
    MITHRIL(item = getRSCM("items.mithril_axe"), level = 21, animation = Animation.WOODCUTTING_MITHRIL_AXE),
    ADAMANT(item = getRSCM("items.adamant_axe"), level = 31, animation = Animation.WOODCUTTING_ADAMANT_AXE),
    RUNE(item = getRSCM("items.rune_axe"), level = 41, animation = Animation.WOODCUTTING_RUNE_AXE),
    DRAGON(item = getRSCM("items.dragon_axe"), level = 61, animation = Animation.WOODCUTTING_DRAGON_AXE),
    INFERNAL(item = getRSCM("items.infernal_axe"), level = 61, animation = Animation.WOODCUTTING_INFERNAL_AXE),
    THIRDAGE(item = getRSCM("items.3a_axe"), level = 61, animation = Animation.WOODCUTTING_THIRDAGE_AXE),
    CRYSTAL(item = getRSCM("items.crystal_axe"), level = 71, animation = Animation.WOODCUTTING_CRYSTAL_AXE),
    ;

    companion object {
        val values = enumValues<AxeType>()
    }
}
