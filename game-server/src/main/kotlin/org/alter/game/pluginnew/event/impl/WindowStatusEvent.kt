package org.alter.game.pluginnew.event.impl

import org.alter.game.model.entity.Player
import org.alter.game.pluginnew.event.PlayerEvent

class WindowStatusEvent(
    val frameWidth: Int,
    val frameHeight: Int,
    val windowMode: Int,
    player: Player
) : PlayerEvent(player)

