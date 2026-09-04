package com.zakootaapps.ilmjafar.engine.jafr.interfaces

import com.zakootaapps.ilmjafar.data.local.abjad.ArabicLetterEntity
import com.zakootaapps.ilmjafar.data.local.abjad.LetterPropertyEntity
import com.zakootaapps.ilmjafar.data.local.abjad.SpiritualGuidanceEntity

interface AbjadProvider {
    fun getArabicLetter(letter: String): ArabicLetterEntity?
    fun getLetterProperties(letter: String): LetterPropertyEntity?
    fun getAbjadSagheerValue(letter: String): Int
    fun getAbjadKabeerValue(letter: String): Int
    fun getAbjadShamsiValue(letter: String): Int
    fun getAbjadQamariValue(letter: String): Int
    fun getSpiritualGuidance(letter: String): SpiritualGuidanceEntity?
}
