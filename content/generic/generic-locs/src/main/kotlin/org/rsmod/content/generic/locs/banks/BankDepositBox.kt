package org.rsmod.content.generic.locs.banks

import org.rsmod.api.player.protect.ProtectedAccess
import org.rsmod.api.script.onOpContentLoc1
import org.rsmod.plugin.scripts.PluginScript
import org.rsmod.plugin.scripts.ScriptContext

class BankDepositBox : PluginScript() {
    override fun ScriptContext.startup() {
        onOpContentLoc1("content.bank_deposit_box") { openDepositBox() }
    }

    private fun ProtectedAccess.openDepositBox() {
        ifOpenMainSidePair(main = "interface.bank_depositbox", side = "interface.inventory_noops")
    }
}
