package org.rsmod.content.skills.runecrafting.essence

import org.rsmod.api.player.dialogue.Dialogue
import org.rsmod.api.player.output.soundSynth
import org.rsmod.api.player.protect.ProtectedAccess
import org.rsmod.game.entity.Npc
import org.rsmod.map.CoordGrid

//TODO:
//  1: Make projectile on spotanim
//  2: Make Teleport Random
//  3: Make portals tele back to npc who send you

private val RUNE_ESSENCE_MINE = CoordGrid(2912, 4838)

suspend fun Dialogue.teleportToRuneEssenceMine(npc: Npc) {
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
    access.telejump(RUNE_ESSENCE_MINE)
}

suspend fun ProtectedAccess.teleportToRuneEssenceMine(npc: Npc) {
    startDialogue(npc) { teleportToRuneEssenceMine(npc) }
}
