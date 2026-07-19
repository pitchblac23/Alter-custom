package org.rsmod.content.skills.runecrafting.essence

import org.rsmod.api.player.dialogue.Dialogue
import org.rsmod.api.player.output.soundSynth
import org.rsmod.api.player.protect.ProtectedAccess
import org.rsmod.api.script.onOpLoc1
import org.rsmod.game.entity.Npc
import org.rsmod.map.CoordGrid
import org.rsmod.plugin.scripts.PluginScript
import org.rsmod.plugin.scripts.ScriptContext

//TODO:
//  1: Make projectile on spotanim

private val AUBURY = CoordGrid(3253, 3401)
private val SEDRIDOR = CoordGrid(3105, 9572)

private val RUNE_ESSENCE_MINE = listOf(
    CoordGrid(2911, 4832),
    CoordGrid(2910, 4833),
    CoordGrid(2894, 4843),
    CoordGrid(2926, 4844),
    CoordGrid(2924, 4818),
    CoordGrid(2898, 4818),
    CoordGrid(2924, 4811),
    CoordGrid(2922, 4853),
    CoordGrid(2898, 4851),
    CoordGrid(2887, 4818),
)

class EssencePortals : PluginScript() {
    override fun ScriptContext.startup() {
        onOpLoc1("loc.blankrunestone_exit_portal") { teleportToMainLand() }
    }
}

suspend fun ProtectedAccess.teleportToRuneEssenceMine(npc: Npc) {
    startDialogue(npc) { teleportToRuneEssenceMine(npc) }
}

suspend fun ProtectedAccess.teleportToMainLand() {
    startDialogue() { teleportToMainLand() }
}

suspend fun Dialogue.teleportToRuneEssenceMine(npc: Npc) {
    val essenceMine: CoordGrid = randomLocation(RUNE_ESSENCE_MINE)

    npc.say("Seventior Disthine Molenko!")
    npc.spotanim("spotanim.curse_casting", height = 92)
    player.soundSynth("synth.curse_cast_and_fire")
    delay(1)
    npc.facePlayer(player)
    delay(1)
    npc.resetFaceEntity()
    access.spotanim("spotanim.curse_impact", delay = 15, height = 124)
    player.soundSynth("synth.curse_hit", delay = 15)
    delay(1)
    access.telejump(essenceMine)
}

suspend fun Dialogue.teleportToMainLand() {
    val aubury = randomLocation(AUBURY)
    val sedridor = randomLocation(SEDRIDOR)

    access.spotanim("spotanim.curse_impact", delay = 15, height = 124)
    player.soundSynth("synth.teleport_all", delay = 15)
    delay(1)
    if (player.vars["varbit.essencemine_portal"] == 0) {
        access.telejump(sedridor)
    } else if (player.vars["varbit.essencemine_portal"] == 1) {
        access.telejump(aubury)
    }
}

fun randomLocation(bases: List<CoordGrid>): CoordGrid {
    val base = bases.random()
    return randomizeLocation(base)
}

fun randomLocation(base: CoordGrid): CoordGrid {
    return randomizeLocation(base)
}

fun randomizeLocation(base: CoordGrid) :CoordGrid {
    val offsetX = (-1..1).random()
    val offsetZ = (-1..1).random()

    if ((0..1).random() == 0) {
        return base
    }

    return CoordGrid(
        x = base.x + offsetX,
        z = base.z + offsetZ,
    )
}
