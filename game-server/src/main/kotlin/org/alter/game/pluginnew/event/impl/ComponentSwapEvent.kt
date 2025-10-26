package org.alter.game.pluginnew.event.impl

import net.rsprot.protocol.util.CombinedId
import org.alter.game.model.entity.Player
import org.alter.game.pluginnew.event.PlayerEvent

class ComponentSwapEvent(
    val fromItemId: Int,
    val toItemId: Int,
    val fromSlot: Int,
    val toSlot: Int,
    val from : CombinedId,
    val to : CombinedId,
    player: Player
) : PlayerEvent(player)

