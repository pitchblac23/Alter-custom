package org.alter

import dev.openrune.definition.type.ItemType
import it.unimi.dsi.fastutil.bytes.Byte2ByteOpenHashMap

val ItemType.skillReqs: Byte2ByteOpenHashMap
    get() = extra["skillReq"] as Byte2ByteOpenHashMap

val ItemType.bonuses: IntArray
    get() = getIntArrayProperty("bonuses")

val ItemType.equipType: Int
    get() = getIntProperty("equipType")

val ItemType.renderAnimations: IntArray
    get() = getIntArrayProperty("renderAnimations")

val ItemType.weaponType: String
    get() = getStringProperty("weaponType")

val ItemType.attackSpeed: Int
    get() = getIntProperty("attackSpeed")
