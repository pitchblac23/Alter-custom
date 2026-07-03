package org.rsmod.content.generic.locs.banks

import org.rsmod.api.player.protect.ProtectedAccess
import org.rsmod.api.script.onOpContentLoc1
import org.rsmod.plugin.scripts.PluginScript
import org.rsmod.plugin.scripts.ScriptContext

//TODO:: Make diary bank chests only work after completing diary

class BankChest : PluginScript() {
    override fun ScriptContext.startup() {
        onOpContentLoc1("content.bank_chest") { openBank() }
    }

    private fun ProtectedAccess.openBank() {
        ifOpenMainSidePair(main = "interface.bankmain", side = "interface.bankside")
    }
}
