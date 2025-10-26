package org.alter

import org.alter.api.ext.message
import org.alter.game.pluginnew.Script
import org.alter.game.pluginnew.event.impl.ButtonClickEvent
import org.alter.game.pluginnew.event.impl.ObjectClickEvent

class TestEvents : Script() {

    init {

        on<ObjectClickEvent> {
            where { optionName == "Chop down" }
            then {
                player.message("Opening doorrrr... [${option}, ${gameObject.tile}]")
            }
        }

        on<ButtonClickEvent> {
            then {
                player.message("$component")
            }
        }

    }

}