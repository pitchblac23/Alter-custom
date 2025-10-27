package org.alter.plugins.content.objects.door

import org.alter.api.cfg.Sound
import org.alter.api.ext.*
import org.alter.game.Server
import org.alter.game.model.World
import org.alter.game.model.attr.AttributeKey
import org.alter.game.model.collision.WALL_DIAGONAL
import org.alter.game.model.entity.GameObject
import org.alter.game.model.entity.Player
import org.alter.game.plugin.KotlinPlugin
import org.alter.game.plugin.PluginRepository
import kotlin.math.abs

class DoorPlugin(
    r: PluginRepository, world: World,
    server: Server) : KotlinPlugin(r, world, server) {

    val STICK_STATE = AttributeKey<DoorStickState>()
    val changesBeforeStick: Int = 8
    val resetStickDelay: Int = 80

    init {
        loadService(DoorService())

        onWorldInit {
            world.getService(DoorService::class.java)?.let { service ->
                service.doors.forEach { door ->
                    onObjOption(obj = door.opened, option = "close") {
                        val obj = player.getInteractingGameObj()

                        if (!isStuck(world, obj)) {
                            val newDoor = world.closeDoor(obj, closed = door.closed, invertTransform = obj.type == WALL_DIAGONAL)

                            copyStickVars(obj, newDoor)
                            addStickVar(world, newDoor)
                            player.playSound(Sound.CLOSE_DOOR_SFX)
                        } else {
                            player.message("The door seems to be stuck.")
                            player.playSound(Sound.STUCK_DOOR_SFX)
                        }
                    }

                    onObjOption(obj = door.closed, option = "open") {
                        val obj = player.getInteractingGameObj()
                        val newDoor = world.openDoor(obj, opened = door.opened, invertTransform = obj.type == WALL_DIAGONAL)

                        copyStickVars(obj, newDoor)
                        addStickVar(world, newDoor)
                        player.playSound(Sound.OPEN_DOOR_SFX)
                    }
                }

                service.doubleDoors.forEach { doors ->
                    onObjOption(obj = doors.closed.left, option = "open") {
                        handleDoubleDoors(player, player.getInteractingGameObj(), doors, open = true)
                    }

                    onObjOption(obj = doors.closed.right, option = "open") {
                        handleDoubleDoors(player, player.getInteractingGameObj(), doors, open = true)
                    }

                    onObjOption(obj = doors.opened.left, option = "close") {
                        handleDoubleDoors(player, player.getInteractingGameObj(), doors, open = false)
                    }

                    onObjOption(obj = doors.opened.right, option = "close") {
                        handleDoubleDoors(player, player.getInteractingGameObj(), doors, open = false)
                    }
                }
            }
        }
    }

    fun handleDoubleDoors(p: Player, obj: GameObject, doors: DoubleDoorSet, open: Boolean) {
        val left = obj.id == doors.opened.left || obj.id == doors.closed.left
        val right = obj.id == doors.opened.right || obj.id == doors.closed.right

        check(left || right)

        val otherDoorId = if (open) {
            if (left) doors.closed.right else doors.closed.left
        } else {
            if (left) doors.opened.right else doors.opened.left
        }
        val otherDoor = getNeighbourDoor(world, obj, otherDoorId) ?: return

        if (!open && (isStuck(world, obj) || isStuck(world, otherDoor))) {
            p.message("The door seems to be stuck.")
            p.playSound(Sound.STUCK_DOOR_SFX)
            return
        }

        if (open) {
            val door1 = world.openDoor(obj, opened = if (left) doors.opened.left else doors.opened.right, invertRot = left)
            val door2 = world.openDoor(otherDoor, opened = if (left) doors.opened.right else doors.opened.left, invertRot = right)
            copyStickVars(obj, door1)
            copyStickVars(obj, door2)
            p.playSound(Sound.OPEN_DOOR_SFX)
        } else {
            val door1 = world.closeDoor(obj, closed = if (left) doors.closed.left else doors.closed.right, invertRot = left, invertTransform = left)
            val door2 = world.closeDoor(otherDoor, closed = if (left) doors.closed.right else doors.closed.left, invertRot = right, invertTransform = right)
            copyStickVars(obj, door1)
            copyStickVars(obj, door2)
            p.playSound(Sound.CLOSE_DOOR_SFX)
        }
    }

    fun getNeighbourDoor(world: World, obj: GameObject, otherDoor: Int): GameObject? {
        val tile = obj.tile

        for (x in -1..1) {
            for (z in -1..1) {
                if (x == 0 && z == 0) {
                    continue
                }
                val transform = tile.transform(x, z)
                val tileObj = world.getObject(transform, type = obj.type)
                if (tileObj?.id == otherDoor) {
                    return tileObj
                }
            }
        }
        return null
    }

    fun copyStickVars(from: GameObject, to: GameObject) {
        if (from.attr.has(STICK_STATE)) {
            to.attr[STICK_STATE] = from.attr[STICK_STATE]!!
        }
    }

    fun addStickVar(world: World, obj: GameObject) {
        var currentChanges = getStickChanges(obj)

        if (obj.attr.has(STICK_STATE) && abs(world.currentCycle - obj.attr[STICK_STATE]!!.lastChangeCycle) >= resetStickDelay) {
            currentChanges = 0
        }

        obj.attr[STICK_STATE] = DoorStickState(currentChanges +1, world.currentCycle)
    }

    fun getStickChanges(obj: GameObject): Int = obj.attr[STICK_STATE]?.changeCount ?: 0

    fun isStuck(world: World, obj: GameObject): Boolean {
        val stuck = getStickChanges(obj) >= changesBeforeStick

        if (stuck && abs(world.currentCycle - obj.attr[STICK_STATE]!!.lastChangeCycle) >= resetStickDelay) {
            obj.attr.remove(STICK_STATE)
            return false
        }
        return stuck
    }
}
