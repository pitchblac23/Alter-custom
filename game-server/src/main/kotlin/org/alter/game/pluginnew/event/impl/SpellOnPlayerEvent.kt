package org.alter.game.pluginnew.event.impl

import org.alter.game.model.entity.Player
import org.alter.game.model.item.Item
import org.alter.game.pluginnew.event.PlayerEvent

class SpellOnPlayerEvent(
    val target: Player,
    val item: Item,
    val slot: Int,
    val interfaceId: Int,
    val componentId: Int,
    player: Player
) : PlayerEvent(player)

