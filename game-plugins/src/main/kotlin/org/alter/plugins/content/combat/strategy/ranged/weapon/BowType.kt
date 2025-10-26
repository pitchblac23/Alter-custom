package org.alter.plugins.content.combat.strategy.ranged.weapon

import org.alter.rscm.RSCM.getRSCM
import org.alter.plugins.content.combat.strategy.ranged.ammo.Arrows.ADAMANT_ARROWS
import org.alter.plugins.content.combat.strategy.ranged.ammo.Arrows.AMETHYST_ARROWS
import org.alter.plugins.content.combat.strategy.ranged.ammo.Arrows.BRONZE_ARROWS
import org.alter.plugins.content.combat.strategy.ranged.ammo.Arrows.BRUTAL_ADAMANT_ARROWS
import org.alter.plugins.content.combat.strategy.ranged.ammo.Arrows.BRUTAL_BLACK_ARROWS
import org.alter.plugins.content.combat.strategy.ranged.ammo.Arrows.BRUTAL_BRONZE_ARROWS
import org.alter.plugins.content.combat.strategy.ranged.ammo.Arrows.BRUTAL_IRON_ARROWS
import org.alter.plugins.content.combat.strategy.ranged.ammo.Arrows.BRUTAL_MITHRIL_ARROWS
import org.alter.plugins.content.combat.strategy.ranged.ammo.Arrows.BRUTAL_RUNE_ARROWS
import org.alter.plugins.content.combat.strategy.ranged.ammo.Arrows.BRUTAL_STEEL_ARROWS
import org.alter.plugins.content.combat.strategy.ranged.ammo.Arrows.DRAGON_ARROWS
import org.alter.plugins.content.combat.strategy.ranged.ammo.Arrows.IRON_ARROWS
import org.alter.plugins.content.combat.strategy.ranged.ammo.Arrows.MITHRIL_ARROWS
import org.alter.plugins.content.combat.strategy.ranged.ammo.Arrows.OGRE_ARROWS
import org.alter.plugins.content.combat.strategy.ranged.ammo.Arrows.RUNE_ARROWS
import org.alter.plugins.content.combat.strategy.ranged.ammo.Arrows.STEEL_ARROWS
import org.alter.plugins.content.combat.strategy.ranged.ammo.Arrows.TRAINING_ARROWS

/**
 * @author Tom <rspsmods@gmail.com>
 */
enum class BowType(val item: Int, val ammo: Array<Int>) {
    TRAINING_BOW(item = getRSCM("items.aide_shortbow"), ammo = TRAINING_ARROWS),

    SHORTBOW(item = getRSCM("items.shortbow"), ammo = BRONZE_ARROWS + IRON_ARROWS),
    LONGBOW(item = getRSCM("items.longbow"), ammo = BRONZE_ARROWS + IRON_ARROWS),

    OAK_SHORTBOW(item = getRSCM("items.oak_shortbow"), ammo = BRONZE_ARROWS + IRON_ARROWS + STEEL_ARROWS),
    OAK_LONGBOW(item = getRSCM("items.oak_longbow"), ammo = BRONZE_ARROWS + IRON_ARROWS + STEEL_ARROWS),

    WILLOW_SHORTBOW(item = getRSCM("items.willow_shortbow"), ammo = BRONZE_ARROWS + IRON_ARROWS + STEEL_ARROWS + MITHRIL_ARROWS),
    WILLOW_COMP_BOW(item = getRSCM("items.trail_composite_bow_willow"), ammo = BRONZE_ARROWS + IRON_ARROWS + STEEL_ARROWS + MITHRIL_ARROWS),
    WILLOW_LONGBOW(item = getRSCM("items.willow_longbow"), ammo = BRONZE_ARROWS + IRON_ARROWS + STEEL_ARROWS + MITHRIL_ARROWS),

    MAPLE_SHORTBOW(item = getRSCM("items.maple_shortbow"), ammo = BRONZE_ARROWS + IRON_ARROWS + STEEL_ARROWS + MITHRIL_ARROWS + ADAMANT_ARROWS),
    MAPLE_LONGBOW(item = getRSCM("items.maple_longbow"), ammo = BRONZE_ARROWS + IRON_ARROWS + STEEL_ARROWS + MITHRIL_ARROWS + ADAMANT_ARROWS),

    OGRE_BOW(item = getRSCM("items.ogre_bow"), ammo = OGRE_ARROWS),
    COMP_OGRE_BOW(
        item = getRSCM("items.zogre_bow"),
        ammo = BRUTAL_BRONZE_ARROWS + BRUTAL_IRON_ARROWS + BRUTAL_STEEL_ARROWS + BRUTAL_BLACK_ARROWS + BRUTAL_MITHRIL_ARROWS + BRUTAL_ADAMANT_ARROWS + BRUTAL_RUNE_ARROWS,
    ),

    YEW_SHORTBOW(
        item = getRSCM("items.yew_shortbow"),
        ammo = BRONZE_ARROWS + IRON_ARROWS + STEEL_ARROWS + MITHRIL_ARROWS + ADAMANT_ARROWS + RUNE_ARROWS,
    ),
    YEW_LONGBOW(
        item = getRSCM("items.yew_longbow"),
        ammo = BRONZE_ARROWS + IRON_ARROWS + STEEL_ARROWS + MITHRIL_ARROWS + ADAMANT_ARROWS + RUNE_ARROWS,
    ),
    YEW_COMP_BOW(
        item = getRSCM("items.trail_composite_bow_yew"),
        ammo = BRONZE_ARROWS + IRON_ARROWS + STEEL_ARROWS + MITHRIL_ARROWS + ADAMANT_ARROWS + RUNE_ARROWS,
    ),

