package com.zakootaapps.ilmjafar.engine.jafr.modules.adaad

import com.zakootaapps.ilmjafar.engine.jafr.core.AdaadResult

class AdaadCalculator {

    fun calculate(dateOfBirth: String, abjadKabeerTotal: Int): AdaadResult {
        // DateOfBirth format expected: YYYY-MM-DD
        val parts = dateOfBirth.split("-")
        val destiny = if (parts.size == 3) {
            reduceToSingleDigit(parts.joinToString("").map { it.toString().toIntOrNull() ?: 0 }.sum())
        } else {
            reduceToSingleDigit(abjadKabeerTotal)
        }
        
        val soulUrge = reduceToSingleDigit(abjadKabeerTotal)
        val personality = reduceToSingleDigit(destiny + soulUrge)
        
        return AdaadResult(
            destinyNumber = destiny,
            soulUrgeNumber = soulUrge,
            personalityNumber = personality
        )
    }

    private fun reduceToSingleDigit(number: Int): Int {
        var current = number
        while (current > 9) {
            current = current.toString().map { it.toString().toInt() }.sum()
        }
        return current
    }
}
