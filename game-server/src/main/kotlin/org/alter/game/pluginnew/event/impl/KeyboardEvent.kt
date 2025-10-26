package org.alter.game.pluginnew.event.impl

import org.alter.game.model.entity.Player
import org.alter.game.pluginnew.event.PlayerEvent

class KeyboardEvent(
    val keyCode: Int,
    val keyChar: Char,
    player: Player
) : PlayerEvent(player)

