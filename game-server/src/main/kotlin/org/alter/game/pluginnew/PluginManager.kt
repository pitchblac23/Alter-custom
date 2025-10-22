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

    fun loadSize(): Int = scripts.size

    @OptIn(DelicateCoroutinesApi::class)
    fun load() {
        println("Scanning package: $pkg")

        // First try to find compiled classes from the current classpath
        ClassGraph()
            .enableClassInfo()
            .acceptPackages(pkg)
            .scan()
            .use { scanResult ->
                val subclasses = scanResult.getSubclasses(Script::class.java.name)
                    .directOnly()

                println("Found ${subclasses.size} subclasses of Script in package '$pkg'")

                for (classInfo in subclasses) {
                    println(" -> Found subclass: ${classInfo.name}")

                    try {
                        val clazz = classInfo.loadClass(Script::class.java)
                        println("    Loaded class: ${clazz.name}")

                        val constructor = clazz.getDeclaredConstructor()
                        println("    Found constructor: $constructor")

                        scripts.add(constructor)
                        println("    ✅ Added to scripts list")
                    } catch (e: NoSuchMethodException) {
                        println("    ⚠️ No no-arg constructor found for ${classInfo.name}: ${e.message}")
                    } catch (e: Throwable) {
                        println("    ❌ Failed to load ${classInfo.name}: ${e::class.simpleName} - ${e.message}")
                        e.printStackTrace()
                    }
                }
            }

        // Also try to load from content module's build output
        loadFromContentModule()

        runBlocking {
            scripts.map {
                GlobalScope.launch {
                    it.newInstance()
                    //step()
                }
            }.joinAll()
        }
    }

    private fun loadFromContentModule() {
        try {
            // Look for content module's build output
            val contentBuildDir = File("../content/build/classes/kotlin/main")
            if (!contentBuildDir.exists()) {
                println("Content build directory not found: ${contentBuildDir.absolutePath}")
                return
            }

            println("Scanning content module build output: ${contentBuildDir.absolutePath}")

            // Create a classloader that includes the content module's build output
            val contentClassLoader = URLClassLoader(
                arrayOf(contentBuildDir.toURI().toURL()),
                Thread.currentThread().contextClassLoader
            )

            // Use ClassGraph with the content module's classloader
            ClassGraph()
                .overrideClassLoaders(contentClassLoader)
                .enableClassInfo()
                .acceptPackages(pkg)
                .scan()
                .use { scanResult ->
                    val subclasses = scanResult.getSubclasses(Script::class.java.name)
                        .directOnly()

                    println("Found ${subclasses.size} subclasses of Script in content module")

                    for (classInfo in subclasses) {
                        println(" -> Found content subclass: ${classInfo.name}")

                        try {
                            val clazz = classInfo.loadClass(Script::class.java)
                            println("    Loaded content class: ${clazz.name}")

                            val constructor = clazz.getDeclaredConstructor()
                            println("    Found content constructor: $constructor")

                            scripts.add(constructor)
                            println("    ✅ Added content script to scripts list")
                        } catch (e: NoSuchMethodException) {
                            println("    ⚠️ No no-arg constructor found for content class ${classInfo.name}: ${e.message}")
                        } catch (e: Throwable) {
                            println("    ❌ Failed to load content class ${classInfo.name}: ${e::class.simpleName} - ${e.message}")
                            e.printStackTrace()
                        }
                    }
                }

        } catch (e: Throwable) {
            println("Error loading from content module: ${e.message}")
            e.printStackTrace()
        }
    }
}