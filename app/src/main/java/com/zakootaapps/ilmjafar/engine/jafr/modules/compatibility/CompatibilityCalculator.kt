package com.zakootaapps.ilmjafar.engine.jafr.modules.compatibility

import com.zakootaapps.ilmjafar.engine.jafr.core.CompatibilityResult
import com.zakootaapps.ilmjafar.engine.jafr.interfaces.NumerologyProvider

class CompatibilityCalculator(private val numerologyProvider: NumerologyProvider) {

    fun calculate(person1Number: Int, person2Number: Int): CompatibilityResult {
        val compatibility = numerologyProvider.getCompatibility(person1Number, person2Number)
        
        return CompatibilityResult(
            haroofScore = (Math.random() * 100).toInt(), // Mocking haroof score
            numbersScore = compatibility?.marriageScore ?: 50,
            analysis = compatibility?.description ?: "Average compatibility."
        )
    }
}
