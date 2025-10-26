@file:Suppress("UNCHECKED_CAST")

package org.alter.game.pluginnew.event

import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableSharedFlow
import org.alter.game.pluginnew.event.impl.WorldTickEvent
import org.joda.time.DateTime
import org.joda.time.format.DateTimeFormat
import java.util.*
import java.util.concurrent.ConcurrentHashMap
import java.util.function.Predicate
import kotlin.collections.map

object EventManager {

    val DEBUG_EVENTS = false

    val DT_FORMAT = DateTimeFormat.forPattern("MM/dd/yyyy HH:mm:ss")

    /**
     * Coroutine channels that receive and dispatch events
     */
    private val eventChannels = ConcurrentHashMap<Class<out Event>, MutableSharedFlow<out Event>>()

    /**
     * Predicates used to filter events before they are sent to listeners
     */
    private val eventFilter = ConcurrentHashMap<Class<out Event>, LinkedList<Predicate<out Event>>>()

    /**
     * Predicates used to filter events before they are sent to listeners
     */
    private val listeners = ConcurrentHashMap<Class<out Event>, List<EventListener<out Event>>>()

    /**
     * Predicates used to filter events before they are sent to listeners
     */
    val returnableListeners = ConcurrentHashMap<Class<out Event>, List<ReturnableEventListener<out Event, Any>>>()

    /**
     * Coroutine context for processing events
     */
    @OptIn(DelicateCoroutinesApi::class)
    private val context = newFixedThreadPoolContext(
        (Runtime.getRuntime().availableProcessors() - 1).coerceAtLeast(1),
        "EventContext"
    )

    /**
     * Post an [Event] to all subscribed [EventListener]s
     */
    fun <E : Event> post(event: E) {

        if (DEBUG_EVENTS && event::class != WorldTickEvent::class) {
            if (event is PlayerEvent) {
                println("[${DT_FORMAT.print(DateTime.now())}] [${event.player.username}] ${event::class.simpleName}")
            }
        }

        CoroutineScope(context).launch {
            if (getFilters<E>(event.javaClass).all { it.test(event) }) {
                getChannel<E>(event.javaClass).emit(event)
            }
        }

    }

    /**
     * Post an [Event] to all subscribed [EventListener]s and waits for all subscribers to complete
     */
    fun <E : Event> postAndWait(event: E) {
        runBlocking {
            listeners[event.javaClass]?.map {
                CoroutineScope(context).launch {
                    if (getFilters<E>(event.javaClass).all { filter -> filter.test(event) }) {
                        try {
                            with(it as EventListener<E>) {
                                if (condition(event)) {
                                    action(event)
                                } else {
                                    otherwiseAction(event)
                                }
                            }
                        } catch (ex: Exception) {
                            ex.printStackTrace()
                            println("Error in event listener")
                            it.stack.forEach { println(it) }
                        }
                    }
                }
            }?.joinAll()
        }
    }

    /**
     * Post an [Event] to all subscribed [EventListener]s and waits for all subscribers to complete
     */
    fun <E : Event> postAndCall(event: E, completion: Runnable) {
        CoroutineScope(context).launch { postAndWait(event) }.invokeOnCompletion { completion.run() }
    }

    /**
     * Post an [Event] to all subscribed [EventListener]s and waits for all listeners
     * returning all that match the type K
     */
    fun <E : Event, K> postAndReturn(event: E): List<K> {
        post(event)

        val returns = mutableListOf<K>()

        returnableListeners[event.javaClass]?.forEach {
            runBlocking {
                try {
                    if (getFilters<E>(event.javaClass).all { it.test(event) }) {
                        with(it as ReturnableEventListener<E, K>) {
                            if (condition(event)) {
                                val ret = action(event)
                                returns.add(ret as K)
                            } else {
                                otherwiseAction(event)
                            }
                        }
                    }
                } catch (ex: Exception) {
                    ex.printStackTrace()
                    println("Error in event listener")
                    it.stack.forEach { println(it) }
                }
            }
        }

        return returns
    }

    /**
     * Get the coroutine [Channel] responsible for the specific event
     */
    private fun <E : Event> getChannel(clazz: Class<out Event>): MutableSharedFlow<E> {
        return eventChannels.computeIfAbsent(clazz) { MutableSharedFlow<E>() } as MutableSharedFlow<E>
    }

    /**
     * Get the list of filter predicates for the specific event
     */
    private fun <E : Event> getFilters(clazz: Class<out Event>): LinkedList<Predicate<E>> {
        return eventFilter.computeIfAbsent(clazz) { LinkedList<Predicate<out Event>>() } as LinkedList<Predicate<E>>
    }

    /**
     * Post an [Event] to all subscribed [EventListener]s
     */
    fun <E : Event> addFilter(clazz: Class<E>, filter: Predicate<E>) {
        getFilters<E>(clazz).addLast(filter)
    }

    /**
     * Listen for new posts on the [Channel] on a separate coroutine
     */
    fun <E : Event> listen(event: Class<out Event>, listener: EventListener<E>) {
        listeners.compute(event) { _, old ->
            if (old == null) listOf(listener)
            else old + listener
        }

        CoroutineScope(context).launch {
            try {
                getChannel<E>(event).collect {
                    try {
                        if (listener.condition(it)) {
                            listener.action(it)
                            if (listener.singleUse) {
                                listeners.compute(event) { _, old ->
                                    old?.filter { it !== listener }
                                }
                                cancel(CancellationException())
                            }
                        } else {
                            listener.otherwiseAction(it)
                        }
                    } catch (_: CancellationException) {
                    } catch (ex: Exception) {
                        ex.printStackTrace()
                    }
                }
            } catch (_: CancellationException) {
            } catch (ex: Exception) {
                ex.printStackTrace()
            }
        }
    }

}