package org.alter.game.pluginnew.event.impl

import org.alter.game.model.entity.Player
import org.alter.game.pluginnew.event.PlayerEvent

class MouseClickEvent(
    val x: Int,
    val y: Int,
    val button: Int,
    val rightClick : Boolean,
    val lastTransmittedMouseClick : Int,
    player: Player
) : PlayerEvent(player)

