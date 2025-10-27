package org.alter.plugins.content.mechanics.run

import org.alter.api.ext.player
import org.alter.game.Server
import org.alter.game.model.World
import org.alter.game.plugin.KotlinPlugin
import org.alter.game.plugin.PluginRepository

class RunEnergyPlugin(r: PluginRepository, world: World, server: Server) : KotlinPlugin(r, world, server) {
        
    init {
        onLogin {
            player.timers[RunEnergy.RUN_DRAIN] = 1
        }

        onTimer(RunEnergy.RUN_DRAIN) {
            player.timers[RunEnergy.RUN_DRAIN] = 1
            RunEnergy.drain(player)
        }

        /**
         * Button by minimap.
         */
        onButton(interfaceId = 160, component = 28) {
            RunEnergy.toggle(player)
        }

        /**
         * Settings button.
         */
        onButton(interfaceId = 116, component = 71) {
            RunEnergy.toggle(player)
        }
    }
}
