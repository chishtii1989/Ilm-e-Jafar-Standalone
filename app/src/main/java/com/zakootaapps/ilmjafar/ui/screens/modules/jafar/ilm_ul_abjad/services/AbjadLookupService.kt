package com.zakootaapps.ilmjafar.ui.screens.modules.jafar.ilm_ul_abjad.services

import com.zakootaapps.ilmjafar.ui.screens.modules.jafar.ilm_ul_abjad.data.AbjadRepository

object AbjadLookupService {
    fun getLetterForNumber(number: Int): String {
        val entry = AbjadRepository.abjadKabeer.entries.find { it.value == number }
        return entry?.key?.toString() ?: "اس عدد کے مطابق کوئی حرف موجود نہیں"
    }
}
