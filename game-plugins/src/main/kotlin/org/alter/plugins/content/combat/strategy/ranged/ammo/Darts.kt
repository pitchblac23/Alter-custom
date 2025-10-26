package org.alter.plugins.content.combat.strategy.ranged.ammo

import org.alter.rscm.RSCM.getRSCM

/**
 * @author Tom <rspsmods@gmail.com>
 */
object Darts {
    val BRONZE_DARTS = arrayOf(getRSCM("items.bronze_dart"), getRSCM("items.bronze_dart_p"), getRSCM("items.bronze_dart_p+"), getRSCM("items.bronze_dart_p++"))
    val IRON_DARTS = arrayOf(getRSCM("items.iron_dart"), getRSCM("items.iron_dart_p+")/*, Items.IRON_DARTP_5629 */, getRSCM("items.iron_dart_p++"))
    val STEEL_DARTS = arrayOf(getRSCM("items.steel_dart"), getRSCM("items.steel_dart_p"), getRSCM("items.steel_dart_p+"), getRSCM("items.steel_dart_p++"))
    val BLACK_DARTS = arrayOf(getRSCM("items.black_dart"), getRSCM("items.black_dart_p"), getRSCM("items.black_dart_p+"), getRSCM("items.black_dart_p++"))
    val MITHRIL_DARTS = arrayOf(getRSCM("items.mithril_dart"), getRSCM("items.mithril_dart_p"), getRSCM("items.mithril_dart_p+"), getRSCM("items.mithril_dart_p++"))
    val ADAMANT_DARTS = arrayOf(getRSCM("items.adamant_dart"), getRSCM("items.adamant_dart_p"), getRSCM("items.adamant_dart_p+"), getRSCM("items.adamant_dart_p++"))
    val RUNE_DARTS = arrayOf(getRSCM("items.rune_dart"), getRSCM("items.rune_dart_p"), getRSCM("items.rune_dart_p+"), getRSCM("items.rune_dart_p++"))
    val DRAGON_DARTS = arrayOf(getRSCM("items.dragon_dart"), getRSCM("items.dragon_dart_p"), getRSCM("items.dragon_dart_p+"), getRSCM("items.dragon_dart_p++"))

    val DARTS = BRONZE_DARTS + IRON_DARTS + STEEL_DARTS + BLACK_DARTS + MITHRIL_DARTS + ADAMANT_DARTS + RUNE_DARTS + DRAGON_DARTS
}
