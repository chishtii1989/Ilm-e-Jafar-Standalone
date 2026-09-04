package com.zakootaapps.ilmjafar.engine.jafr.modules.abjad

import com.zakootaapps.ilmjafar.engine.jafr.interfaces.AbjadProvider

class AbjadCalculator(private val abjadProvider: AbjadProvider) {

    fun calculateKabeer(text: String): Int {
        return calculate(text) { abjadProvider.getAbjadKabeerValue(it) }
    }

    fun calculateSagheer(text: String): Int {
        return calculate(text) { abjadProvider.getAbjadSagheerValue(it) }
    }

    fun calculateShamsi(text: String): Int {
        return calculate(text) { abjadProvider.getAbjadShamsiValue(it) }
    }

    fun calculateQamari(text: String): Int {
        return calculate(text) { abjadProvider.getAbjadQamariValue(it) }
    }

    private fun calculate(text: String, valueSelector: (String) -> Int): Int {
        var total = 0
        val cleanText = text.replace(" ", "")
        for (char in cleanText) {
            total += valueSelector(char.toString())
        }
        return total
    }
}
