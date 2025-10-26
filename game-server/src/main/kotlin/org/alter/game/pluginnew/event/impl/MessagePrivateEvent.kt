package org.alter.game.pluginnew.event.impl

import org.alter.game.model.entity.Player
import org.alter.game.pluginnew.event.PlayerEvent

class MessagePrivateEvent(
    val name: String,
    val message: String,
    player: Player
) : PlayerEvent(player)

