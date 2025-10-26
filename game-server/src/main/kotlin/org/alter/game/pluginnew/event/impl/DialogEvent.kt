package org.alter.game.pluginnew.event.impl

import org.alter.game.model.entity.Player
import org.alter.game.pluginnew.event.PlayerEvent

class DialogInputEvent(
    val name: String,
    player: Player
) : MessageEvent(name, player)

class DialogCountEvent(
    val count: Int,
    player: Player
) : ValueEvent<Int>(count, player)

class DialogStringEvent(
    val text: String,
    player: Player
) : MessageEvent(text, player)

class DialogPlayerEvent(
    val name: String,
    target : Player?,
    player: Player
) : PlayerEvent(player) {
    val targetName: String = name
    val targetPlayer: Player? = target
}

class DialogItemEvent(
    val item: Int,
    player: Player
) : ValueEvent<Int>(item, player)

