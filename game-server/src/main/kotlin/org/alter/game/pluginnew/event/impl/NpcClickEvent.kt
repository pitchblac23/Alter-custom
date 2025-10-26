package org.alter.game.pluginnew.event.impl

import dev.openrune.ServerCacheManager.getNpc
import dev.openrune.ServerCacheManager.getObject
import org.alter.game.model.entity.Npc
import org.alter.game.model.entity.Player
import org.alter.game.pluginnew.MenuOption
import org.alter.game.pluginnew.event.PlayerEvent

class NpcAttackEvent(val npc: Npc, player: Player) : PlayerEvent(player)

class NpcClickEvent(
    val npc: Npc,
    val op: MenuOption,
    player: Player
) : PlayerEvent(player) {

    val id : Int = npc.id

    val option: String
        get() = resolveOptionName(id, op.id)

    companion object {
        private fun resolveOptionName(id: Int, opId: Int): String {
            val def = getNpc(id)
                ?: error("Npc not found for id=$id")

            return def.actions.getOrNull(opId - 1)
                ?: error("No action found at index $opId for npc id=$id")
        }
    }
}