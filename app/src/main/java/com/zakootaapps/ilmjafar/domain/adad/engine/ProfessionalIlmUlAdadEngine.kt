package com.zakootaapps.ilmjafar.domain.adad.engine

import android.content.Context
import com.zakootaapps.ilmjafar.domain.integration.orchestrator.UnifiedKnowledgeEngineOrchestrator
import com.zakootaapps.ilmjafar.domain.adad.engine.data.NumerologyDataProvider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

data class NumerologySystemResult(
    val birthNumber: Int,
    val destinyNumber: Int,
    val lifePathNumber: Int,
    val soulNumber: Int,
    val expressionNumber: Int,
    val personalityNumber: Int,
    val luckyNumbers: String,
    val career: String,
    val business: String,
    val finance: String,
    val relationships: String,
    val marriage: String,
    val health: String,
    val personality: String,
    val hiddenTalent: String,
    val lifePurpose: String,
    val successFormula: String,
    val practicalAdvice: String,
    val strengths: String,
    val weaknesses: String,
    val suggestions: String,
    val spiritualGuidance: String,
    val luckyColors: String,
    val luckyDays: String,
    val luckyDirections: String,
    val unfavourableNumbers: String,
    val luckyGemstone: String,
    val detailedReport: String
)

data class IlmUlAdadResult(
    val rootNumber: Int,
    val destinyNumber: Int,
    val isDobProvided: Boolean,
    val isNameProvided: Boolean,
    val numerology: NumerologySystemResult?,
    val dobNumerology: com.zakootaapps.ilmjafar.domain.adad.engine.dob.DobNumerologyResult? = null,
    
    // Legacy fields for PDF generator compatibility
    val strongPoints: String = "",
    val weakPoints: String = "",
    val suggestions: String = "",
    val successFormula: String = "",
    val luckyPlanet: String = "",
    val luckyElement: String = "",
    val luckyColors: String = "",
    val luckyGemstone: String = ""
)

