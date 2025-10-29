package org.alter.skills.woodcutting

import org.alter.rscm.RSCM.getRSCM

/**
 * @author Tom <rspsmods@gmail.com>
 */
enum class TreeType(val level: Int, val xp: Double, val log: Int, val depleteChance: Int, val respawnTime: IntRange) {
    Trees(level = 1, xp = 25.0, getRSCM("items.logs"), depleteChance = 0, respawnTime = 15..25),
    ACHEY(level = 1, xp = 25.0, getRSCM("items.achey_tree_logs"), depleteChance = 1, respawnTime = 15..25),
    OAK(level = 15, xp = 37.5, getRSCM("items.oak_logs"), depleteChance = 2, respawnTime = 20..40),
    WILLOW(level = 30, xp = 67.5, getRSCM("items.willow_logs"), depleteChance = 8, respawnTime = 15..20),
    TEAK(level = 35, xp = 85.0, getRSCM("items.teak_logs"), depleteChance = 11, respawnTime = 22..55),
    MAPLE(level = 45, xp = 100.0, getRSCM("items.maple_logs"), depleteChance = 11, respawnTime = 30..50),
    HOLLOW(level = 45, xp = 82.0, getRSCM("items.hollow_bark"), depleteChance = 8, respawnTime = 22..68),
    MAHOGANY(level = 50, xp = 125.0, getRSCM("items.mahogany_logs"), depleteChance = 11, respawnTime = 22..68),
    YEW(level = 60, xp = 175.0, getRSCM("items.yew_logs"), depleteChance = 14, respawnTime = 30..60),
    MAGIC(level = 75, xp = 250.0, getRSCM("items.magic_logs"), depleteChance = 18, respawnTime = 120..120),
    REDWOOD(level = 90, xp = 380.0, getRSCM("items.redwood_logs"), depleteChance = 11, respawnTime = 50..100),
}
