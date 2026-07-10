package org.rsmod.content.generic.locs.ladders

import dev.openrune.rscm.RSCM
import dev.openrune.rscm.RSCMType
import dev.openrune.types.ObjectServerType
import dev.openrune.types.SequenceServerType
import org.rsmod.api.config.refs.params
import org.rsmod.api.player.protect.ProtectedAccess
import org.rsmod.api.script.onOpContentLoc1
import org.rsmod.api.script.onOpLoc1
import org.rsmod.map.CoordGrid
import org.rsmod.plugin.scripts.PluginScript
import org.rsmod.plugin.scripts.ScriptContext

class DungeonLadderScript : PluginScript() {
    override fun ScriptContext.startup() {
        onOpContentLoc1("content.dungeonladder_down") { climbDown(it.type) }
        onOpContentLoc1("content.dungeonladder_up") { climbUp(it.type) }
        onOpLoc1("loc.wizards_tower_laddertop") { customDown(it.type) }
        onOpLoc1("loc.wizards_tower_ladder") { customUp(it.type) }
    }

    private suspend fun ProtectedAccess.climbUp(type: ObjectServerType): Unit = climb(type, -6400, RSCM.getReverseMapping(RSCMType.SEQ,type.climbAnim().id))

    private suspend fun ProtectedAccess.climbDown(type: ObjectServerType): Unit = climb(type, 6400, "seq.human_pickupfloor")

    private suspend fun ProtectedAccess.customUp(type: ObjectServerType): Unit = customClimb(type, player.coords.x +1, player.coords.z -6414, RSCM.getReverseMapping(RSCMType.SEQ,type.climbAnim().id))

    private suspend fun ProtectedAccess.customDown(type: ObjectServerType): Unit = customClimb(type, player.coords.x -1, player.coords.z +6414, "seq.human_pickupfloor")

    private suspend fun ProtectedAccess.climb(type: ObjectServerType, translateZ: Int, anim: String) {
        val dest = player.coords.translateZ(translateZ)

        arriveDelay()
        anim(anim)
        delay(1)
        telejump(dest)
    }

    private suspend fun ProtectedAccess.customClimb(type: ObjectServerType, translateX: Int, translateZ: Int, anim: String) {
        val dest = CoordGrid(translateX, translateZ, 0)

        arriveDelay()
        anim(anim)
        delay(1)
        telejump(dest)
    }

    private fun ObjectServerType.climbAnim(): SequenceServerType = param(params.climb_anim)
}
