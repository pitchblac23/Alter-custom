package org.rsmod.content.areas.city.draynor.npcs.wizardstower

import jakarta.inject.Inject
import org.rsmod.api.invtx.invDel
import org.rsmod.api.player.dialogue.Dialogue
import org.rsmod.api.player.output.mes
import org.rsmod.api.player.output.soundSynth
import org.rsmod.api.player.protect.ProtectedAccess
import org.rsmod.api.script.onOpNpc1
import org.rsmod.api.script.onOpNpc3
import org.rsmod.content.quest.area.lumbridge.RuneMysteries
import org.rsmod.content.skills.runecrafting.essence.teleportToRuneEssenceMine
import org.rsmod.game.entity.Npc
import org.rsmod.plugin.scripts.PluginScript
import org.rsmod.plugin.scripts.ScriptContext

//TODO:
// Need to get a ifOverLayClose after finishing quest

class Sedridor @Inject constructor(private val runeMyst: RuneMysteries) : PluginScript() {
    private val airTalisman = "obj.air_talisman"
    private val researchPackage = "obj.research_package"
    private val researchNotes = "obj.research_notes"

    override fun ScriptContext.startup() {
        onOpNpc1("npc.head_wizard") { sedridor(it.npc) }
        onOpNpc3("npc.head_wizard") { teleportToRuneEssenceMine(it.npc) }
    }

    private suspend fun ProtectedAccess.sedridor(npc: Npc) {
        startDialogue(npc) { sedridorDialogue(npc) }
    }

    private suspend fun Dialogue.sedridorDialogue(npc: Npc) {
        val questStage = runeMyst.quest.getQuestStage(player)

        when (questStage) {
            1 -> {
                if (player.vars["varbit.runemysteries_talisman"] == 0) {
                    welcomeBack()
                } else {
                    duringRuneMysteries()
                }
            }
            2 -> {
                if (player.vars["varbit.runemysteries_backstory"] == 0) {
                    chatPlayer(quiz, "So is that talisman of any use to you?")
                    hereYouAre()
                } else {
                    chatNpc(quiz,
                        "Hello again, adventurer. You have already done so " +
                            "much, but I would really appreciate it if you were to " +
                            "visit my associate, Aubury. Would you be willing to?")
                    willingToGo()
                }
            }
            3 -> {
                if (player.vars["varbit.runemysteries_package"] == 0) {
                    chatNpc(happy,
                        "Hello again, adventurer. Please, take this package of " +
                            "research notes to Aubury in Varrock. He runs a rune " +
                            "shop in the south east of the city.")
                    hasInventorySpacePackage()
                } else {
                    researchPackage()
                }
            }
            4 -> deliveredPackage()
            5 -> {
                if (player.vars["varbit.runemysteries_notes_given"] == 0) {
                    researchNotes(npc)
                } else {
                    handedNotes(npc)
                }
            }
            6 -> {
                chatPlayer(neutral, "Hello there.")

                if (player.vars["varbit.runemysteries_owed_talisman"] == 0) {
                    chatNpc(happy,
                        "Hello again, ${player.displayName}. I have that air talisman for " +
                            "you."
                    )
                    hasInventorySpaceTalisman(npc)
                } else {
                    chatNpc(happy, "Hello again, ${player.displayName}. What can I do for you?")
                    afterRuneMysteries(npc)
                }
            }
            else -> startSedridor()
        }
    }

    private suspend fun Dialogue.startSedridor() {
        chatNpc(happy,
            "Welcome adventurer, to the world renowned Wizards' " +
                  "Tower, home to the Order of Wizards. How may I help " +
                  "you?")
        chatPlayer(neutral, "I'm just looking around.")
        chatNpc(confused,
            "Well take care adventurer. You stand on the ruins of " +
                  "the old destroyed Wizards' Tower. Strange and " +
                  "powerful magicks lurk here.")
    }

