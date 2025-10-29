package org.alter.plugins.content

import org.alter.api.Skills
import org.alter.api.ext.calculateAndSetCombatLevel
import org.alter.api.ext.levelUpMessageBox
import org.alter.api.ext.message
import org.alter.api.ext.playJingle
import org.alter.api.ext.player
import org.alter.game.Server
import org.alter.game.model.World
import org.alter.game.model.attr.LEVEL_UP_INCREMENT
import org.alter.game.model.attr.LEVEL_UP_SKILL_ID
import org.alter.game.model.entity.AreaSound
import org.alter.game.plugin.KotlinPlugin
import org.alter.game.plugin.PluginRepository
import kotlin.random.Random

class LevelUpPlugin(r: PluginRepository, world: World, server: Server)
    : KotlinPlugin(r, world, server) {

    init {
        setLevelUpLogic {
            val skill = player.attr[LEVEL_UP_SKILL_ID]!!

            if (Skills.isCombat(skill)) {
                player.calculateAndSetCombatLevel()
            }

            player.queue {
                if(player.getSkills()[skill].currentLevel == 99) {
                    player.graphic(1388)
                } else {
                    /**
                    @TODO Each skill has it's own jingle -> Also diff sound when a player unlocks something new.
                    @TODO https://oldschool.runescape.wiki/w/Jingles
                    @TODO Also iirc when unlocking a new skill item it shows it on the message.
                     */
                    var levelupJingles = listOf(29, 67, 50)
                    player.playJingle(levelupJingles[Random.nextInt(levelupJingles.size)])
                    player.graphic(199, 124)
                    player.message("Congratulations, you've just advanced your ${Skills.getSkillName(world, skill)} level. You are now level ${player.getSkills().getBaseLevel(skill)}.")
                    world.spawn(AreaSound(player.tile, 2396, 1, 1))
                }
                levelUpMessageBox(player, skill, player.attr[LEVEL_UP_INCREMENT]!!)
            }
        }
    }
}