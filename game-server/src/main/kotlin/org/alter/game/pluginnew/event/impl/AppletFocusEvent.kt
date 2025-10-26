package org.alter.game.pluginnew.event.impl

import org.alter.game.model.entity.Player
import org.alter.game.pluginnew.event.PlayerEvent

class AppletFocusEvent(
    val inFocus: Boolean,
    player: Player
) : PlayerEvent(player)

