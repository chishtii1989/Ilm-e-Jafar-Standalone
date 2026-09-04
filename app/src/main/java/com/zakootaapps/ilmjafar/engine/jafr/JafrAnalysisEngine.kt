package com.zakootaapps.ilmjafar.engine.jafr

import com.zakootaapps.ilmjafar.engine.rules.RuleContext
import com.zakootaapps.ilmjafar.engine.rules.JafariRuleEngine
import com.zakootaapps.ilmjafar.engine.rules.AbjadRules
import com.zakootaapps.ilmjafar.engine.rules.TalismRules
import com.zakootaapps.ilmjafar.util.TalismEngine
import com.zakootaapps.ilmjafar.data.local.JafariKnowledgeRecordEntity

data class LetterStats(
    val letter: String,
    val count: Int,
    val abjadValue: Int,
    val isNoorani: Boolean,
    val element: String
)

data class ElementBalance(
    val fireCount: Int,
    val airCount: Int,
    val waterCount: Int,
    val earthCount: Int,
    val dominantElement: String
)

data class JafrAnalysisRequest(
    val text: String,
    val purpose: String = "",
    val cachedKnowledge: List<JafariKnowledgeRecordEntity> = emptyList()
)

data class JafrAnalysisResult(
    val originalText: String,
    val processedText: String,
    val totalAbjad: Int,
    val totalLetters: Int,
    val spiritualNumber: Int,
    val reducedNumber: Int,
    
    val letterStats: List<LetterStats>,
    val hiddenLetters: List<String>,
    
    val nooraniCount: Int,
    val zulmaniCount: Int,
    val elementBalance: ElementBalance,
    val dominantLetter: String,
    
    val numericalHarmony: String,
    val numericalConflict: String,
    
    val recommendedPlanet: String,
    val recommendedElement: String,
    val recommendedShape: String,
    val recommendedInk: String,
    val recommendedDay: String,
    val recommendedHour: String,
    val recommendedIsm: String,
    val recommendedMuwakkil: String,
    val recommendedDirection: String
)

object JafrAnalysisEngine {
    
    private val nooraniLetters = listOf("ا", "ح", "ر", "س", "ص", "ط", "ع", "ق", "ک", "ل", "م", "ن", "ہ", "ی")
    
    private val fireLetters = listOf("ا", "ہ", "ط", "م", "ف", "ش", "ذ")
    private val airLetters = listOf("ب", "و", "ی", "ن", "ص", "ت", "ض")
    private val waterLetters = listOf("ج", "ز", "ک", "س", "ق", "ث", "ظ")
    private val earthLetters = listOf("د", "ح", "ل", "ع", "ر", "خ", "غ")
    
    private val hiddenLettersMap = mapOf(
        "ا" to "الف", "ب" to "با", "ج" to "جیم", "د" to "دال", "ہ" to "ہا",
        "و" to "واو", "ز" to "زا", "ح" to "حا", "ط" to "طا", "ی" to "یا",
        "ک" to "کاف", "ل" to "لام", "م" to "میم", "ن" to "نون", "س" to "سین",
        "ع" to "عین", "ف" to "فا", "ص" to "صاد", "ق" to "قاف", "ر" to "را",
        "ش" to "شین", "ت" to "تا", "ث" to "ثا", "خ" to "خا", "ذ" to "ذال",
        "ض" to "ضاد", "ظ" to "ظا", "غ" to "غین"
    )

