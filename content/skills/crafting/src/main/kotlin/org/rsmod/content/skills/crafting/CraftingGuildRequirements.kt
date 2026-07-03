package org.rsmod.content.skills.crafting

import dev.openrune.ServerCacheManager
import org.rsmod.api.player.back
import org.rsmod.game.entity.Player
import org.rsmod.game.inv.InvObj

private val craftingOutfits = setOf(
    "obj.skillcape_crafting",
    "obj.skillcape_crafting_trimmed"
)

private fun InvObj?.hasItemContent(content: String): Boolean {
    val obj = this ?: return false
    val type = ServerCacheManager.getItem(obj.id) ?: return false
    return type.isContentType(content)
}

private fun Player.wearingMaxCape(): Boolean =
    back.hasItemContent("content.max_cape")

internal fun Player.wearingApron(): Boolean = "obj.brown_apron" in worn ||
                                              "obj.golden_apron" in worn

internal fun Player.outfitInv(): Boolean = "obj.brown_apron" in inv ||
                                           "obj.golden_apron" in inv ||
                                           "obj.skillcape_crafting" in inv ||
                                           "obj.skillcape_crafting_trimmed" in inv

internal fun Player.hasGuildEntryOutfit(): Boolean {
    if (wearingApron()) return true
    if (craftingOutfits.any { it in worn }) return true
    if (wearingMaxCape()) return true
    return false
}

internal fun Player.ownsCookingSkillcape(): Boolean =
    "obj.skillcape_crafting" in inv ||
    "obj.skillcape_crafting_trimmed" in inv ||
    "obj.skillcape_crafting" in worn ||
    "obj.skillcape_crafting_trimmed" in worn
