package org.alter.plugins.content.objects.ladder

import org.alter.api.ext.options
import org.alter.api.ext.player
import org.alter.game.Server
import org.alter.game.model.World
import org.alter.game.model.entity.Player
import org.alter.game.model.move.moveTo
import org.alter.game.plugin.KotlinPlugin
import org.alter.game.plugin.PluginRepository

class LadderPlugin(r: PluginRepository, world: World, server: Server) : KotlinPlugin(r, world, server) {

    init {
        /**Stairs*/

        val stairs =
            arrayOf(
                "objects.spiralstairsmiddle",
                "objects.spiralstairstop",
                "objects.spiralstairstop_3",
                "objects.spiralstairs",
                "objects.spiralstairsbottom_3",
            )

        stairs.forEach { stairs ->
            if (objHasOption(obj = stairs, option = "climb")) {
                onObjOption(obj = stairs, option = "climb") {
                    climbstairs(player)
                }
            }
            if (objHasOption(obj = stairs, option = "climb-up")) {
                onObjOption(obj = stairs, option = "climb-up") {
                    climbupstairs(player)
                }
            }
            if (objHasOption(obj = stairs, option = "climb-down")) {
                onObjOption(obj = stairs, option = "climb-down") {
                    climbdownstairs(player)
                }
            }
            if (objHasOption(obj = stairs, option = "Top-floor")) {
                onObjOption(obj = stairs, option = "Top-floor") {
                    topfloorstairs(player)
                }
            }
            if (objHasOption(obj = stairs, option = "Bottom-floor")) {
                onObjOption(obj = stairs, option = "Bottom-floor") {
                    bottomfloorstairs(player)
                }
            }
        }

        /**Ladders*/

        val ladders =
            arrayOf(
                "objects.qip_cook_ladder",
                "objects.qip_cook_ladder_middle",
                "objects.ladder",
                "objects.qip_cook_ladder_top",
                "objects.laddertop",
                "objects.laddermiddle",
            )

        ladders.forEach { ladder ->
            if (objHasOption(obj = ladder, option = "climb")) {
                onObjOption(obj = ladder, option = "climb") {
                    climbladder(player)
                }
            }
            if (objHasOption(obj = ladder, option = "climb-up")) {
                onObjOption(obj = ladder, option = "climb-up") {
                    climbupladder(player)
                }
            }
            if (objHasOption(obj = ladder, option = "climb-down")) {
                onObjOption(obj = ladder, option = "climb-down") {
                    climbdownladder(player)
                }
            }
        }

        /**Trapdoors.*/

        onObjOption("objects.qip_cook_trapdoor_open", option = "climb-down") {
            player.moveTo(3210, 9616, 0)
        }
        onObjOption("objects.ladder_from_cellar", option = "climb-up") {
            player.moveTo(3210, 3216, 0)
        }
    }

    /**Function for ladders.*/

    fun climbupladder(player: Player) {
        player.queue {
            player.animate(828)
            player.lock()
            wait(2)
            player.moveTo(player.tile.x, player.tile.z, player.tile.height + 1)
            player.unlock()
        }
    }

    fun climbdownladder(player: Player) {
        player.queue {
            player.animate(828)
            player.lock()
            wait(2)
            player.moveTo(player.tile.x, player.tile.z, player.tile.height - 1)
            player.unlock()
        }
    }

    fun climbladder(player: Player) {
        player.queue {
            when (options(player, "Climb up the ladder.", "Climb down the ladder")) {
                1 -> climbupladder(player)
                2 -> climbdownladder(player)
            }
        }
    }

    /**Function for stairs.*/

    fun climbupstairs(player: Player) {
        player.moveTo(player.tile.x, player.tile.z, player.tile.height + 1)
    }

    fun climbdownstairs(player: Player) {
        player.moveTo(player.tile.x, player.tile.z, player.tile.height - 1)
    }
    fun topfloorstairs(player: Player) {
        player.moveTo(player.tile.x, player.tile.z, 2)
    }
    fun bottomfloorstairs(player: Player) {
        player.moveTo(player.tile.x, player.tile.z, 0)
    }

    fun climbstairs(player: Player) {
        player.queue {
            when (options(player, "Climb up the stairs.", "Climb down the stairs.")) {
                1 -> climbupstairs(player)
                2 -> climbdownstairs(player)
            }
        }
    }
}
