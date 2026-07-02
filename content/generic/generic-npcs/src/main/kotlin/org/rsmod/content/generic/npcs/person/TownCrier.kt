package org.rsmod.content.generic.npcs.person

import jakarta.inject.Inject
import org.rsmod.api.random.GameRandom
import org.rsmod.api.script.onAiContentTimer
import org.rsmod.game.entity.Npc
import org.rsmod.plugin.scripts.PluginScript
import org.rsmod.plugin.scripts.ScriptContext

class TownCrier
@Inject
constructor(private val random: GameRandom) :
    PluginScript() {
        override fun ScriptContext.startup() {
            onAiContentTimer("content.town_crier") { npc.crierTimer() }
        }

        private fun Npc.crierTimer() {
            val next = random.of(15..34)
            aiTimer(next)

            if (random.randomBoolean(2)) {
                sayFlavourText()
            }
        }

        private fun Npc.sayFlavourText() {
            when (random.of(maxExclusive = 2)) {
                0 -> {
                    say("Make sure your account security is up to date!")
                    randomAnim()
                }
                1 -> {
                    say("Check out our newsposts for all the latest info!")
                    randomAnim()
                }
            }
        }

        private fun Npc.randomAnim() {
            when (random.of(maxExclusive = 2)) {
                0 -> anim("seq.town_crier_scratch_head")
                1 -> anim("seq.town_crier_bell_ring")
            }
        }

    }
