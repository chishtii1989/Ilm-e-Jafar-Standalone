package com.zakootaapps.ilmjafar.engine.jafr.modules.ghalibmaghloob

import com.zakootaapps.ilmjafar.engine.jafr.core.GhalibMaghloobResult

class GhalibMaghloobCalculator {

    fun calculate(nameAbjad: Int, motherAbjad: Int): GhalibMaghloobResult {
        // Simple logic: if name is greater, Ghalib, else Maghloob.
        // True Jafr rules divide by elements, numbers, etc.
        val diff = nameAbjad - motherAbjad
        return if (diff > 0) {
            GhalibMaghloobResult("Ghalib", "The name has dominant elemental energy.")
        } else {
            GhalibMaghloobResult("Maghloob", "The mother's name elemental energy is stronger.")
        }
    }
}
