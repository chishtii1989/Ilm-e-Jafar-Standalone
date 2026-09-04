package com.zakootaapps.ilmjafar.ui.screens.modules.jafar.ilm_ul_abjad.services

import com.zakootaapps.ilmjafar.ui.screens.modules.jafar.ilm_ul_abjad.data.AbjadRepository

data class AbjadCalculationResult(
    val letter: Char,
    val value: Int
)

object AbjadCalculatorService {
    fun calculate(text: String): List<AbjadCalculationResult> {
        val result = mutableListOf<AbjadCalculationResult>()
        for (char in text) {
            val normalizedChar = AbjadRepository.getNormalizedChar(char)
            if (AbjadRepository.abjadKabeer.containsKey(normalizedChar)) {
                result.add(AbjadCalculationResult(char, AbjadRepository.getAbjadKabeerValue(normalizedChar)))
            }
        }
        return result
    }
}
