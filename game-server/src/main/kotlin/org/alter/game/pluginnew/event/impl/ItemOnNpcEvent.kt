package org.alter.game.pluginnew.event.impl

import org.alter.game.model.entity.Npc
import org.alter.game.model.entity.Player
import org.alter.game.model.item.Item
import org.alter.game.pluginnew.event.PlayerEvent

class ItemOnNpcEvent(
    val npc: Npc,
    val item: Item,
    val slot: Int,
    val interfaceId: Int,
    val componentId: Int,
    player: Player
) : PlayerEvent(player)

