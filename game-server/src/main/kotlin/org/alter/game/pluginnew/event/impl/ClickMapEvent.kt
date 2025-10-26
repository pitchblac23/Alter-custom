package org.alter.game.pluginnew.event.impl

import org.alter.game.model.Tile
import org.alter.game.model.entity.Player
import org.alter.game.pluginnew.event.PlayerEvent

class ClickMapEvent(
    override val tile : Tile,
    val keyCombination: Int,
    player: Player
) : LocationEvent(tile, player) {
    val keyCombo: Int = keyCombination
}