    MAGIC_SHORTBOW(
        item = getRSCM("items.magic_shortbow"),
        ammo = BRONZE_ARROWS + IRON_ARROWS + STEEL_ARROWS + MITHRIL_ARROWS + ADAMANT_ARROWS + RUNE_ARROWS + AMETHYST_ARROWS,
    ),
    MAGIC_SHORTBOW_I(
        item = getRSCM("items.magic_shortbow_i"),
        ammo = BRONZE_ARROWS + IRON_ARROWS + STEEL_ARROWS + MITHRIL_ARROWS + ADAMANT_ARROWS + RUNE_ARROWS + AMETHYST_ARROWS,
    ),
    MAGIC_LONGBOW(
        item = getRSCM("items.magic_longbow"),
        ammo = BRONZE_ARROWS + IRON_ARROWS + STEEL_ARROWS + MITHRIL_ARROWS + ADAMANT_ARROWS + RUNE_ARROWS + AMETHYST_ARROWS,
    ),
    MAGIC_COMP_BOW(
        item = getRSCM("items.trail_composite_bow_magic"),
        ammo = BRONZE_ARROWS + IRON_ARROWS + STEEL_ARROWS + MITHRIL_ARROWS + ADAMANT_ARROWS + RUNE_ARROWS + AMETHYST_ARROWS,
    ),

    SEERCULL(
        item = getRSCM("items.daganoth_cave_magic_shortbow"),
        ammo = BRONZE_ARROWS + IRON_ARROWS + STEEL_ARROWS + MITHRIL_ARROWS + ADAMANT_ARROWS + RUNE_ARROWS + AMETHYST_ARROWS,
    ),
    CRAWS_BOW(item = getRSCM("items.wild_cave_bow_charged"), ammo = emptyArray()),

    DARK_BOW(
        item = getRSCM("items.darkbow"),
        ammo = BRONZE_ARROWS + IRON_ARROWS + STEEL_ARROWS + MITHRIL_ARROWS + ADAMANT_ARROWS + RUNE_ARROWS + AMETHYST_ARROWS + DRAGON_ARROWS,
    ),
    BLUE_DARK_BOW(
        item = getRSCM("items.darkbow_green"),
        ammo = BRONZE_ARROWS + IRON_ARROWS + STEEL_ARROWS + MITHRIL_ARROWS + ADAMANT_ARROWS + RUNE_ARROWS + AMETHYST_ARROWS + DRAGON_ARROWS,
    ),
    GREEN_DARK_BOW(
        item = getRSCM("items.darkbow_blue"),
        ammo = BRONZE_ARROWS + IRON_ARROWS + STEEL_ARROWS + MITHRIL_ARROWS + ADAMANT_ARROWS + RUNE_ARROWS + AMETHYST_ARROWS + DRAGON_ARROWS,
    ),
    WHITE_DARK_BOW(
        item = getRSCM("items.darkbow_yellow"),
        ammo = BRONZE_ARROWS + IRON_ARROWS + STEEL_ARROWS + MITHRIL_ARROWS + ADAMANT_ARROWS + RUNE_ARROWS + AMETHYST_ARROWS + DRAGON_ARROWS,
    ),
    YELLOW_DARK_BOW(
        item = getRSCM("items.darkbow_white"),
        ammo = BRONZE_ARROWS + IRON_ARROWS + STEEL_ARROWS + MITHRIL_ARROWS + ADAMANT_ARROWS + RUNE_ARROWS + AMETHYST_ARROWS + DRAGON_ARROWS,
    ),

    THIRD_AGE_BOW(
        item = getRSCM("items.trail_ranger_bow"),
        ammo = BRONZE_ARROWS + IRON_ARROWS + STEEL_ARROWS + MITHRIL_ARROWS + ADAMANT_ARROWS + RUNE_ARROWS + AMETHYST_ARROWS + DRAGON_ARROWS,
    ),

    CRYSTAL_BOW_110(item = getRSCM("items.roving_crystal_bow_100"), ammo = emptyArray()),
    CRYSTAL_BOW_210(item = getRSCM("items.roving_crystal_bow_200"), ammo = emptyArray()),
    CRYSTAL_BOW_310(item = getRSCM("items.roving_crystal_bow_300"), ammo = emptyArray()),
    CRYSTAL_BOW_410(item = getRSCM("items.roving_crystal_bow_400"), ammo = emptyArray()),
    CRYSTAL_BOW_510(item = getRSCM("items.roving_crystal_bow_500"), ammo = emptyArray()),
    CRYSTAL_BOW_610(item = getRSCM("items.roving_crystal_bow_600"), ammo = emptyArray()),
    CRYSTAL_BOW_710(item = getRSCM("items.roving_crystal_bow_700"), ammo = emptyArray()),
    CRYSTAL_BOW_810(item = getRSCM("items.roving_crystal_bow_800"), ammo = emptyArray()),
    CRYSTAL_BOW_910(item = getRSCM("items.roving_crystal_bow_900"), ammo = emptyArray()),
    CRYSTAL_BOW_FULL(item = getRSCM("items.roving_crystal_bow_1000"), ammo = emptyArray()),
    CRYSTAL_BOW_NEW(item = getRSCM("items.roving_crystal_bow_new"), ammo = emptyArray()),

    TWISTED_BOW(
        item = getRSCM("items.twisted_bow"),
        ammo = BRONZE_ARROWS + IRON_ARROWS + STEEL_ARROWS + MITHRIL_ARROWS + ADAMANT_ARROWS + RUNE_ARROWS + AMETHYST_ARROWS + DRAGON_ARROWS,
    ),
    ;

    companion object {
        val values = enumValues<BowType>()
    }
}
