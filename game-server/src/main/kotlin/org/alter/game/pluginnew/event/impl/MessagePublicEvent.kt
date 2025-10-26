package org.alter.game.pluginnew.event.impl

import org.alter.game.model.entity.Player
import org.alter.game.pluginnew.event.PlayerEvent

class MessagePublicEvent(
    override val message: String,
    val colour: Int,
    val effect: Int,
    val type: Int,
    val pattern: ByteArray?,
    player: Player
) : MessageEvent(message, player) {
    val color: Int = colour
    val effectType: Int = effect
    val messageType: Int = type
    val patternData: ByteArray? = pattern
}

