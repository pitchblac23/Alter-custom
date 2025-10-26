package org.alter.game.pluginnew.event.impl

import net.rsprot.protocol.util.CombinedId
import org.alter.game.model.entity.Player
import org.alter.game.pluginnew.event.PlayerEvent

class ButtonClickEvent(
    val component: CombinedId,
    val option: Int,
    val item: Int,
    val slot: Int,
    player: Player
) : PlayerEvent(player) {
    val itemId: Int = item
    val slotId: Int = slot
    val optionId: Int = option
}

