package org.rsmod.content.areas.city.varrock.npcs

import jakarta.inject.Inject
import org.rsmod.api.player.dialogue.Dialogue
import org.rsmod.api.player.protect.ProtectedAccess
import org.rsmod.api.script.onOpNpc1
import org.rsmod.api.script.onOpNpc3
import org.rsmod.api.shops.Shops
import org.rsmod.game.entity.Npc
import org.rsmod.game.entity.Player
import org.rsmod.plugin.scripts.PluginScript
import org.rsmod.plugin.scripts.ScriptContext

class Horvik @Inject constructor(private val shops: Shops) : PluginScript() {
    override fun ScriptContext.startup() {
        onOpNpc1("npc.horvik_the_armourer") { shopDialogue(it.npc) }
        onOpNpc3("npc.horvik_the_armourer") { player.openGeneralStore(it.npc) }
    }

    private fun Player.openGeneralStore(npc: Npc) {
        shops.open(this, npc, "Horvik's Armour Shop.", "inv.armourshop")
    }

    private suspend fun ProtectedAccess.shopDialogue(npc: Npc) =
        startDialogue(npc) { shopKeeper(npc) }

    private suspend fun Dialogue.shopKeeper(npc: Npc) {
        chatNpc(happy, "Hello, do you need any help?")

        when (choice2("Do you want to trade?", 1, "No thanks. I'm just looking around.", 2)) {
            1 -> player.openGeneralStore(npc)
            2 -> {
                chatPlayer(happy, "No thanks. I'm just looking around.")
                chatNpc(happy, "Well, come and see me if you're ever in need of armor!")
            }
        }
    }
}
