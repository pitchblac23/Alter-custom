package org.rsmod.content.areas.city.varrock.npcs

import jakarta.inject.Inject
import org.rsmod.api.config.constants
import org.rsmod.api.player.dialogue.Dialogue
import org.rsmod.api.player.output.UpdateRun
import org.rsmod.api.player.output.soundSynth
import org.rsmod.api.player.protect.ProtectedAccess
import org.rsmod.api.script.onOpNpc1
import org.rsmod.api.script.onOpNpc3
import org.rsmod.api.script.onOpNpc4
import org.rsmod.api.shops.Shops
import org.rsmod.content.quest.area.lumbridge.RuneMysteries
import org.rsmod.content.skills.runecrafting.essence.teleportToRuneEssenceMine
import org.rsmod.game.entity.Npc
import org.rsmod.game.entity.Player
import org.rsmod.plugin.scripts.PluginScript
import org.rsmod.plugin.scripts.ScriptContext

// TODO:
//  Other dialogue

class Aubury @Inject constructor(private val shops: Shops, private val runeMyst: RuneMysteries)
    : PluginScript() {

    private val researchPackage = "obj.research_package"
    private val researchNotes = "obj.research_notes"

    override fun ScriptContext.startup() {
        onOpNpc1("npc.aubury") { shopDialogue(it.npc) }
        onOpNpc3("npc.aubury") { player.openGeneralStore(it.npc) }
        onOpNpc4("npc.aubury") {
            vars["varbit.essencemine_portal"] = 1
            teleportToRuneEssenceMine(it.npc)
        }
    }

    private fun Player.openGeneralStore(npc: Npc) {
        shops.open(this, npc, "Aubury's Rune Shop", "inv.runeshop")
    }

    private suspend fun ProtectedAccess.shopDialogue(npc: Npc) =
        startDialogue(npc) { aubury(npc) }

    private suspend fun Dialogue.aubury(npc: Npc) {
        val questStage = runeMyst.quest.getQuestStage(player)

        when (questStage) {
            in 0..4 -> startingQuestStage(npc, questStage)
            5 -> stageNotes(npc)
            6 -> postQuestStage(npc)
        }
    }

    private suspend fun Dialogue.startingQuestStage(npc: Npc, questStage: Int) {
        chatNpc(happy, "Do you want to buy some runes?")

        when (questStage) {
            in 0..2 -> shopKeeper(npc)
            3 -> duringRuneMysteries(npc)
            4 -> progressRuneMysteries(npc)
        }
    }

    private suspend fun Dialogue.stageNotes(npc: Npc) {
        if (player.vars["varbit.runemysteries_notes"] == 0) {
            chatNpc(quiz,
                "Here, take these notes back to Sedridor. They should " +
                    "hopefully give him everything he needs."
            )
            chatPlayer(happy, "Okay.")
            hasInventorySpace()
            cupOfTea()
        } else {
            deliverNotes(npc)
        }
    }

    private suspend fun Dialogue.postQuestStage(npc: Npc) {
        chatNpc(happy, "Do you want to buy some runes?")

        val choice = choice4(
            "Yes please!", 1,
            "Can you tell me about your cape?", 2,
            "Can you teleport me to the Rune Essence Mine?", 3,
            "No thank you.", 4
        )

        when (choice) {
            1 -> yesPlease(npc)
            2 -> runecraftingCape(npc)
            3 -> {
                chatPlayer(quiz, "Can you teleport me to the Rune Essence Mine?")
                chatNpc(happy,
                    "Of course. By the way, if you end up making any " +
                        "runes from the essence you mine, I'll happily buy them " +
                        "from you."
                )
                vars["varbit.essencemine_portal"] = 1
                teleportToRuneEssenceMine(npc)
            }
            4 -> noThankYou()
        }
    }

    private suspend fun Dialogue.duringRuneMysteries(npc: Npc) {
        val choice = choice3(
            "I've been sent here with a package for you.", 1,
            "Yes please!", 2,
            "Oh, it's a rune shop. No thank you, then.", 3
        )

        when (choice) {
            1 -> packageForYou()
            2 -> yesPlease(npc)
            3 -> runeShopNoThankYou()
        }
    }

    private suspend fun Dialogue.progressRuneMysteries(npc: Npc) {
        val choice = choice3(
            "Anything useful in that package I gave you?", 1,
            "Yes please!", 2,
            "No, thank you.", 3
        )

        when (choice) {
            1 -> {
                chatPlayer(quiz, "Anything useful in that package I gave you?")
                chatNpc(happy, "Well, let's have a look...")
                gavePackage()
            }
            2 -> yesPlease(npc)
            3 -> noThankYou()
        }
    }

    private suspend fun Dialogue.shopKeeper(npc: Npc) {
        val choice = choice3(
            "Yes please!", 1,
            "Can you tell me about your cape?", 2,
            "Oh, it's a rune shop. No thank you, then.", 3
        )

        when (choice) {
            1 -> yesPlease(npc)
            2 -> runecraftingCape(npc)
            3 -> runeShopNoThankYou()
        }
    }

    private suspend fun Dialogue.packageForYou() {
        chatPlayer(neutral, "I've been sent here with a package for you.")
        chatNpc(confused, "A package? From who?")
        chatPlayer(neutral, "From Sedridor at the Wizards' Tower.")
        chatNpc(shocked,
            "From Sedridor? But... surely, he can't have? Please, let " +
                "me have it. It must be extremely important for him to " +
                "have sent a stranger."
        )

        if (researchPackage in player.inv) {
            access.invDel(player.inv, researchPackage)
            runeMyst.quest.advanceQuestStage(access)
            objbox(researchPackage, "You hand the package to Aubury")
            chatNpc(happy, "Now, let's have a look...")
            gavePackage()
        } else {
            chatPlayer(confused,
                "Uh... yeah... about that... I kind of don't have it with " +
                    "me...")
            chatNpc(confused,
                "What kind of person says they have a delivery for me, " +
                    "but not with them? Honestly.")
            chatNpc(neutral, "Come back when you have it.")
        }
    }

    private suspend fun Dialogue.gavePackage() {
        objbox(researchPackage, "Aubury goes through the package of research notes.")
        chatNpc(shocked, "This... this is incredible.")
        chatNpc(happy,
            "My gratitude to you adventurer for bringing me these " +
                "research notes. Thanks to you, I think we finally have " +
                "it.")
        chatPlayer(quiz, "You mean the incantation?")
        chatNpc(happy,
            "Well when we combine my own research with this latest " +
                "discovery, I think we might just...")
        chatNpc(neutral,
            "No, no, I'm getting ahead of myself. The signs are " +
                "promising, but let's not jump to any conclusions just " +
                "yet.")
        runeMyst.quest.advanceQuestStage(access)
        chatNpc(quiz,
            "Here, take these notes back to Sedridor. They should " +
                "hopefully give him everything he needs.")
        hasInventorySpace()
        cupOfTea()
    }

    private suspend fun Dialogue.hasInventorySpace() {
        if (access.invAdd(player.inv, researchNotes).success) {
            vars["varbit.runemysteries_notes"] = 1
            objbox(researchNotes, "Aubury hands you some research notes.")
        } else {
            objbox(researchNotes,
                "Aubury tried to hand you some research notes, but you " +
                    "don't have enough room to take them.")
        }
    }

    private suspend fun Dialogue.cupOfTea() {
        chatNpc(happy, "Before you leave, why not have a cup of tea?")

        val choice = choice2(
            "I'd love a cup of tea.", 1,
            "No, thank you.", 2
        )

        when (choice) {
            1 -> {
                chatPlayer(happy, "I'd love a cup of tea.")
                player.anim("seq.human_eat")
                player.soundSynth("synth.drinking_potion")
                player.runEnergy = constants.run_max_energy
                UpdateRun.energy(player, 10_000)
                player.say("Aaah, nothing like a nice cuppa tea!")
            }
            2 -> chatPlayer(neutral, "No, thank you.")
        }
    }

    private suspend fun Dialogue.deliverNotes(npc: Npc) {
        chatNpc(quiz, "Hello. Did you take those notes back to Sedridor?")

        if (researchNotes in player.inv) {
            chatPlayer(neutral, "I'm still working on it.")
            chatNpc(happy,
                "Don't take too long. He'll be eager to see if this is " +
                    "indeed the breakthrough we were hoping for. Now, did " +
                    "you want to buy some runes?"
            )

            val choice = choice2(
                "Yes please!", 1,
                "No, thank you.", 2
            )

            when (choice) {
                1 -> yesPlease(npc)
                2 -> noThankYou()
            }
        } else {
            chatPlayer(sad, "I don't have them.")
            chatNpc(neutral,
                "Well, luckily I have duplicates. It's a good thing they " +
                    "are written in code. I wouldn't want the wrong kind of " +
                    "person to get access to the information contained within.")
            hasInventorySpace()
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
            "No thank you.", 2
        )

        when (choice) {
            1 -> {
                chatPlayer(happy, "I'd like to view your store please.")
                player.openGeneralStore(npc)
            }
            2 -> noThankYou()
        }
    }

    private suspend fun Dialogue.yesPlease(npc: Npc) {
        chatPlayer(happy, "Yes please!")
        player.openGeneralStore(npc)
    }

    private suspend fun Dialogue.runeShopNoThankYou() {
        chatPlayer(neutral, "Oh, it's a rune shop. No thank you, then.")
        chatNpc(happy, "Well, if you find someone who does want runes, please send them my way.")
    }

    private suspend fun Dialogue.noThankYou() {
        chatPlayer(neutral, "No thank you.")
        chatNpc(happy, "Well, if you find someone who does want runes, please send them my way.")
    }
}
