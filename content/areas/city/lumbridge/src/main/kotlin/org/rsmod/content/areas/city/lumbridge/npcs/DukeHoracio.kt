package org.rsmod.content.areas.city.lumbridge.npcs

import jakarta.inject.Inject
import org.rsmod.api.player.dialogue.Dialogue
import org.rsmod.api.player.output.mes
import org.rsmod.api.player.protect.ProtectedAccess
import org.rsmod.api.script.onOpNpc1
import org.rsmod.content.quest.area.lumbridge.RuneMysteries
import org.rsmod.game.entity.Npc
import org.rsmod.plugin.scripts.PluginScript
import org.rsmod.plugin.scripts.ScriptContext

//TODO: -> Other dialogue
//TODO: -> Make a bank check for item talisman

class DukeHoracio @Inject constructor(private val runeMyst: RuneMysteries) : PluginScript() {
    private val airTalisman = "obj.air_talisman"

    override fun ScriptContext.startup() {
        onOpNpc1("npc.duke_of_lumbridge") { startDukesDialogue(it.npc) }
    }

    private suspend fun ProtectedAccess.startDukesDialogue(npc: Npc) {
        startDialogue(npc) { dukeHoracio() }
    }

    private suspend fun Dialogue.dukeHoracio() {
        when (runeMyst.quest.getQuestStage(player)) {
            1 -> duringRuneMysteries()
            else -> startDukeHoracio()
        }
    }

    private suspend fun Dialogue.startDukeHoracio() {
        chatNpc(neutral, "Greetings. Welcome to my castle.")

        when (choice2(
            "Have you any quests for me?", 1,
            "Where can I find Money?", 2
        )) {
            1 -> {
                chatPlayer(quiz, "Have you any quests for me?")
                if (runeMyst.quest.getQuestStage(player) <= 0) {
                    runeMysteries()
                } else {
                    chatNpc(neutral,
                        "The only job I had was the delivery of that talisman, so " +
                            "I'm afraid not.")
                }
            }
            2 -> findMoney()
        }
    }

    private suspend fun Dialogue.runeMysteries() {
        chatNpc(confused,
            "Well, I wouldn't describe it as a quest, but there is " +
                "something I could use some help with.")
        chatPlayer(quiz, "What is it?")
        chatNpc(neutral,
            "We were recently sorting through some of the things " +
                "stored down in the cellar, and we found this old " +
                "talisman.")
        objbox(airTalisman, "The Duke shows you a talisman.")
        chatNpc(neutral,
            "The Order of Wizards over at the Wizards' Tower " +
                "have been on the hunt for magical artefacts recently. I " +
                "wonder if this might be just the kind of thing they're " +
                "after.")
        chatNpc(quiz, "Would you be willing to take it to them for me?")

        when (choice2(
            "Yes.", 1,
            "No.", 2
        )) {
            1 -> startRuneMysteries()
            2 -> {
                chatPlayer(neutral, "Not right now.")
                chatNpc(sad, "As you wish. Hopefully I can find someone else to help.")
            }
        }
    }

    private suspend fun Dialogue.startRuneMysteries() {
        chatPlayer(happy, "Sure, no problem.")
        runeMyst.quest.advanceQuestStage(access)
        player.mes("You've started a new quest: <col=0000ff>Rune Mysteries</col>")
        chatNpc(happy,
            "Thank you very much. You'll find the Wizards' Tower "+
                "south west of here, across the bridge from Draynor " +
                "Village. when you arrive, look for Sedridor. He is the " +
                "Archmage of the wizards there.")
        hasInventorySpace()
    }

    private suspend fun Dialogue.duringRuneMysteries() {
        when (choice2(
            "What did you want me to do again?", 1,
            "Where can I find Money?", 2
        )) {
            1 -> {
                chatPlayer(neutral, "What did you want me to do again?")

                if (airTalisman !in player.inv) {
                    chatNpc(quiz, "Did you take that talisman to Sedridor?")
                    chatPlayer(sad, "No I lost it.")
                    chatNpc(neutral,
                        "Ah, well that explains things. One of my servants found " +
                            "it outside, and it seemed to much of a coincidence that " +
                            "another would suddenly show up.")
                    chatNpc(neutral,
                        "Here, take it to the Wizards' Tower, south west of here. " +
                            "Please try not to lose it this time.")
                    hasInventorySpace()
                } else {
                    chatNpc(neutral,
                        "Take that talisman I gave you to Sedridor at the " +
                            "Wizards' Tower. You'll find it south west of here, " +
                            "across the bridge from Draynor Village.")
                    chatPlayer(happy, "Okay, will do.")
                }
            }
            2 -> findMoney()
        }
    }

    private suspend fun Dialogue.hasInventorySpace() {
        if (access.invAdd(access.inv, airTalisman).success) {
            vars["varbit.runemysteries_talisman"] = 1
            objbox(airTalisman, "The Duke hands you the talisman.")
        } else {
            objbox(airTalisman,
                "The Duke tries to hand you the talisman, but you don't " +
                    "have enough room to take it.")
        }
    }

    private suspend fun Dialogue.findMoney() {
        chatPlayer(quiz, "Where can I find money?")
        chatNpc(neutral,
            "I've heard that the blacksmiths are prosperous amongst " +
                "the peasantry. Maybe you could try your hand at " +
                "that?")
    }
}