    private suspend fun Dialogue.duringRuneMysteries() {
        chatNpc(happy,
            "Welcome adventurer, to the world renowned Wizards' " +
                  "Tower, home to the Order of Wizards. We are the " +
                  "oldest and most prestigious group of wizards around. " +
                  "Now, how may I help you?")
        chatPlayer(quiz, "Are you Sedridor?")
        chatNpc(quiz, "Sedridor? What is it you want with him?")
        chatPlayer(neutral,
            "The Duke of Lumbridge sent me to find him. I have " +
                  "this Talisman he found. He said Sedridor would be " +
                  "interested in it.")
        chatNpc(neutral,
            "Did he now? Well hand it over then, and we'll see what " +
                  "all the hubbub is about.")

        when (choice2(
            "Okay, here you are.", 1,
            "No, I'll only give it to Sedridor.", 2
        )) {
            1 -> {
                chatPlayer(happy, "Okay, here you are.")
                giveTalisman()
            }
            2 -> notSedridor()
        }
    }

    private suspend fun Dialogue.giveTalisman() {
        if (airTalisman in player.inv) {
            runeMyst.quest.advanceQuestStage(access)
            access.invDel(access.inv, airTalisman)
            vars["varbit.runemysteries_talisman_give"] = 1
            objbox(airTalisman, "You hand the talisman to Sedridor.")
            hereYouAre()
        } else {
            vars["varbit.runemysteries_talisman"] = 0
            chatNpc(silent, "...")
            chatPlayer(silent, "...")
            chatNpc(confused, "Well?")
            chatPlayer(confused, "I don't seem to have it with me.")
            chatNpc(confused,
                "Hmm? You are a very odd person. Come back again " +
                    "when you have it.")
        }
    }

    private suspend fun Dialogue.welcomeBack() {
        chatNpc(quiz,
            "Welcome back, adventurer. Do you have that talisman " +
                "now?")

        if (airTalisman in player.inv) {
            chatPlayer(neutral, "Here you go.")
            giveTalisman()
        } else {
            chatPlayer(confused, "Not yet.")
            chatNpc(confused, "Well come back when you have it.")
        }
    }

    private suspend fun Dialogue.hereYouAre() {
        chatNpc(confused,
            "Hmm... Doesn't seem to be anything too special. Just a " +
                  "normal air talisman by the looks of things. Still, looks " +
                  "can be deceiving. Let me take a closer look...")
        player.soundSynth("synth.enchant_emerald_ring")
        objbox(airTalisman,
            "Sedridor murmurs some sort of incantation and the " +
                  "talisman glows slightly.")
        chatNpc(confused,
            "How interesting... It would appear I spoke too soon. " +
                  "There's more to this talisman then meets the eye. In " +
                  "fact, it may well be the last piece of the puzzle.")
        chatPlayer(quiz, "Puzzle?")
        chatNpc(happy,
            "Indeed! The lost legacy of the first tower. This talisman " +
                  "may in fact be key to finding the forgotten essence " +
                  "mine!")
        chatPlayer(confused,
            "First tower? Forgotten essence mine? What are you on " +
                  "about?")
        chatNpc(happy, "Ah, my apologies, adventurer. Allow me to fill you in.")

        when (choice2(
            "Go ahead.", 1,
            "Actually, I'm not interested.", 2
        )) {
            1 -> goAhead()
            2 -> {
                chatPlayer(neutral, "Actually, I'm not Interested.")
                chatNpc(
                    sad,
                    "Oh... Well I guess the short of it is that this talisman" +
                          " could be the key to helping us rediscover an important" +
                          " teleportation incantation.")
                chatNpc(
                    neutral,
                    "With it, we'll be able to access a hidden essence mine," +
                          " our lost source of rune essence.")
                progressRuneMysteries()
            }
        }
    }

    private suspend fun Dialogue.notSedridor() {
        chatPlayer(confused, "No, I'll only give it to Sedridor.")
        chatNpc(happy,
            "Well good news, for I am Sedridor! Now, hand it over " +
                "and let me have a proper look at it, hmm?")

        when (choice2(
            "Okay, here you are.", 1,
            "No, I don't think you are Sedridor.", 2
        )) {
            1 -> {
                chatPlayer(happy, "Okay, here you are.")
                giveTalisman()
            }
            2 -> {
                vars["varbit.runemysteries_knowname"] = 1

                chatPlayer(confused, "No, I don't think you are Sedridor.")
                chatNpc(quiz,
                    "Hmm... Well, I admire your caution adventurer. " +
                        "Perhaps I can prove myself? I wil use my mental " +
                        "powers to discover...")
                chatNpc(happy, "Your name is... ${player.displayName}!")
                chatPlayer(shocked, "You're right! How did you know that?")
                chatNpc(happy,
                    "Well I am the Archmage you know! You don't get to " +
                        "my position without learning a few tricks along the way!")
                chatNpc(quiz,
                    "So now that I have proved myself to you, why don't " +
                        "you hand over that talisman, hmm?")
                chatPlayer(neutral, "Okay, here you are.")
                giveTalisman()
            }
        }
    }

