package org.rsmod.content.areas.city.varrock.npcs

import org.rsmod.api.player.protect.ProtectedAccess
import org.rsmod.api.script.onOpNpc1
import org.rsmod.game.entity.Npc
import org.rsmod.plugin.scripts.PluginScript
import org.rsmod.plugin.scripts.ScriptContext

class Iffie : PluginScript() {
    override fun ScriptContext.startup() {
        onOpNpc1("npc.varrock_granny_1") { startDialogue(it.npc) }
    }

    private suspend fun ProtectedAccess.startDialogue(npc: Npc) {
        startDialogue(npc) {
            chatNpc(confused, "Sorry, dearie, if I stop to chat I'll lose count.")
        }
    }
}
