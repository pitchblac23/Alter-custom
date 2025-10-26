package org.alter.plugins.content.combat.strategy.ranged.ammo

import org.alter.rscm.RSCM.getRSCM
/**
 * @author Tom <rspsmods@gmail.com>
 */
object Bolts {
    val BRONZE_BOLTS = arrayOf(getRSCM("items.bolt"), getRSCM("items.poison_bolt"), getRSCM("items.poison_bolt+"), getRSCM("items.poison_bolt++"))
    val IRON_BOLTS = arrayOf(getRSCM("items.xbows_crossbow_bolts_iron"), getRSCM("items.xbows_crossbow_bolts_iron_poisoned"), getRSCM("items.xbows_crossbow_bolts_iron_poisoned+"), getRSCM("items.xbows_crossbow_bolts_iron_poisoned++"))
    val STEEL_BOLTS = arrayOf(getRSCM("items.xbows_crossbow_bolts_steel"), getRSCM("items.xbows_crossbow_bolts_steel_poisoned"), getRSCM("items.xbows_crossbow_bolts_steel_poisoned+"), getRSCM("items.xbows_crossbow_bolts_steel_poisoned++"))
    val MITHRIL_BOLTS = arrayOf(getRSCM("items.xbows_crossbow_bolts_mithril"), getRSCM("items.xbows_crossbow_bolts_mithril_poisoned"), getRSCM("items.xbows_crossbow_bolts_mithril_poisoned+"), getRSCM("items.xbows_crossbow_bolts_mithril_poisoned++"))
    val ADAMANT_BOLTS = arrayOf(getRSCM("items.xbows_crossbow_bolts_adamantite"), getRSCM("items.xbows_crossbow_bolts_adamantite_poisoned"), getRSCM("items.xbows_crossbow_bolts_adamantite_poisoned+"), getRSCM("items.xbows_crossbow_bolts_adamantite_poisoned++"))
    val BROAD_BOLTS = arrayOf(getRSCM("items.slayer_broad_bolt"), getRSCM("items.slayer_broad_bolt_amethyst"))
    val RUNITE_BOLTS = arrayOf(getRSCM("items.xbows_crossbow_bolts_runite"), getRSCM("items.xbows_crossbow_bolts_runite_poisoned"), getRSCM("items.xbows_crossbow_bolts_runite_poisoned+"), getRSCM("items.xbows_crossbow_bolts_runite_poisoned++"))
    val DRAGON_BOLTS = arrayOf(getRSCM("items.dragon_bolts"), getRSCM("items.dragon_bolts_p"), getRSCM("items.dragon_bolts_p+"), getRSCM("items.dragon_bolts_p++"))
    val BLURITE_BOLTS = arrayOf(getRSCM("items.xbows_crossbow_bolts_blurite"), getRSCM("items.xbows_crossbow_bolts_blurite_poisoned"), getRSCM("items.xbows_crossbow_bolts_blurite_poisoned+"), getRSCM("items.xbows_crossbow_bolts_blurite_poisoned++"))
    val BONE_BOLTS = arrayOf(getRSCM("items.dttd_bone_crossbow_bolt"))
    val KEBBIT_BOLTS = arrayOf(getRSCM("items.huntingbow_bolts"), getRSCM("items.huntingbow_bigbolts"))
    val BOLT_RACKS = arrayOf(getRSCM("items.barrows_karil_ammo"))
}
