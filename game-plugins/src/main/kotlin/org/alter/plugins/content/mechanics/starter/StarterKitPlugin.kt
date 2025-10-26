package org.alter.plugins.content.mechanics.starter

import org.alter.api.ext.*
import org.alter.game.*
import org.alter.game.model.*
import org.alter.game.model.attr.NEW_ACCOUNT_ATTR
import org.alter.game.plugin.*
import org.alter.rscm.RSCM.getRSCM

class StarterKitPlugin(
    r: PluginRepository,
    world: World,
    server: Server
) : KotlinPlugin(r, world, server) {
        
    init {
        onLogin {
            if (player.attr[NEW_ACCOUNT_ATTR] ?: return@onLogin) {
                with(player.inventory) {
                    add(getRSCM("items.logs"), 5)
                    add(getRSCM("items.tinderbox"))
                    add(getRSCM("items.bread"), 5)
                    add(getRSCM("items.bronze_pickaxe"))
                    add(getRSCM("items.bronze_dagger"))
                    add(getRSCM("items.knife"))
                }
            }
        }

    }
}
