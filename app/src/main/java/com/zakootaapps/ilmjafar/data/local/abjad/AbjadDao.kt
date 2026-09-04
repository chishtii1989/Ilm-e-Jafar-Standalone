package com.zakootaapps.ilmjafar.data.local.abjad

interface AbjadDao {
    fun getAllArabicLetters(): List<ArabicLetterEntity>
    fun getArabicLetter(letter: String): ArabicLetterEntity?
    fun getAbjadSagheerValue(letter: String): AbjadSagheerEntity?
    fun getAbjadKabeerValue(letter: String): AbjadKabeerEntity?
    fun getAbjadShamsiValue(letter: String): AbjadShamsiEntity?
    fun getAbjadQamariValue(letter: String): AbjadQamariEntity?
    
    fun getLetterProperties(letter: String): LetterPropertyEntity?
    fun getLetterMeaning(letter: String): LetterMeaningEntity?
    fun getLetterEnergy(letter: String): LetterEnergyEntity?
    
    fun getCompatibleLetters(letter: String): List<CompatibleLetterEntity>
    fun getIncompatibleLetters(letter: String): List<IncompatibleLetterEntity>
    fun getLetterRelationships(letter: String): List<LetterRelationshipEntity>
    
    fun getDominantLetters(number: Int): DominantLetterEntity?
    fun getWeakLetters(number: Int): WeakLetterEntity?
    
    fun getSpiritualGuidance(letter: String): SpiritualGuidanceEntity?
    fun getRemedy(letter: String): RemediesEntity?
}
