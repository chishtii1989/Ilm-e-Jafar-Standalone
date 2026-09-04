package com.zakootaapps.ilmjafar.engine.jafr.modules.ism

import com.zakootaapps.ilmjafar.engine.jafr.core.IsmResult
import com.zakootaapps.ilmjafar.engine.jafr.interfaces.AsmaUlHusnaProvider
import com.zakootaapps.ilmjafar.data.local.modules.asmaulhusna.AsmaUlHusnaEntity

class IsmCalculator(private val asmaUlHusnaProvider: AsmaUlHusnaProvider) {

    fun calculate(abjadTotal: Int): IsmResult {
        // Find Asma-ul-Husna matching the abjad total
        val matchingNames: List<AsmaUlHusnaEntity> = asmaUlHusnaProvider.getAsmaUlHusnaByAbjadKabeer(abjadTotal)
        
        val istikhraj = matchingNames.map { it.name } 
        
        return IsmResult(
            istikhrajEIsm = istikhraj,
            ismEAzam = if (istikhraj.isNotEmpty()) listOf(istikhraj.first()) else listOf("Ya Allah")
        )
    }
}
