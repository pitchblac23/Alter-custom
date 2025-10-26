package org.alter.plugins.content.items.food


enum class Food(
    val item: String,
    val heal: Int = 0,
    val overheal: Boolean = false,
    val replacement: Int = -1,
    val tickDelay: Int = 3,
    val comboFood: Boolean = false,
) {
    /**
     * Sea food.
     */
    SHRIMP(item = "items.shrimp", heal = 3),
    SARDINE(item = "items.sardine", heal = 4),
    HERRING(item = "items.herring", heal = 5),
    MACKEREL(item = "items.mackerel", heal = 6),
    TROUT(item = "items.trout", heal = 7),
    COD(item = "items.cod", heal = 7),
    PIKE(item = "items.pike", heal = 8),
    SALMON(item = "items.salmon", heal = 9),
    TUNA(item = "items.tuna", heal = 10),
    RAINBOW(item = "items.hunting_fish_special", heal = 11),
    CAVEEEL(item = "items.cave_eel", heal = 9),
    LOBSTER(item = "items.lobster", heal = 12),
    BASS(item = "items.bass", heal = 13),
    SWORDFISH(item = "items.swordfish", heal = 14),
    MONKFISH(item = "items.monkfish", heal = 16),
    KARAMBWAN(item = "items.tbwt_cooked_karambwan", heal = 18, comboFood = true),
    SHARK(item = "items.shark", heal = 20),
    SEATURTLE(item = "items.seaturtle", heal = 21),
    MANTA_RAY(item = "items.mantaray", heal = 21),
    DARK_CRAB(item = "items.dark_crab", heal = 22),
    ANGLERFISH(item = "items.anglerfish", overheal = true),

    /**
     * Meat.
     */
    CHICKEN(item = "items.cooked_chicken", heal = 4),
    MEAT(item = "items.cooked_meat", heal = 4),
    ROASTBEASTMEAT(item = "items.spit_roasted_beast_meat", heal = 8),
    KEBAB(item = "items.ugthanki_kebab_bad", heal = 19),

    /**
     * Pastries.
     */
    BREAD(item = "items.bread", heal = 5),

    /**
     * Other.
     */
    ONION(item = "items.onion", heal = 1),
    ;

    companion object {
        val values = enumValues<Food>()
    }
}
