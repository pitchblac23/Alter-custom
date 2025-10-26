package org.alter.plugins.content.combat.strategy.ranged.ammo

import org.alter.rscm.RSCM.getRSCM

/**
 * @author Tom <rspsmods@gmail.com>
 */
object Knives {
    val BRONZE_KNIVES = arrayOf(getRSCM("items.bronze_knife"), getRSCM("items.bronze_knife_p"), getRSCM("items.bronze_knife_p+"), getRSCM("items.bronze_knife_p++"))
    val IRON_KNIVES = arrayOf(getRSCM("items.iron_knife"), getRSCM("items.iron_knife_p"), getRSCM("items.iron_knife_p+"), getRSCM("items.iron_knife_p++"))
    val STEEL_KNIVES = arrayOf(getRSCM("items.steel_knife"), getRSCM("items.steel_knife_p"), getRSCM("items.steel_knife_p+"), getRSCM("items.steel_knife_p++"))
    val BLACK_KNIVES = arrayOf(getRSCM("items.black_knife"), getRSCM("items.black_knife_p"), getRSCM("items.black_knife_p+"), getRSCM("items.black_knife_p++"))
    val MITHRIL_KNIVES = arrayOf(getRSCM("items.mithril_knife"), getRSCM("items.mithril_knife_p"), getRSCM("items.mithril_knife_p+"), getRSCM("items.mithril_knife_p++"))
    val ADAMANT_KNIVES = arrayOf(getRSCM("items.adamant_knife"), getRSCM("items.adamant_knife_p"), getRSCM("items.adamant_knife_p+"), getRSCM("items.adamant_knife_p++"))
    val RUNE_KNIVES = arrayOf(getRSCM("items.rune_knife"), getRSCM("items.rune_knife_p"), getRSCM("items.rune_knife_p+"), getRSCM("items.rune_knife_p++"))
    val DRAGON_KNIVES = arrayOf(getRSCM("items.dragon_knife"), getRSCM("items.dragon_knife_p"), getRSCM("items.dragon_knife_p+"), getRSCM("items.dragon_knife_p++"))

    val KNIVES = BRONZE_KNIVES + IRON_KNIVES + STEEL_KNIVES + BLACK_KNIVES + MITHRIL_KNIVES + ADAMANT_KNIVES + RUNE_KNIVES + DRAGON_KNIVES
}
