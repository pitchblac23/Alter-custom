package org.alter

import dev.openrune.cache.tools.Builder
import dev.openrune.cache.tools.tasks.CacheTask
import dev.openrune.cache.tools.tasks.TaskType
import dev.openrune.tools.PackServerConfig
import io.github.oshai.kotlinlogging.KotlinLogging
import java.io.File
import java.nio.file.Files
import java.nio.file.StandardCopyOption
import kotlin.system.exitProcess

fun getCacheLocation() = File("../data/","cache").path
fun getRawCacheLocation(dir : String) = File("../data/","raw-cache/$dir/")

private val logger = KotlinLogging.logger {}

fun main(args : Array<String>) {
    if (args.isEmpty()) {
        println("Usage: <buildType>")
        exitProcess(1)
    }
    downloadRev(TaskType.valueOf(args.first().uppercase()))
}

fun downloadRev(type : TaskType) {

    val rev = readRevision()

    logger.error { "Using Revision: $rev" }

    val tasks : MutableList<CacheTask> = listOf(
        PackServerConfig()
    ).toMutableList()

    when(type) {
        TaskType.FRESH_INSTALL -> {
            val builder = Builder(type = TaskType.FRESH_INSTALL, File(getCacheLocation()))
            builder.revision(rev.first)
            builder.subRevision(rev.second)
            builder.removeXteas(false)
            builder.extraTasks(*tasks.toTypedArray()).build().initialize()

            Files.move(
                File(getCacheLocation(), "xteas.json").toPath(),
                File("../data/","xteas.json").toPath(),
                StandardCopyOption.REPLACE_EXISTING
            )
        }
        TaskType.BUILD -> {
            val builder = Builder(type = TaskType.BUILD, cacheLocation = File(getCacheLocation()))
            builder.revision(rev.first)
            builder.extraTasks(*tasks.toTypedArray()).build().initialize()
        }
    }


}

fun readRevision(): Pair<Int, Int> {
    val file = listOf("../game.yml", "../game.example.yml")
        .map(::File)
        .first { it.exists() }

    return file.useLines { lines ->
        val line = lines.firstOrNull { it.trimStart().startsWith("revision:") }
            ?: error("No revision line found in ${file.name}")

        // e.g. "revision: 234.1" or "revision: 234"
        val revisionStr = line.substringAfter("revision:").trim()
        val match = Regex("""^(\d+)(?:\.(\d+))?$""").matchEntire(revisionStr)
            ?: error("Invalid revision format: '$revisionStr'")

        val major = match.groupValues[1].toInt()
        val minor = match.groupValues.getOrNull(2)?.toIntOrNull() ?: -1

        major to minor
    }
}
