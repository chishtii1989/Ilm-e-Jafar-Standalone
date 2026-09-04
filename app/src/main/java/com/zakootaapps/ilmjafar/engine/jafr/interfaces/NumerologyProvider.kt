package com.zakootaapps.ilmjafar.engine.jafr.interfaces

import com.zakootaapps.ilmjafar.data.local.numerology.BirthNumberEntity
import com.zakootaapps.ilmjafar.data.local.numerology.CompatibilityNumberEntity

interface NumerologyProvider {
    fun getBirthNumberDetails(number: Int): BirthNumberEntity?
    fun getCompatibility(number1: Int, number2: Int): CompatibilityNumberEntity?
}
