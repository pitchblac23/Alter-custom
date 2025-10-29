package org.alter.plugins.content.mechanics.run

import org.alter.api.cfg.Sound
import org.alter.api.ext.playSound
import org.alter.api.ext.player
import org.alter.game.Server
import org.alter.game.model.World
import org.alter.game.plugin.KotlinPlugin
import org.alter.game.plugin.PluginRepository
import org.alter.plugins.content.interfaces.gameframe.config.Orbs

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
        onButton(interfaceId = Orbs.ORBS_UNIVERSE, component = Orbs.RUN_ORB) {
            RunEnergy.toggle(player)
            player.playSound(Sound.INTERFACE_SELECT1)
        }

        /**
         * Settings button.
         */
        onButton(interfaceId = 116, component = 71) {
            RunEnergy.toggle(player)
        }
    }
}
