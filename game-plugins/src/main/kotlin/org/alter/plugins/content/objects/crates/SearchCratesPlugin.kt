package org.alter.plugins.content.objects.crates

import org.alter.api.*
import org.alter.api.cfg.*
import org.alter.api.dsl.*
import org.alter.api.ext.*
import org.alter.game.*
import org.alter.game.model.*
import org.alter.game.model.attr.*
import org.alter.game.model.container.*
import org.alter.game.model.container.key.*
import org.alter.game.model.entity.*
import org.alter.game.model.item.*
import org.alter.game.model.queue.*
import org.alter.game.model.shop.*
import org.alter.game.model.timer.*
import org.alter.game.plugin.*

class SearchCratesPlugin(
    r: PluginRepository,
    world: World,
    server: Server
) : KotlinPlugin(r, world, server) {
        
    init {
        val CRATES =
            setOf(
                "objects.crate2",
                "objects.crate3",
                "objects.crate_old",
                "objects.crate2_old",
                "objects.crate3_old",
                "objects.crate",
                "objects.golrie_crate_waterfall_quest",
                "objects.baxtorian_crate_waterfall_quest",
                "objects.mournercrateup",
            )

        CRATES.forEach { crate ->
            onObjOption(obj = crate, option = "search") {
                player.message("You search the crate but find nothing.")
            }
        }
    }
}
