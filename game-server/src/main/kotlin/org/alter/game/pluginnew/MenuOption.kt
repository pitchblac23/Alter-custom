package org.alter.game.pluginnew

enum class MenuOption(val id: Int) {
    OP1(0),
    OP2(1),
    OP3(2),
    OP4(3),
    OP5(4),
    OP6(5);

    companion object {
        fun fromId(id: Int): MenuOption = entries.find { it.id == id } ?: error("Invalid object option id: $id")
    }
}
