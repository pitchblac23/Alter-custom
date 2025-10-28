package org.alter.game.pluginnew.event.impl

import io.github.oshai.kotlinlogging.KotlinLogging
import net.rsprot.protocol.util.CombinedId
import org.alter.game.model.entity.Player
import org.alter.game.pluginnew.MenuOption
import org.alter.game.pluginnew.event.PlayerEvent
import org.alter.rscm.RSCM
import org.alter.rscm.RSCM.asRSCM

enum class ContainerType(val id: String) {
    INVENTORY("interfaces.inventory"),
    WORN_EQUIPMENT("interfaces.wornitems"),
    EQUIPMENT("interfaces.equipment"),
    SHOP("interfaces.shopmain");

    companion object {
        val logger = KotlinLogging.logger {}

        fun fromId(id: Int): ContainerType? {
            val entry = entries.find { it.id.asRSCM() == id }
            return entry ?: run {
                val reverseName = RSCM.getReverseMapping("interfaces", id)
                logger.info { "Missing mapping for id=$id (reverse: $reverseName) in ContainerType." }
                null
            }
        }
    }
}

data class ButtonClickEvent(
    val component: CombinedId,
    val option: Int,
    val item: Int,
    val slot: Int,
    override val player: Player
) : PlayerEvent(player) {

    init {
        if (item != -1) {
            val containerType = ContainerType.fromId(component.interfaceId)
            val option = MenuOption.fromId(option)
            if (containerType != null) {
                ItemClickEvent(item, slot, option, containerType, player).post()
            }
        }
    }
}

