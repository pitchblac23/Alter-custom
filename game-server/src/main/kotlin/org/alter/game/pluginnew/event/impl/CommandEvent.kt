package org.alter.game.pluginnew.event.impl

import org.alter.game.model.entity.Player
import org.alter.game.pluginnew.event.PlayerEvent

class CommandEvent(
    val command: String,
    val args: Array<String>?,
    player: Player
) : MessageEvent(command, player) {
    val arguments: Array<String>? = args
}

