package org.alter.game.pluginnew.event.impl

import org.alter.game.model.entity.Player
import org.alter.game.pluginnew.event.PlayerEvent

class PlayerAttackEvent(
    val target: Player,
    val option: Int,
    player: Player
) : PlayerEvent(player)

