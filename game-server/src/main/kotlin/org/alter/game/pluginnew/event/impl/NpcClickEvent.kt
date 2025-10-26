package org.alter.game.pluginnew.event.impl

import dev.openrune.ServerCacheManager.getNpc
import org.alter.game.model.entity.Npc
import org.alter.game.model.entity.Player
import org.alter.game.pluginnew.MenuOption
import org.alter.game.pluginnew.event.PlayerEvent

class NpcAttackEvent(val npc: Npc, player: Player) : PlayerEvent(player)

class NpcClickEvent(
    val npc: Npc,
    val op: MenuOption,
    player: Player
) : EntityInteractionEvent<Npc>(npc, op, player) {

    val id : Int = npc.id

    override fun resolveOptionName(): String {
        val def = getNpc(id) ?: error("Npc not found for id=$id")
        return def.actions.getOrNull(op.id - 1) ?: error("No action found at index ${op.id} for npc id=$id")
    }
}