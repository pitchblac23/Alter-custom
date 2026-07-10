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

class Thessalia @Inject constructor(private val shops: Shops) : PluginScript() {
    override fun ScriptContext.startup() {
        onOpNpc1("npc.thessalia") { shopDialogue(it.npc) }
        onOpNpc3("npc.thessalia") { player.openGeneralStore(it.npc) }
    }

    private fun Player.openGeneralStore(npc: Npc) {
        shops.open(this, npc, "Thessalia's Fine Clothes.", "inv.clotheshop")
    }

    private suspend fun ProtectedAccess.shopDialogue(npc: Npc) =
        startDialogue(npc) { shopKeeper(npc) }

    private suspend fun Dialogue.shopKeeper(npc: Npc) {
        chatNpc(happy, "Do you want to buy any fine clothes?")

        val choice = choice2(
            "What have you got?", 1,
            "No, thank you.", 2)

        when (choice) {
            1 -> clothingSelection(npc)
            2 -> noThanks()
        }
    }

    private suspend fun Dialogue.clothingSelection(npc: Npc) {
        chatPlayer(quiz,"What have you got?")
        chatNpc(happy,
            "Well, I have a number of fine pieces of clothing on sale " +
                "or, if you prefer, I can offer you an exclusive total-" +
                "clothing makeover?")

        val choice = choice3(
            "Tell me more about this makeover.", 1,
            "I'd just like to buy some clothes.", 2,
            "No, thank you.", 3)

        when (choice) {
            1 -> makeover(npc)
            2 -> player.openGeneralStore(npc)
            3 -> noThanks()
        }
    }

    private suspend fun Dialogue.makeover(npc: Npc) {
        chatPlayer(quiz,"Tell me more about this makeover.")
        chatNpc(happy, "Certainly!")
        chatNpc(happy,
            "Here at Thessalia's fine clothing boutique, we offer a " +
                "unique service where we will totally revamp your outfit " +
                "to your choosing")

        chatNpc(neutral,
            "It's on the house, completely free! Tired of always " +
                "wearing the same old outfit, day in, day out? This is " +
                "the service for you!")

        chatNpc(quiz,
            "So what do you say? Interested? We can change either " +
                "your top, your bottoms or your wristwear!")

        //TODO Setup interface for customizing char
        val choice = choice5(
            "I'd like to change my top please.", 1,
            "I'd like to change my bottoms please.", 2,
            "I'd like to buy some clothes.", 3,
            "I'd like to change my wristwear.", 4,
            "No, thank you.", 5)

        when (choice) {
            1 -> {
                player.openGeneralStore(npc)
            }
            2 -> player.openGeneralStore(npc)
            3 -> player.openGeneralStore(npc)
            4 -> player.openGeneralStore(npc)
            5 -> noThanks()
        }
    }

    private suspend fun Dialogue.noThanks() {
        chatPlayer(neutral, "No, thank you.")
        chatNpc(happy, "Well, please return if you change your mind.")
    }
}
