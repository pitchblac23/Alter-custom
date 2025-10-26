package org.alter.game.pluginnew.event.impl

import org.alter.game.model.entity.GroundItem
import org.alter.game.model.entity.Player
import org.alter.game.pluginnew.event.PlayerEvent

class GroundItemClickEvent(
    val groundItem: GroundItem,
    val option: Int,
    val tile: org.alter.game.model.Tile,
    player: Player
) : PlayerEvent(player)

