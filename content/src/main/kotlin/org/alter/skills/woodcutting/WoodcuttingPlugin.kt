package org.alter.skills.woodcutting

import org.alter.api.ext.getInteractingGameObj
import org.alter.game.pluginnew.PluginEvent
import org.alter.game.pluginnew.event.impl.ObjectClickEvent

class WoodcuttingPlugin : PluginEvent() {

    override fun init() {
        val TREES =
            setOf(
                Woodcutting.Tree(TreeType.Trees, obj = 1276, trunk = 1342), // Tree
                Woodcutting.Tree(TreeType.Trees, obj = 1278, trunk = 1342), // Tree

                Woodcutting.Tree(TreeType.Trees, obj = 1282, trunk = 1347), // Dead Tree
                Woodcutting.Tree(TreeType.Trees, obj = 1286, trunk = 1351), // Dead Tree
                Woodcutting.Tree(TreeType.Trees, obj = 1289, trunk = 1353), // Dead Tree
                Woodcutting.Tree(TreeType.Trees, obj = 1291, trunk = 23054), // Dead Tree
                Woodcutting.Tree(TreeType.Trees, obj = 1383, trunk = 1358), // Dead Tree

                Woodcutting.Tree(TreeType.Trees, obj = 2091, trunk = 1342), // Evergreen
                Woodcutting.Tree(TreeType.Trees, obj = 2092, trunk = 1355), // Evergreen

                //Woodcutting.Tree(TreeType.TREE, obj = 4818, trunk = 4819), // Jungle Tree

                Woodcutting.Tree(TreeType.OAK, obj = 10820, trunk = 1356), // Oak

                Woodcutting.Tree(TreeType.WILLOW, obj = 10819, trunk = 9711), // Willow
                Woodcutting.Tree(TreeType.WILLOW, obj = 10829, trunk = 9471), // Willow
                Woodcutting.Tree(TreeType.WILLOW, obj = 10831, trunk = 9471), // Willow
                Woodcutting.Tree(TreeType.WILLOW, obj = 10833, trunk = 9471), // Willow

                Woodcutting.Tree(TreeType.MAPLE, obj = 10832, trunk = 9712), // Maple

                Woodcutting.Tree(TreeType.YEW, obj = 10822, trunk = 9714), // Yew
            )

        TREES.forEach { tree ->
            on<ObjectClickEvent> {
                where { gameObject.id == tree.obj }
                then {
                    val obj = player.getInteractingGameObj()
                    player.queue {
                        Woodcutting.chopDownTree(this, obj, tree.type, tree.trunk)
                    }
                }
            }
        }
    }
}