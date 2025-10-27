package org.alter.plugins.content.skills.woodcutting

import org.alter.api.Skills
import org.alter.api.cfg.Sound
import org.alter.api.ext.*
import org.alter.game.model.attr.AttributeKey
import org.alter.game.model.entity.DynamicObject
import org.alter.game.model.entity.GameObject
import org.alter.game.model.entity.Player
import org.alter.game.model.queue.QueueTask
import org.alter.rscm.RSCM.getRSCM

/**
 * @author Tom <rspsmods@gmail.com>
 */
object Woodcutting {
    val infernalAxe = AttributeKey<Int>("Infernal Axe Charges")

    data class Tree(val type: TreeType, val obj: Int, val trunk: Int)

    suspend fun chopDownTree(it: QueueTask, obj: GameObject, tree: TreeType, trunkId: Int) {

        val p = it.ctx as Player

        if (!canChop(p, obj, tree)) {
            return
        }

        p.filterableMessage("You swing your axe at the tree.")

        while (true) {
            if (!canChop(p, obj, tree)) {
                p.animate(-1)
                break
            }

            p.animate(selectAxe(p)!!.animation)
            it.wait(2)

            val level = p.getSkills().getCurrentLevel(Skills.WOODCUTTING)
            if (level.interpolate(minChance = 60, maxChance = 190, minLvl = 1, maxLvl = 99, cap = 255)) {
                p.filterableMessage("You get some ${tree.log.getItemName(true)}.")
                p.playSound(3600)
                p.inventory.add(tree.log)
                p.addXp(Skills.WOODCUTTING, tree.xp)

                if (p.world.random(tree.depleteChance) == 0) {
                    p.animate(-1)

                    if (trunkId != -1) {
                        val world = p.world

                        p.playSound(Sound.TREE_FALL)

                        world.queue {
                            val trunk = DynamicObject(obj, trunkId)

                            world.remove(obj)
                            world.spawn(trunk)
                            wait(tree.respawnTime.random())
                            world.remove(trunk)
                            world.spawn(DynamicObject(obj))
                        }
                    }
                    break
                }
            }
            it.wait(2)
        }
    }

    private fun canChop(p: Player, obj: GameObject, tree: TreeType): Boolean {

        if (!p.world.isSpawned(obj)) {
            return false
        }

        if (selectAxe(p) == null) {
            p.message("You need an axe to chop down this tree.")
            p.message("You do not have an axe which you have the woodcutting level to use.")
            return false
        }

        if (p.getSkills().getBaseLevel(Skills.WOODCUTTING) < tree.level) {
            p.message("You need a Woodcutting level of ${tree.level} to chop down this tree.")
            return false
        }

        if (p.inventory.isFull) {
            p.message("Your inventory is too full to hold any more logs.")
            return false
        }

        return true
    }

    fun createAxe(player: Player) {
        if (player.getSkills().getBaseLevel(Skills.WOODCUTTING) >= 61 && player.getSkills().getBaseLevel(Skills.FIREMAKING) >= 85) {
            player.inventory.remove(getRSCM("item.dragon_axe"))
            player.inventory.remove(getRSCM("item.smouldering_stone"))
            player.inventory.add(getRSCM("item.infernal_axe"))

            player.attr.put(infernalAxe, 5000)

            player.animate(id = 4511, delay = 2)
            player.graphic(id = 1240, height = 2)

            player.addXp(Skills.FIREMAKING, 350.0)
            player.addXp(Skills.WOODCUTTING, 200.0)
        } else if (player.getSkills().getBaseLevel(Skills.FIREMAKING) < 85 || player.getSkills().getBaseLevel(Skills.WOODCUTTING) < 61 &&
            player.getSkills().getBaseLevel(Skills.FIREMAKING) >= 85 || player.getSkills().getBaseLevel(Skills.WOODCUTTING) >= 61
        ) {
            player.message("You need 61 woodcrafing and 85 firemaking to make this")
        }
    }

    fun checkCharges(p: Player) {
        p.message("Your infernal axe currently has ${p.attr.get(infernalAxe)} charges left.")
    }

    private fun selectAxe(p: Player): AxeType? {
        return AxeType.values.firstOrNull {
            p.getSkills().getBaseLevel(Skills.WOODCUTTING) >= it.level &&
                    (p.equipment.contains(it.item) || p.inventory.contains(it.item))
        }
    }
}
