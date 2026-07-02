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

// TODO -> dialogue for quest and onOpNpc teleport

class Aubury @Inject constructor(private val shops: Shops) : PluginScript() {
    override fun ScriptContext.startup() {
        onOpNpc1("npc.aubury") { shopDialogue(it.npc) }
        onOpNpc3("npc.aubury") { player.openGeneralStore(it.npc) }
    }

    private fun Player.openGeneralStore(npc: Npc) {
        shops.open(this, npc, "Aubury's Rune Shop", "inv.runeshop")
    }

    private suspend fun ProtectedAccess.shopDialogue(npc: Npc) =
        startDialogue(npc) { shopKeeper(npc) }

    private suspend fun Dialogue.shopKeeper(npc: Npc) {
        chatNpc(happy, "Do you want to buy some runes?")

        val choice = choice3(
            "Yes please!", 1,
            "Can you tell me about your cape?", 2,
            "Oh, it's a rune shop. No thank you, then.", 3)

        when (choice) {
            1 -> {
                chatPlayer(happy, "Yes please!")
                player.openGeneralStore(npc)
            }
            2 -> runecraftingCape(npc)
            3 -> {
                chatPlayer(neutral, "Oh, it's a rune shop. No thank you, then.")
                chatNpc(happy, "Well, if you find someone who does want runes, please send them my way.")
            }
        }
    }

    private suspend fun Dialogue.runecraftingCape(npc: Npc) {
        chatPlayer(quiz, "Can you tell me about your cape?")
        chatNpc(shocked,
            "Certainly! Skillcapes are a symbol of achievement. Only " +
                "people who have mastered a skill and reached level 99 " +
                "can get their hands on them and gain the benefits they carry.")

        chatNpc(neutral,
            "The Cape of Runecrafting has been upgraded with each " +
                "talisman, allowing you all Runecrafting altars. Is there " +
                "anything else I can help you with?")

        val choice = choice2(
            "I'd like to view your store please.", 1,
            "No thank you.", 2)

        when (choice) {
            1 -> {
                chatPlayer(happy, "I'd like to view your store please.")
                player.openGeneralStore(npc)
            }
            2 -> {
                chatPlayer(neutral, "No thank you.")
                chatNpc(happy, "Well, if you find someone who does want runes, please send them my way.")
            }
        }
    }
}
