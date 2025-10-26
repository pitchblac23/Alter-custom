package org.alter.plugins.content.combat.strategy.ranged.ammo

import org.alter.rscm.RSCM.getRSCM
/**
 * @author Tom <rspsmods@gmail.com>
 */
object Arrows {
    val TRAINING_ARROWS = arrayOf(getRSCM("items.aide_arrow"))
    val OGRE_ARROWS = arrayOf(getRSCM("items.ogre_arrow"))

    val BRONZE_ARROWS = arrayOf(getRSCM("items.bronze_arrow"), getRSCM("items.bronze_arrow_p"), getRSCM("items.bronze_arrow_p+"), getRSCM("items.bronze_arrow_p++"))
    val IRON_ARROWS = arrayOf(getRSCM("items.iron_arrow"), getRSCM("items.iron_arrow_p"), getRSCM("items.iron_arrow_p+"), getRSCM("items.iron_arrow_p++"))
    val STEEL_ARROWS = arrayOf(getRSCM("items.steel_arrow"), getRSCM("items.steel_arrow_p"), getRSCM("items.steel_arrow_p+"), getRSCM("items.steel_arrow_p++"))
    val MITHRIL_ARROWS = arrayOf(getRSCM("items.mithril_arrow"), getRSCM("items.mithril_arrow_p"), getRSCM("items.mithril_arrow_p+"), getRSCM("items.mithril_arrow_p++"))
    val ADAMANT_ARROWS = arrayOf(getRSCM("items.adamant_arrow"), getRSCM("items.adamant_arrow_p"), getRSCM("items.adamant_arrow_p+"), getRSCM("items.adamant_arrow_p++"))
    val RUNE_ARROWS = arrayOf(getRSCM("items.rune_arrow"), getRSCM("items.rune_arrow_p"), getRSCM("items.rune_arrow_p+"), getRSCM("items.rune_arrow_p++"))
    val AMETHYST_ARROWS = arrayOf(getRSCM("items.amethyst_arrow"), getRSCM("items.amethyst_arrow_p"), getRSCM("items.amethyst_arrow_p+"), getRSCM("items.amethyst_arrow_p++"))
    val DRAGON_ARROWS = arrayOf(getRSCM("items.dragon_arrow"), getRSCM("items.dragon_arrow_p"), getRSCM("items.dragon_arrow_p+"), getRSCM("items.dragon_arrow_p++"))

    val BRUTAL_BRONZE_ARROWS = arrayOf(getRSCM("items.zogre_brutal_bronze"))
    val BRUTAL_IRON_ARROWS = arrayOf(getRSCM("items.zogre_brutal_iron"))
    val BRUTAL_STEEL_ARROWS = arrayOf(getRSCM("items.zogre_brutal_steel"))
    val BRUTAL_BLACK_ARROWS = arrayOf(getRSCM("items.zogre_brutal_black"))
    val BRUTAL_MITHRIL_ARROWS = arrayOf(getRSCM("items.zogre_brutal_mithril"))
    val BRUTAL_ADAMANT_ARROWS = arrayOf(getRSCM("items.zogre_brutal_adamant"))
    val BRUTAL_RUNE_ARROWS = arrayOf(getRSCM("items.zogre_brutal_rune"))
}