    fun analyze(request: JafrAnalysisRequest): JafrAnalysisResult {
        val processedText = request.text.replace(Regex("\\s+"), "")
        
        val totalAbjad = TalismEngine.calculateAbjad(processedText)
        val totalLetters = processedText.length
        
        val ruleContext = RuleContext(
            totalAbjad = totalAbjad,
            lettersCount = totalLetters,
            purpose = request.purpose,
            cachedKnowledge = request.cachedKnowledge
        )
        
        var nooraniCount = 0
        var zulmaniCount = 0
        var fireCount = 0
        var airCount = 0
        var waterCount = 0
        var earthCount = 0
        
        val letterStatsMap = mutableMapOf<String, Int>()
        val hiddenLettersList = mutableListOf<String>()
        
        for (char in processedText) {
            val letter = char.toString()
            letterStatsMap[letter] = letterStatsMap.getOrDefault(letter, 0) + 1
            
            if (nooraniLetters.contains(letter)) nooraniCount++ else zulmaniCount++
            
            val dbLetter = request.cachedKnowledge.find { it.recordType == "LETTER" && it.arabicName == letter }
            val element = dbLetter?.element ?: when {
                fireLetters.contains(letter) -> "آتش"
                airLetters.contains(letter) -> "باد"
                waterLetters.contains(letter) -> "آب"
                earthLetters.contains(letter) -> "خاک"
                else -> "نامعلوم"
            }
            
            when {
                element.contains("آتش") -> fireCount++
                element.contains("باد") -> airCount++
                element.contains("آب") -> waterCount++
                element.contains("خاک") -> earthCount++
            }
            
            hiddenLettersMap[letter]?.let { hiddenLettersList.add(it) }
        }
        
        val letterStats = letterStatsMap.map { (letter, count) ->
            val isNoorani = nooraniLetters.contains(letter)
            val dbLetter = request.cachedKnowledge.find { it.recordType == "LETTER" && it.arabicName == letter }
            val element = dbLetter?.element ?: when {
                fireLetters.contains(letter) -> "آتش"
                airLetters.contains(letter) -> "باد"
                waterLetters.contains(letter) -> "آب"
                earthLetters.contains(letter) -> "خاک"
                else -> "نامعلوم"
            }
            LetterStats(
                letter = letter,
                count = count,
                abjadValue = TalismEngine.calculateAbjad(letter),
                isNoorani = isNoorani,
                element = element
            )
        }.sortedByDescending { it.count }
        
        val maxElementCount = maxOf(fireCount, airCount, waterCount, earthCount)
        val dominantElementStr = when (maxElementCount) {
            fireCount -> "آتش (Fire)"
            airCount -> "باد (Air)"
            waterCount -> "آب (Water)"
            earthCount -> "خاک (Earth)"
            else -> "متوازن (Balanced)"
        }
        
        val elementBalance = ElementBalance(
            fireCount = fireCount,
            airCount = airCount,
            waterCount = waterCount,
            earthCount = earthCount,
            dominantElement = dominantElementStr
        )
        
        val numericalHarmony = if (totalAbjad % 2 == 0) "سعد (Even)" else "طاق (Odd)"
        val numericalConflict = if (totalAbjad % 3 == 0) "موافق (Favorable)" else "مخالف (Unfavorable)"
        
        return JafrAnalysisResult(
            originalText = request.text,
            processedText = processedText,
            totalAbjad = totalAbjad,
            totalLetters = totalLetters,
            spiritualNumber = AbjadRules.calculateSpiritualNumber(totalAbjad, totalLetters),
            reducedNumber = AbjadRules.reduceToSingleDigit(totalAbjad),
            
            letterStats = letterStats,
            hiddenLetters = hiddenLettersList,
            
            nooraniCount = nooraniCount,
            zulmaniCount = zulmaniCount,
            elementBalance = elementBalance,
            dominantLetter = TalismRules.getDominantLetter(ruleContext),
            
            numericalHarmony = numericalHarmony,
            numericalConflict = numericalConflict,
            
            recommendedPlanet = JafariRuleEngine.evaluatePlanet(ruleContext),
            recommendedElement = JafariRuleEngine.evaluateElement(ruleContext),
            recommendedShape = JafariRuleEngine.evaluateShape(ruleContext),
            recommendedInk = JafariRuleEngine.evaluateInk(ruleContext),
            recommendedDay = JafariRuleEngine.evaluateDay(ruleContext),
            recommendedHour = JafariRuleEngine.evaluateHour(ruleContext),
            recommendedIsm = JafariRuleEngine.evaluateIsmIlahi(ruleContext),
            recommendedMuwakkil = JafariRuleEngine.evaluateMuwakkil(ruleContext),
            recommendedDirection = JafariRuleEngine.evaluateDirection(ruleContext)
        )
    }
}
