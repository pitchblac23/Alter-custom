package org.alter.rscm

import dev.openrune.definition.constants.ConstantProvider
import io.github.oshai.kotlinlogging.KotlinLogging

object RSCM {

    val logger = KotlinLogging.logger {}

    fun getRSCM(entity: Array<String>): List<Int> = entity.map { getRSCM(it) }.toList()

    fun getRSCM(entity: String) = ConstantProvider.getMapping(entity)

}