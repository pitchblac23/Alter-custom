package org.rsmod.content.quest.area.lumbridge

import org.rsmod.api.player.protect.ProtectedAccess
import org.rsmod.content.quest.manager.ItemRewardDisplay
import org.rsmod.content.quest.manager.QuestScript
import org.rsmod.content.quest.manager.rewards
import org.rsmod.plugin.scripts.ScriptContext

class RuneMysteries :
    QuestScript(
       "quest_runemysteries",
       "varp.runemysteries",
       rewards {
           extra("Access to the Rune Essence Mine")
               },
       ItemRewardDisplay("obj.air_talisman")) {

    val questStart =
        "I spoke to <red>Duke Horacio</red> in <red>Lumbridge Castle</red>. He told me " +
            "that he'd found a <red>Strange Talisman</red> in the <red>Castle</red> which might be " +
            "of use to the <red>Order of Wizards</red> at the <red>Wizards' Tower</red>. He asked " +
            "me to take it there and give it to a wizard called <red>Sedridor</red>."
    val deliverPackage =
        "I delivered the <red>Strange Talisman</red> to <red>Sedridor</red> in the basement " +
            "of the <red>Wizards' Tower</red>. He believes it might be key to " +
            "discovering a <red>Teleportation Incantation</red> to the lost <red>Rune " +
            "Essence Mine</red>. He asked me to help confirm this by delivering " +
            "a <red>Package</red> to <red>Aubury</red>, an expert on <red>Runecrafting</red>."
    val deliverNotes =
        "I delivered the <red>Package</red> to <red>Aubury</red> at his <red>Rune Shop</red> in the south " +
            "east <red>Varrock</red>. He confirmed <red>Sedridor's</red> suspicions and asked " +
            "me to take some <red>Research Notes</red> back to him."

    override fun subTitle(): String {
        return "talking to the <col=800000>Duke Horacio</col> in <col=800000>Lumbridge Castle</col>."
    }

    override fun questLog(player: ProtectedAccess) = questJournal(player) {
        if (player.vars["varbit.runemysteries_notes_given"] == 1) {
            strike(questStart)
            strike(deliverPackage)
            description(
                "I delivered the <red>Package</red> to <red>Aubury</red> in <red>Varrock</red>. he confirmed " +
                    "<red>Sedridor's</red> suspicions and asked me to take some <red>Research " +
                    "Notes</red> back to him, which I did. I should see what <red>Sedridor</red> has " +
                    "learnt from them."
            )

        } else if (player.vars["varbit.runemysteries_notes"] == 1) {
            if ("obj.research_notes" !in player.inv) {
                strike(questStart)
                strike(deliverPackage)
                description(
                    "$deliverNotes I can find " +
                    "<red>Sedridor</red> in the basement of the <red>Wizards' Tower</red>. It I lose the " +
                        "<red>Research Notes</red>, I'll need to ask <red>Aubury</red> for some more."
                )
            } else {
                strike(questStart)
                strike(deliverPackage)
                description(
                    "$deliverNotes I can find " +
                        "<red>Sedridor</red> in the basement of the <red>Wizards' Tower</red>."
                )
            }

        } else if (quest.getQuestStage(player.player) == 4) {
            strike(questStart)
            strike(deliverPackage)
            description(
                "I delivered the <red>Package</red> to <red>Aubury</red> at his <red>Rune Shop</red> in south " +
                    "east <red>Varrock</red>. I should see what he can tell me about the " +
                    "<red>Teleportation Incantation</red>"
            )

        } else if (player.vars["varbit.runemysteries_package"] == 1) {
            if ("obj.research_package" !in player.inv) {
                strike(questStart)
                description(
                    "$deliverPackage I can find" +
                        "him in his <red>Rune Shop</red> in south east <red>Varrock</red>. If I lose the " +
                        "<red>Package</red>, I'll need to ask <red>Sedridor</red> for another."
                )
            } else {
                strike(questStart)
                description(
                    "$deliverPackage I can find" +
                        "him in his <red>Rune Shop</red> in south east <red>Varrock</red>."
                )
            }

        } else if (player.vars["varbit.runemysteries_talisman_give"] == 1) {
            strike(questStart)
            description(
                "I delivered the <red>Strange Talisman</red> to <red>Sedridor</red> in the basement " +
                    "of the <red>Wizards' Tower</red>. I should see what he can tell me about " +
                    "it."
            )

        } else {
            if ("obj.air_talisman" !in player.inv) {
                description(
                    "$questStart I can " +
                        "find the <red>Wizards' Tower</red> south west of <red>Lumbridge</red>, across the " +
                        "bridge from <red>Draynor Village</red>. If I lose the <red>Strange Talisman</red>, I'll " +
                        "need to ask <red>Duke Horacio</red> for another."
                )
            } else {
                description(
                    "$questStart I can " +
                        "find the <red>Wizards' Tower</red> south west of <red>Lumbridge</red>, across the " +
                        "bridge from <red>Draynor Village</red>."
                )
            }
        }
    }

    override fun completedLog(player: ProtectedAccess): String = completionJournal(player) {
        strike(questStart)
        strike(deliverPackage)
        strike(
            "$deliverNotes I did so, and Sedridor used them to " +
                "discover the Teleportation Incantation to the lost Rune " +
                "Essence Mine. As a think you for my help, he granted me " +
                "permission to use the Rune Essence Mine when ever I please.")
    }

    override fun ScriptContext.init() {
    }
}
