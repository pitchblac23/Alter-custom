package org.alter.game.pluginnew

import org.alter.game.pluginnew.event.Event
import org.alter.game.pluginnew.event.EventListener
import org.alter.game.pluginnew.event.EventManager
import kotlin.reflect.KClass
import kotlin.script.experimental.annotations.KotlinScript

@KotlinScript(fileExtension = "plugin.kts", compilationConfiguration = PluginConfig::class)
abstract class Script  {

    inline fun <reified K : Event> on(config: EventListener<K>.() -> EventListener<K>): EventListener<K> {
        return config.invoke(EventListener(K::class)).submit()
    }

    fun <K : Event> on(type: KClass<K>, config: EventListener<K>.() -> EventListener<K>): EventListener<K> {
        return config.invoke(EventListener(type)).submit()
    }

    fun <K : Event> addFilter(type: KClass<K>, filter: K.() -> Boolean) {
        EventManager.addFilter<K>(type.java, filter)
    }

}