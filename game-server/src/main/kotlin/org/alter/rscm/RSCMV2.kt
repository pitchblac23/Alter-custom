package org.alter.rscm

import dev.openrune.definition.constants.ConstantProvider

object RSCMV2 {

    fun getRSCM(entity: Array<String>): List<Int> = entity.map { getRSCM(it) }.toList()


    fun getRSCM(entity: String) : Int = ConstantProvider.getMapping(entity)



}