package org.alter.game.pluginnew.event.impl

import org.alter.game.model.ExamineEntityType
import org.alter.game.model.entity.Player
import org.alter.game.pluginnew.event.PlayerEvent

class ExamineEvent(
    val id: Int,
    val type: ExamineEntityType,
    player: Player
) : ValueEvent<Int>(id, player) {
    val entityType: ExamineEntityType = type
}

