package org.alter.game.pluginnew.event.impl

import net.rsprot.protocol.util.CombinedId
import org.alter.game.model.entity.Player
import org.alter.game.model.item.Item
import org.alter.game.pluginnew.event.PlayerEvent
import java.util.Optional
import java.util.function.Predicate

class ItemOnItemEvent(
    val fromItem: Item,
    val toItem: Item,
    val fromSlot: Int,
    val toSlot: Int,
    val from: CombinedId,
    val to: CombinedId,
    player: Player
) : PlayerEvent(player) {
    val fromComponent: CombinedId = from
    val toComponent: CombinedId = to

    /**
     * Checks if both items match any of the given IDs and are in the player's inventory.
     */
    fun itemsAre(vararg ids: Int): Boolean {
        val idSet = ids.toSet()
        return fromItem.id in idSet &&
                toItem.id in idSet &&
                player.inventory.contains(fromItem) &&
                player.inventory.contains(toItem)
    }

    /**
     * Checks if both items match any of the given Items and are in the player's inventory.
     */
    fun itemsAre(vararg items: Item): Boolean {
        val itemSet = items.toSet()
        return fromItem in itemSet &&
                toItem in itemSet &&
                player.inventory.contains(fromItem) &&
                player.inventory.contains(toItem)
    }

    /**
     * Returns the item ID other than the one provided.
     * Returns 0 if both items have the same ID.
     */
    fun otherItem(id: Int): Int =
        if (fromItem.id == toItem.id) 0 else listOf(fromItem.id, toItem.id).first { it != id }

    /**
     * Returns the other Item object, or an invalid placeholder if both have the same ID.
     */
    fun other(id: Int): Item =
        if (fromItem.id == toItem.id) Item(-1) else listOf(fromItem, toItem).first { it.id != id }

    /**
     * Checks that both items satisfy the given condition.
     */
    fun bothSatisfy(condition: (Item) -> Boolean): Boolean =
        condition(fromItem) && condition(toItem)

    /**
     * Checks if either item has the given ID.
     */
    fun oneIs(id: Int): Boolean =
        fromItem.id == id || toItem.id == id

    /**
     * Checks if either item's ID is in the given list.
     */
    fun oneIs(ids: List<Int>): Boolean =
        fromItem.id in ids || toItem.id in ids

    /**
     * Checks if at least one item satisfies the given condition.
     */
    fun oneSatisfies(condition: (Item) -> Boolean): Boolean =
        condition(fromItem) || condition(toItem)

    /**
     * Kotlin-friendly overload for [oneSatisfies].
     */
    fun oneIs(condition: (Item) -> Boolean): Boolean =
        condition(fromItem) || condition(toItem)

    /**
     * Returns the slot index of the specified item ID.
     */
    fun slotOf(itemId: Int): Int =
        if (fromItem.id == itemId) fromSlot else toSlot

    /**
     * Returns the slot index of the other item.
     */
    fun slotOfOther(itemId: Int): Int =
        if (fromItem.id == itemId) toSlot else fromSlot

    /**
     * Java interop: Checks if either item satisfies the provided predicate.
     */
    fun oneIs(pred: java.util.function.Predicate<Item>): Boolean =
        pred.test(fromItem) || pred.test(toItem)

    /**
     * Java interop: Finds the first item that matches the predicate.
     */
    fun find(pred: java.util.function.Predicate<Item>): Optional<Item> =
        when {
            pred.test(fromItem) -> Optional.of(fromItem)
            pred.test(toItem) -> Optional.of(toItem)
            else -> Optional.empty()
        }
}

