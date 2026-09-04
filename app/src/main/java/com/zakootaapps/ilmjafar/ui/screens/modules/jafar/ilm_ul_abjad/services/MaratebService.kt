package com.zakootaapps.ilmjafar.ui.screens.modules.jafar.ilm_ul_abjad.services

import com.zakootaapps.ilmjafar.ui.screens.modules.jafar.ilm_ul_abjad.data.AbjadRepository

data class MaratebResult(
    val letter: Char,
    val marateb: Int
)

object MaratebService {
    fun calculate(text: String): List<MaratebResult> {
        val result = mutableListOf<MaratebResult>()
        for (char in text) {
            val normalizedChar = AbjadRepository.getNormalizedChar(char)
            if (AbjadRepository.abjadKabeer.containsKey(normalizedChar)) {
                val value = AbjadRepository.getAbjadKabeerValue(normalizedChar)
                val marateb = if (value > 0) value.toString().length else 0
                result.add(MaratebResult(char, marateb))
            }
        }
        return result
    }
}
