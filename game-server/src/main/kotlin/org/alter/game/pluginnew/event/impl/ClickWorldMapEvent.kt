package org.alter.game.pluginnew.event.impl

import org.alter.game.model.Tile
import org.alter.game.model.entity.Player
import org.alter.game.pluginnew.event.PlayerEvent

class ClickWorldMapEvent(
    override val tile : Tile,
    player: Player
) : LocationEvent(tile, player)

