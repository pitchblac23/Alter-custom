package org.alter.game.pluginnew.event.impl

import dev.openrune.ServerCacheManager.getObject
import org.alter.game.model.entity.Player
import org.alter.game.pluginnew.event.PlayerEvent

enum class ObjectOption(val id: Int) {
    OP1(0),
    OP2(1),
    OP3(2),
    OP4(3),
    OP5(4),
    OP6(5);

    companion object {
        fun fromId(id: Int): ObjectOption = entries.find { it.id == id } ?: error("Invalid object option id: $id")
    }
}

class ObjectClickEvent(
    val id: Int,
    val op: ObjectOption,
    player: Player
) : PlayerEvent(player) {

    val option: String
        get() = resolveOptionName(id, op.id)

    companion object {
        private fun resolveOptionName(id: Int, opId: Int): String {
            val def = getObject(id)
                ?: error("Object not found for id=$id")

            return def.actions.getOrNull(opId - 1)
                ?: error("No action found at index $opId for object id=$id")
        }
    }
}