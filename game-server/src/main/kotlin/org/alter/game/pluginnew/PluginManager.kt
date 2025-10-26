package org.alter.game.pluginnew

import io.github.classgraph.ClassGraph
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.io.File
import java.lang.reflect.Constructor
import java.net.URLClassLoader

object PluginManager {

    private val pkg = "org.alter"

    val scripts = ArrayList<Constructor<Script>>()

    @OptIn(DelicateCoroutinesApi::class)
    fun load() {
        val otherModuleClasses = File("../content/build/classes/kotlin/main")


        ClassGraph()
            .overrideClasspath(otherModuleClasses)
            .acceptPackages(pkg)
            .enableClassInfo()
            .scan().use { scanResult ->
                val pluginClassList = scanResult.getSubclasses(Script::class.java.name).directOnly()

                pluginClassList.forEach { classInfo ->
                    try {
                        val clazz = classInfo.loadClass(Script::class.java)
                        val instance = clazz.getDeclaredConstructor().newInstance()
                        println("Loaded plugin: ${classInfo.name}")
                        scripts.add(clazz.getDeclaredConstructor())
                    } catch (ex: Exception) {
                        println("Error loading plugin ${classInfo.name}")
                        ex.printStackTrace()
                    }
                }
            }
    }
}