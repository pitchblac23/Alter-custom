package org.alter.game.pluginnew.event.impl

import org.alter.game.model.entity.Player
import org.alter.game.model.item.Item
import org.alter.game.pluginnew.event.PlayerEvent

class ItemOnGroundItemEvent(
    val item: Item,
    val slot: Int,
    val groundItemId: Int,
    val tile: org.alter.game.model.Tile,
    player: Player
) : PlayerEvent(player)

