package org.alter.plugins.content.commands.commands.developer

import org.alter.api.ext.player
import org.alter.api.ext.setVarbit
import org.alter.game.Server
import org.alter.game.model.World
import org.alter.game.model.priv.Privilege
import org.alter.game.plugin.KotlinPlugin
import org.alter.game.plugin.PluginRepository
import org.alter.plugins.content.interfaces.bank.config.Varbits

class EmptybankPlugin(
    r: PluginRepository,
    world: World,
    server: Server
) : KotlinPlugin(r, world, server) {
        
    init {
        onCommand("emptybank", Privilege.DEV_POWER, description = "Empty your bank") {
            player.bank.removeAll()
            for (i in 1..9) {
                player.setVarbit(Varbits.TAB_DISPLAY + i, 0)
            }
        }
    }
}