    private suspend fun Dialogue.goAhead() {
        chatPlayer(neutral, "Go ahead.")
        chatNpc(happy,
            "As you are likely aware, when we cast spells, we do so " +
                "using the power of runes.")
        chatNpc(happy,
            "These runes are crafted from a highly unique material, " +
                "and then imbued with magical power from various runic " +
                "alters. Different alters create different runes with " +
                "different magical effects.")
        chatNpc(happy,
            "The process of imbuing runes is called runecrafting. " +
                "Legend has it that this was once a common art, but the " +
                "secrets of how to do it were lost until just under two " +
                "hundred years ago.")
        chatNpc(happy,
            "The rediscovery of runecrafting had such a large " +
                "impact on the world, that it marked the dawn of the " +
                "Fith Age. It also resulted in the birth of our order, and " +
                "the construction of the first Wizards' Tower.")
        chatPlayer(quiz,
            "If it was the first tower, I'm guessing it doesn't exist " +
                "anymore? What happened?")
        chatNpc(angry,
            "It was burnt down by traitorous member of our own " +
                "order. They followed the evil got of chaos, Zamorak, " +
                "and they wished to claim our magical discoveries in his " +
                "name.")
        chatNpc(sad,
            "When the tower burnt down, much was lost, including " +
                "an important incantation. A spell that could be used to " +
                "teleport to a hidden essence mine.")
        chatPlayer(quiz, "The essence mine you mentioned earlier, I assume?")
        chatNpc(neutral,
            "Precisely. Rune essence is the material used to make " +
                "runes, but it is incredibly rare. That essence mine was " +
                "the only place it could be found that our order knew " +
                "of.")
        chatNpc(sad,
            "Since the incantation was lost, we have struggled to " +
                "maintain our stocks of rune essence.")
        chatNpc(neutral,
            "There are seemingly those out there that still know " +
                "where to find some, but while they have been willing to " +
                "sell essence to us, they have refused to share knowledge " +
                "on how to find it ourselves.")
        chatPlayer(quiz,
            "I'm starting to see why this is so important. So you " +
                "think this talisman can help rediscover that " +
                "incantation?")
        chatNpc(happy,
            "I do! All magic leaves traces, and from what I can tell, " +
                "this talisman was used heavily during the time of the " +
                "first tower.")
        chatNpc(happy,
            "It would have been taken to the essence mine many " +
                "times, any the magical energies there will have left an " +
                "imprint on it. To think that it was hidden in Lumbridge " +
                "all this time!")
        chatPlayer(quiz, "So what happens now?")
        progressRuneMysteries()
    }

    private suspend fun Dialogue.progressRuneMysteries() {
        chatNpc(happy,
            "It is critical I share this discovery with my associate, " +
                "Aubury, as soon as possible. He's not much of a wizard, " +
                "but he's an expert on runecrafting, and his insight will " +
                "be essential.")
        vars["varbit.runemysteries_backstory"] = 1
        chatNpc(quiz,
            "Would you be willing to visit him for me? I would go " +
                "myself, but I wish to study this talisman some more.")
        willingToGo()
    }

    private suspend fun Dialogue.willingToGo() {
        when (choice2(
            "Yes, certainly.", 1,
            "No, I'm busy.", 2
        )) {
            1 -> {
                chatPlayer(neutral, "Yes, Certainly.")
                runeMyst.quest.advanceQuestStage(access)
                chatNpc(happy,
                    "He runs a rune shop in the south east of Varrock. " +
                        "Please, take this package of research notes to him, If all " +
                        "goes well, the secrets of the essence mine may soon be " +
                        "ours once more!")
                hasInventorySpacePackage()
            }
            2 -> noImBusy()
        }
    }

    private suspend fun Dialogue.noImBusy() {
        chatPlayer(neutral, "No, I'm busy.")
        chatNpc(neutral,
            "As you wish adventurer. I will continue to study this " +
                "talisman you have brought me. Return here if you find " +
                "yourself with some spare time to help me.")
    }

