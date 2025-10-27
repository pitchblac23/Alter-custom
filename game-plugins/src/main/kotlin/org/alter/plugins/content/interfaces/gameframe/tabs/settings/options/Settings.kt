package org.alter.plugins.content.interfaces.gameframe.tabs.settings.options

import org.alter.api.cfg.Varbit
import org.alter.api.ext.closeInterface
import org.alter.api.ext.getVarbit
import org.alter.api.ext.player
import org.alter.api.ext.setVarbit
import org.alter.api.ext.toggleVarbit
import org.alter.game.Server
import org.alter.game.model.World
import org.alter.game.model.attr.INTERACTING_SLOT_ATTR
import org.alter.game.plugin.KotlinPlugin
import org.alter.game.plugin.Plugin
import org.alter.game.plugin.PluginRepository
import org.alter.plugins.content.interfaces.options.OptionsTab

object Settings {
    /**
     * Controls setting tab
     */
    const val ALL_SETTING_INTERFACE = 134
    const val SETTINGS_CLOSE_BUTTON_ID = 4
    const val CLICK = 19
    const val DROPDOWN_CLICK = 28
    const val TAB_CLICK = 23
    const val SHOW_INFORMATION = 5

    class KeyBindingPlugin(
        r: PluginRepository,
        world: World,
        server: Server
    ) : KotlinPlugin(r, world, server) {

        init {

            onButton(ALL_SETTING_INTERFACE, TAB_CLICK) {
                val slot = player.attr[INTERACTING_SLOT_ATTR]!!

                if (slot in 0..7) {
                    player.setVarbit(Varbit.SETTINGS_CURRENT_CATEGORY, slot)
                } else 0

            }

            onButton(ALL_SETTING_INTERFACE, SETTINGS_CLOSE_BUTTON_ID) {
                player.closeInterface(ALL_SETTING_INTERFACE)
            }

            bindSetting(CLICK) {
                val slot = player.attr[INTERACTING_SLOT_ATTR]!!
                val category = player.getVarbit(Varbit.SETTINGS_CURRENT_CATEGORY)
                when (slot) {
                    1 -> {
                        if (category == 3) {
                        }
                    }
                    8 -> {
                        if (category == 3) {
                            player.toggleVarbit(Varbit.SHIFT_DROP_ITEMS)
                        }
                    }
                    23 -> {
                        if (category == 6) {
                            player.toggleVarbit(Varbit.SHOW_THE_STORE_BUTTON_ON_DESKTOP)
                        }
                    }
                    31 -> {
                        if (category == 6) {
                            player.toggleVarbit(Varbit.HIDE_COMPLETED_QUESTS)
                        }
                    }
                    else -> null
                }
            }
        }

        fun bindSetting(child: Int, plugin: Plugin.() -> Unit) {
            onButton(interfaceId = OptionsTab.ALL_SETTINGS_INTERFACE_ID, component = child) {
                plugin(this)
            }
        }
    }
}
