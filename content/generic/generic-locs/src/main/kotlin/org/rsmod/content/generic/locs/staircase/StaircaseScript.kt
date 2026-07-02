package org.rsmod.content.generic.locs.staircase

import org.rsmod.api.player.protect.ProtectedAccess
import org.rsmod.api.script.onOpContentLoc1
import org.rsmod.game.loc.BoundLocInfo
import org.rsmod.game.loc.LocAngle
import org.rsmod.map.util.Translation
import org.rsmod.plugin.scripts.PluginScript
import org.rsmod.plugin.scripts.ScriptContext

class StaircaseScript : PluginScript() {
    override fun ScriptContext.startup() {
        onOpContentLoc1("content.staircase_down") { climbDown(it.loc) }
        onOpContentLoc1("content.staircase_up") { climbUp(it.loc) }
    }

    private fun ProtectedAccess.climbUp(loc: BoundLocInfo) =
        climb(loc, loc.climbUpTranslation())

    private fun BoundLocInfo.climbUpTranslation(): Translation =
        when (angle) {
            LocAngle.West -> Translation(x = 0, z = 3, level = 1)   //North
            LocAngle.North -> Translation(x = 3, z = 0, level = 1)  //East
            LocAngle.East -> Translation(x = 0, z = -1, level = 1)  //South
            LocAngle.South -> Translation(x = -1, z = 0, level = 1)  //West
        }

    private fun ProtectedAccess.climbDown(loc: BoundLocInfo) =
        climb(loc, loc.climbDownTranslation())

    private fun BoundLocInfo.climbDownTranslation(): Translation =
        when (angle) {
            LocAngle.West -> Translation(x = 0, z = -2, level = -1)
            LocAngle.North -> Translation(x = -2, z = 0, level = -1)
            LocAngle.East -> Translation(x = 1, z = 3, level = -1)
            LocAngle.South -> Translation(x = 3, z = 0, level = -1)
        }

    private fun ProtectedAccess.climb(loc: BoundLocInfo, translation: Translation) {
        val dest = loc.coords.translate(translation)
        telejump(dest)
    }

}