    private suspend fun Dialogue.researchPackage() {
        chatNpc(quiz,
            "Hello again, adventurer. Did you take that package to " +
                "Aubury?")

        if (researchPackage in player.inv) {
            chatPlayer(neutral, "Not yet.")
            chatNpc(neutral,
                "He runes a rune shop in the south east of Varrock." +
                    "Please deliver it to him soon,")
        } else {
            chatPlayer(sad, "I lost it. Could I have another?")
            chatNpc(neutral, "Well it's a good job I have copies of everything.")
            hasInventorySpacePackage()
        }
    }

    private suspend fun Dialogue.deliveredPackage() {
        chatNpc(neutral,
            "Ah, ${player.displayName}. How goes your quest? Have you " +
                "delivered my research to Aubury yet?")
        chatPlayer(neutral, "Yes, I have.")
        chatNpc(quiz, "And?")
        chatPlayer(confused, "I don't Know.")
        chatNpc(confused, "Oh... Maybe you should go back and see him them.")
    }

    private suspend fun Dialogue.hasInventorySpacePackage() {
        if (access.invAdd(access.inv, researchPackage).success) {
            vars["varbit.runemysteries_package"] = 1
            objbox(researchPackage, "Sedridor hands you a package.")
            chatNpc(happy, "Best of luck, ${player.displayName}.")
            if (player.vars["varbit.runemysteries_knowname"] <= 0) {
                vars["varbit.runemysteries_knowname"] = 1
                chatPlayer(confused,
                    "I don't remember telling you my name... How do you" +
                        " know it?")
                chatNpc(happy, "Really now? I am the Archmage you know.")
            }
        } else {
            objbox(researchPackage,
                "Sedridor tries to hand you a package, but you don't " +
                    "have enough room to take it.")
        }
    }

    private suspend fun Dialogue.researchNotes(npc: Npc) {
        chatNpc(neutral,
            "Ah, ${player.displayName}. How goes you quest? Have you " +
                "delivered my research to Aubury yet?")
        chatPlayer(neutral, "Yes, I have. He gave me some notes to give you.")
        chatNpc(happy, "Wonderful! Let's have a look then.")
        if (researchNotes in player.inv) {
            access.player.invDel(access.inv, researchNotes)
            vars["varbit.runemysteries_notes_given"] = 1
            objbox(researchNotes, "You hand the notes to Sedridor.")
            handedNotes(npc)
        } else {
            chatPlayer(confused, "Err, you're not going to believe this...")
            chatNpc(confused, "What?")
            chatPlayer(confused, "I don't have them.")
            chatNpc(confused,
                "Right... You're rather careless aren't you. i suggest " +
                    "you go and speak to Aubury once more. With luck he " +
                    "will have made copies.")
        }
    }

    private suspend fun Dialogue.handedNotes(npc: Npc) {
        chatNpc(happy, "Alright, let's see what Aubury has for us...")
        chatNpc(shocked, "Yes, this is it! The lost incantation!")
        chatPlayer(quiz, "So you'll be able to access that essence mine now?")
        chatNpc(happy,
            "That's right! Because of you, our order finally has a " +
                "proper source of rune essence again! Thank you, " +
                "friend.")
        chatNpc(happy,
            "If you ever want to access the essence mine yourself, " +
                "just let me know. It's the least I can do.")
        chatNpc(happy,
            "I will also share the incantation with others, including " +
                "Aubury. When I do, I'll let them know that you are to " +
                "be given unlimited access to the mine.")
        chatNpc(happy,
            "Oh, and you can have this air talisman back as well. I " +
                "have no further need of it, and I'm sure you will find " +
                "it useful.")
        chatNpc(happy,
            "In case you didn't know the talisman can be used to" +
                "craft air runes. Just take it to the Air Altar south of " +
                "Falador along with some rune essence.")
        chatNpc(happy,
            "Don't worry if you can't find the altar. The talisman " +
                "can guide you there. You may find talismans for other " +
                "altars as well while adventuring. They'll let you craft " +
                "other types of rune.")
        chatPlayer(happy, "Great! Thanks!")
        chatNpc(happy, "My pleasure!")
        runeMyst.quest.advanceQuestStage(access)
        player.mes("Congratulations, you've completed a quest: <col=0000ff>Rune Mysteries</col>")

        // Needs to wait on interface to close then call this
        if (player.inv.hasFreeSpace()) {
            hasInventorySpaceTalisman(npc)
        }
    }

