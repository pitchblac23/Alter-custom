package org.alter.game.pluginnew.event

import org.alter.game.model.entity.Player

interface Event

abstract class PlayerEvent(open val player: Player) : Event {

    fun post() { EventManager.post(this) }

}

abstract class GameEvent : Event