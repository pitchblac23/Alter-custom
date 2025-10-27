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
                    add(getRSCM("items.bronze_axe"))
                    add(getRSCM("items.bronze_pickaxe"))
                    add(getRSCM("items.tinderbox"))
                    add(getRSCM("items.net"))
                    add(getRSCM("items.shrimp"))
                    add(getRSCM("items.bronze_dagger"))
                    add(getRSCM("items.bronze_sword"))
                    add(getRSCM("items.wooden_shield"))
                    add(getRSCM("items.shortbow"))
                    add(getRSCM("items.bronze_arrow"), 25)
                    add(getRSCM("items.airrune"), 25)
                    add(getRSCM("items.mindrune"), 15)
                    add(getRSCM("items.bucket_empty"))
                    add(getRSCM("items.pot_empty"))
                    add(getRSCM("items.bread"))
                    add(getRSCM("items.waterrune"), 6)
                    add(getRSCM("items.earthrune"), 4)
                    add(getRSCM("items.bodyrune"), 2)
                    add(getRSCM("items.coins"), 25)
                }
            }
        }
    }
}
