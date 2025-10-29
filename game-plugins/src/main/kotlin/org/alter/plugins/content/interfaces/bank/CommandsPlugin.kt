package org.alter.plugins.content.interfaces.bank

import org.alter.api.ext.player
import org.alter.api.ext.setVarbit
import org.alter.game.Server
import org.alter.game.model.World
import org.alter.game.model.priv.Privilege
import org.alter.game.plugin.KotlinPlugin
import org.alter.game.plugin.PluginRepository
import org.alter.plugins.content.interfaces.bank.config.Varbits

class CommandsPlugin(
    r: PluginRepository,
    world: World,
    server: Server
) : KotlinPlugin(r, world, server) {
        
    init {
        onCommand("obank", Privilege.ADMIN_POWER) {
            player.openBank()
        }

        /**
         * Clears all bank tab varbits for the player, effectively
         * dumping all items back into the one main tab.
         */
        onCommand("tabreset") {
            for (tab in 1..9)
                player.setVarbit(Varbits.TAB_DISPLAY + tab, 0)
            player.setVarbit(Varbits.CURRENTTAB, 0)
        }
    }
}