class ProfessionalIlmUlAdadEngine(
    private val context: Context,
    private val orchestrator: UnifiedKnowledgeEngineOrchestrator
) {

    suspend fun calculate(
        method: String,
        name: String,
        mothersName: String,
        dob: String, // format DD/MM/YYYY
        systemId: Int
    ): IlmUlAdadResult = withContext(Dispatchers.IO) {
        val finalName = name.trim()
        val finalMothersName = mothersName.trim()
        val isNameProvided = finalName.isNotBlank() && finalMothersName.isNotBlank()
        val isDobProvided = dob.isNotBlank() && dob.contains("/")

        if (method == "DOB") {
            val dobResult = com.zakootaapps.ilmjafar.domain.adad.engine.dob.DobReportGenerator.generate(context, dob)
            return@withContext IlmUlAdadResult(
                rootNumber = dobResult.birthNumber,
                destinyNumber = 0,
                isDobProvided = isDobProvided,
                isNameProvided = isNameProvided,
                numerology = null,
                dobNumerology = dobResult
            )
        } else {
            val nameTotal = calculateUrduAbjadTotal(finalName)
            val motherTotal = calculateUrduAbjadTotal(finalMothersName)
            
            val destinyNumber = reduceNumerology(nameTotal + motherTotal)
            val expressionNumber = reduceNumerology(nameTotal)
            val soulNumber = reduceNumerology(calculateUrduSoulTotal(finalName))
            val personalityNumber = reduceNumerology(calculateUrduPersonalityTotal(finalName))

            val birthNumber = reduceNumerology(destinyNumber)
            val lifePathNumber = birthNumber

            val profile = NumerologyDataProvider.getProfile(birthNumber)

            val numResult = NumerologySystemResult(
                birthNumber = birthNumber, 
                destinyNumber = destinyNumber, 
                lifePathNumber = lifePathNumber,
                soulNumber = soulNumber, 
                expressionNumber = expressionNumber, 
                personalityNumber = personalityNumber,
                luckyNumbers = profile.luckyNumbers,
                career = profile.career, 
                business = profile.business, 
                finance = profile.finance, 
                relationships = profile.relationships,
                marriage = profile.marriage,
                health = profile.health, 
                personality = profile.personality, 
                hiddenTalent = profile.hiddenTalent, 
                lifePurpose = profile.lifePurpose,
                successFormula = profile.successFormula, 
                practicalAdvice = profile.practicalAdvice,
                strengths = profile.strengths, 
                weaknesses = profile.weaknesses, 
                suggestions = profile.suggestions,
                spiritualGuidance = profile.spiritualGuidance,
                luckyColors = profile.luckyColors,
                luckyDays = profile.luckyDays,
                luckyDirections = profile.luckyDirections,
                unfavourableNumbers = profile.unfavourableNumbers,
                luckyGemstone = profile.luckyGemstone,
                detailedReport = profile.detailedReport
            )
            
            return@withContext IlmUlAdadResult(
                rootNumber = birthNumber, 
                destinyNumber = destinyNumber,
                isDobProvided = isDobProvided, 
                isNameProvided = isNameProvided,
                numerology = numResult,
                strongPoints = profile.strengths,
                weakPoints = profile.weaknesses,
                suggestions = profile.suggestions,
                successFormula = profile.successFormula,
                luckyPlanet = profile.planet, luckyElement = profile.element, luckyColors = profile.luckyColors, luckyGemstone = profile.luckyGemstone
            )
        }
    }

    private fun reduceNumerology(num: Int): Int {
        if (num == 11 || num == 22 || num == 33) return num
        var n = num
        while (n > 9) {
            if (n == 11 || n == 22 || n == 33) return n
            n = n.toString().sumOf { it.toString().toInt() }
        }
        return if (n == 0) 1 else n
    }
    
    private fun getAbjadKabeerValue(char: Char): Int {
        return when (char.lowercaseChar()) {
            'ا', 'أ', 'إ', 'آ', 'ء' -> 1
            'ب', 'پ' -> 2
            'ج', 'چ' -> 3
            'د', 'ڈ' -> 4
            'ہ', 'ھ', 'ۃ' -> 5
            'و' -> 6
            'ز', 'ژ' -> 7
            'ح' -> 8
            'ط' -> 9
            'ی', 'ے', 'ئ' -> 10
            'ک', 'گ' -> 20
            'ل' -> 30
            'م' -> 40
            'ن' -> 50
            'س' -> 60
            'ع' -> 70
            'ف' -> 80
            'ص' -> 90
            'ق' -> 100
            'ر', 'ڑ' -> 200
            'ش' -> 300
            'ت', 'ٹ' -> 400
            'ث' -> 500
            'خ' -> 600
            'ذ' -> 700
            'ض' -> 800
            'ظ' -> 900
            'غ' -> 1000
            'a', 'j', 's' -> 1
            'b', 'k', 't' -> 2
            'c', 'l', 'u' -> 3
            'd', 'm', 'v' -> 4
            'e', 'n', 'w' -> 5
            'f', 'o', 'x' -> 6
            'g', 'p', 'y' -> 7
            'h', 'q', 'z' -> 8
            'i', 'r' -> 9
            else -> 0
        }
    }
    
    private fun calculateUrduAbjadTotal(text: String): Int {
        var total = 0
        for (char in text) {
            total += getAbjadKabeerValue(char)
        }
        return total
    }
    
    private fun isUrduVowel(char: Char): Boolean {
        return when (char.lowercaseChar()) {
            'ا', 'أ', 'إ', 'آ', 'و', 'ی', 'ے', 'ئ', 'a', 'e', 'i', 'o', 'u' -> true
            else -> false
        }
    }
    
    private fun calculateUrduSoulTotal(name: String): Int {
        var total = 0
        for (char in name) {
            if (isUrduVowel(char)) {
                total += getAbjadKabeerValue(char)
            }
        }
        return total
    }
    
    private fun calculateUrduPersonalityTotal(name: String): Int {
        var total = 0
        for (char in name) {
            if (!isUrduVowel(char) && char != ' ') {
                total += getAbjadKabeerValue(char)
            }
        }
        return total
    }
}