    private suspend fun Dialogue.hasInventorySpaceTalisman(npc: Npc) {
        if (access.invAdd(access.inv, airTalisman).success) {
            vars["varbit.runemysteries_owed_talisman"] = 1
            objbox(airTalisman, "Sedridor hands you an air talisman.")
        } else {
            objbox(airTalisman,
                "Sedridor tries to hand you a air talisman, but you don't " +
                    "have enough room to take it.")
        }

        chatNpc(quiz, "Now, is there anything else I can do you you?")
        afterRuneMysteries(npc)
    }

    private suspend fun Dialogue.afterRuneMysteries(npc: Npc) {
        when (choice4(
            "Can you teleport me to the Rune Essence Mine?", 1,
            "Who else knows the teleport to the Rune Essence Mine?", 2,
            "Could you tell me about the old Wizards' Tower?", 3,
            "Nothing thanks, I'm just looking around.", 4,
        )) {
            1 -> {
                chatPlayer(quiz, "Can you teleport me to the Rune Essence Mine?")
                teleportToRuneEssenceMine(npc)
            }
            2 -> whoElseKnows(npc)
            3 -> wizardsTower(npc)
            4 -> noThanks()
        }
    }

    private suspend fun Dialogue.whoElseKnows(npc: Npc) {
        chatPlayer(quiz,
            "Who else knows the teleport to the Rune Essence " +
                "Mine?")
        vars["varbit.runemysteries_know_others"] = 1
        chatNpc(happy,
            "Apart from myself, there's also Aubury in Varrock, " +
                "Wizard Cromperty in East Ardougne, Brimstail in the " +
                "Tree Gnome Stronghold and Wizard Distentor in " +
                "Yanille's Wizards' Guild.")

        when (choice3(
            "Can you teleport me to the Rune Essence Mine?", 1,
            "Could you tell me about the old Wizards' Tower?", 2,
            "Thanks for the information.", 3
        )) {
            1-> {
                chatPlayer(quiz, "Can you teleport me to the Rune Essence Mine?")
                teleportToRuneEssenceMine(npc)
            }
            2 -> wizardsTower(npc)
            3 -> thanksForInfo()
        }
    }

    private suspend fun Dialogue.wizardsTower(npc: Npc) {
        chatPlayer(quiz, "Could you tell me about the old Wizards' Tower?")
        chatNpc(happy,
            "Of course. The first Wizards' Tower was built at the " +
                "same time the Order of Wizards was founded. It was " +
                "at the dawn of the Fifth Age, when the secrets of " +
                "runecrafting were rediscovered.")
        chatNpc(happy,
            "For years, the tower was a hub of magical research. " +
                "Wizards of all races and religions were welcomed into " +
                "our order.")
        chatNpc(sad,
            "Alas, that openness is what ultimately led to disaster. " +
                "The wizards who served Zamorak, the evil god of chaos, " +
                "tried to claim our magical discoveries in his name.")
        chatNpc(sad,
            "They failed, but in retaliation, they burnt the entire " +
                "tower to the ground. Years of work was destroyed.")
        chatNpc(neutral,
            "The tower was soon rebuilt of course, but even now we " +
                "are still trying to regain knowledge that was lost.")
        chatNpc(neutral,
            "That's why I spend my time down here, in fact. This " +
                "basement is all that is left of the old tower, and I believe " +
                "there are still some secrets to discover here.")
        chatNpc(happy,
            "Of course, one secret I am no longer looking for is the " +
                "teleportation incantation to the Rune Essence Mine. " +
                "We have you to thank for that.")

        when (choice3(
            "Can you teleport me to the Rune Essence Mine?", 1,
            "Who else knows the teleport to the Rune Essence Mine?", 2,
            "Thanks for the information.", 3
        )) {
            1 -> teleportToRuneEssenceMine(npc)
            2 -> whoElseKnows(npc)
            3 -> thanksForInfo()
        }
    }

    private suspend fun Dialogue.thanksForInfo() {
        chatPlayer(happy, "Thanks for the information.")
        chatNpc(happy, "My pleasure.")
    }

    private suspend fun Dialogue.noThanks() {
        chatPlayer(neutral, "Nothing thanks, I'm just looking around.")
        chatNpc(confused,
            "Well, take care. You stand on the ruins of the old " +
                "destroyed Wizards' Tower. Strange and powerful " +
                "magicks lurk here.")
    }
}
