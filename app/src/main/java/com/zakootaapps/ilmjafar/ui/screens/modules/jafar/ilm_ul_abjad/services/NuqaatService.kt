package com.zakootaapps.ilmjafar.ui.screens.modules.jafar.ilm_ul_abjad.services

import com.zakootaapps.ilmjafar.ui.screens.modules.jafar.ilm_ul_abjad.data.AbjadRepository

data class NuqaatResult(
    val letter: Char,
    val count: Int
)

object NuqaatService {
    fun calculate(text: String): List<NuqaatResult> {
        val result = mutableListOf<NuqaatResult>()
        for (char in text) {
            if (AbjadRepository.nuqaatMap.containsKey(char)) {
                result.add(NuqaatResult(char, AbjadRepository.nuqaatMap[char] ?: 0))
            }
        }
        return result
    }
}
