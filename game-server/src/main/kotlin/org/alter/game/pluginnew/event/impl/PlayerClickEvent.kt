package org.alter.game.pluginnew.event.impl

import org.alter.game.model.entity.Player
import org.alter.game.pluginnew.MenuOption
import org.alter.game.pluginnew.event.PlayerEvent

class PlayerClickEvent(
    override val target: Player,
    override val option: MenuOption,
    player: Player
) : EntityInteractionEvent<Player>(target, option, player) {
    override fun resolveOptionName(): String = 
        target.options.getOrNull(option.id - 1) ?: "Unknown"
}

