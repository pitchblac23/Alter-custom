package org.alter.game.pluginnew

import kotlin.script.experimental.api.ScriptCompilationConfiguration

object PluginConfig : ScriptCompilationConfiguration({

}) {
    private fun readResolve(): Any = PluginConfig

}