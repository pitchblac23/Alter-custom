package org.rsmod.content.skills.crafting

import jakarta.inject.Inject
import org.rsmod.api.player.dialogue.BaseMesAnims
import org.rsmod.api.player.dialogue.Dialogue
import org.rsmod.api.player.protect.ProtectedAccess
import org.rsmod.api.player.stat.craftingLvl
import org.rsmod.api.repo.loc.LocRepository
import org.rsmod.api.script.onOpLoc1
import org.rsmod.game.loc.BoundLocInfo
import org.rsmod.map.CoordGrid
import org.rsmod.plugin.scripts.PluginScript
import org.rsmod.plugin.scripts.ScriptContext

class CraftingGuildDoor @Inject constructor(private val locRepo: LocRepository) :
    PluginScript() {

    override fun ScriptContext.startup() {
        onOpLoc1("loc.craftingguilddoor") {
            val inside = CoordGrid(0, 45, 51, 53, 24)
            val outside = CoordGrid(0, 45, 51, 53, 25)

            when {
                player.craftingLvl < 40 ->
                    denyEntry {
                        chatNpcSpecific("Master Crafter", "npc.master_crafter",
                                    neutral, "Sorry, only experienced crafters are allowed in here. You must be level 40 or above to enter.")
                    }

                player.coords == outside && player.craftingLvl >= 40 && player.hasGuildEntryOutfit() ->
                    denyEntry {
                        walkThroughDoor(it.vis)
                        chatNpcSpecific("Master Crafter", "npc.master_crafter",
                                    happy, "Welcome to the Guild of Master Craftsmen.")
                    }

                player.coords == outside && player.craftingLvl >= 40 && player.outfitInv() ->
                    denyEntry {
                        chatNpcSpecific("Master Crafter", "npc.master_crafter",
                                    neutral, "Where's your brown apron? You can't come in here unless you're wearing one.")
                    }

                player.coords == outside && player.craftingLvl >= 40 && !player.hasGuildEntryOutfit() ->
                    denyEntry {
                        chatNpcSpecific("Master Crafter", "npc.master_crafter",
                                    neutral, "Where's your brown apron? You can't come in here unless you're wearing one.")
                        chatPlayer(BaseMesAnims.neutral, "Err... I haven't got one.")
                    }

                player.coords == inside -> walkThroughDoor(it.vis)
            }
        }
    }

    private suspend fun ProtectedAccess.denyEntry(lines: suspend Dialogue.() -> Unit) {
        startDialogue { lines() }
    }

    private fun ProtectedAccess.walkThroughDoor(guildDoor: BoundLocInfo) {
        val doorCoords = guildDoor.coords
        val north = coords.z >= doorCoords.z
        val walkTo = if (north) doorCoords.translateZ(-1) else doorCoords.translateZ(0)
        val openAngle = guildDoor.turnAngle(rotations = 1)

        locRepo.del(guildDoor, 3)
        locRepo.add(doorCoords.translateZ(-1), "loc.inactivepoordoor", 3, openAngle, guildDoor.shape)
        locRepo.add(guildDoor.coords, "loc.inviswall", 3, openAngle, guildDoor.shape)

        soundSynth("synth.nicedoor_open")
        teleport(walkTo)
    }
}
