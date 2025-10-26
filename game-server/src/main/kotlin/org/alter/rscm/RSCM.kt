package org.alter.rscm

import dev.openrune.definition.constants.ConstantProvider

object RSCM {

    fun getRSCM(entity: Array<String>): List<Int> = entity.map { getRSCM(it) }.toList()

    fun String.asRSCM(): Int {
        return getRSCM(this)
    }

    fun getRSCM(entity: String) = ConstantProvider.getMapping(entity)

}