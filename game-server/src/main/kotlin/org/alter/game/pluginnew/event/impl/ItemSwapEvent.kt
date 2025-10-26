package org.alter.game.pluginnew.event.impl

import org.alter.game.model.entity.Player
import org.alter.game.model.item.Item
import org.alter.game.pluginnew.event.PlayerEvent

class ItemSwapEvent(
    val fromItem: Item,
    val toItem: Item,
    val fromSlot: Int,
    val toSlot: Int,
    val fromInterfaceId: Int,
    val fromComponentId: Int,
    val toInterfaceId: Int,
    val toComponentId: Int,
    player: Player
) : PlayerEvent(player)

