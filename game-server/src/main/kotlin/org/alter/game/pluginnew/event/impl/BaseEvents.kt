package org.alter.game.pluginnew.event.impl

import net.rsprot.protocol.util.CombinedId
import org.alter.game.model.Tile
import org.alter.game.model.entity.Player
import org.alter.game.model.item.Item
import org.alter.game.pluginnew.MenuOption
import org.alter.game.pluginnew.event.PlayerEvent

/**
 * Base class for entity interaction events (Player, NPC, Object clicks)
 */
abstract class EntityInteractionEvent<T>(
    open val target: T,
    open val option: MenuOption,
    player: Player
) : PlayerEvent(player) {
    val optionName: String
        get() = resolveOptionName()
    
    protected abstract fun resolveOptionName(): String
}

/**
 * Base class for simple events that just carry a value
 */
abstract class ValueEvent<T>(
    val value: T,
    player: Player
) : PlayerEvent(player)

/**
 * Base class for events that carry a message/text
 */
abstract class MessageEvent(
    open val message: String,
    player: Player
) : PlayerEvent(player)

/**
 * Base class for events that carry a tile/location
 */
abstract class LocationEvent(
    open val tile: Tile,
    player: Player
) : PlayerEvent(player)


