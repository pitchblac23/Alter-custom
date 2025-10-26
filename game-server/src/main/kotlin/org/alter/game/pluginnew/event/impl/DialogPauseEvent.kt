package org.alter.game.pluginnew.event.impl

import net.rsprot.protocol.util.CombinedId
import org.alter.game.model.entity.Player
import org.alter.game.pluginnew.event.PlayerEvent

class DialogPauseEvent(
    val combined : CombinedId,
    val slot: Int,
    player: Player
) : PlayerEvent(player)

