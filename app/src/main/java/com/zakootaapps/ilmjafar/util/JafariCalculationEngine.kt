package com.zakootaapps.ilmjafar.util

import com.zakootaapps.ilmjafar.data.local.ClassicalTalismKnowledgeEntity
import com.zakootaapps.ilmjafar.engine.rules.*

enum class EngineMode {
    AUTOMATIC, MANUAL
}

data class TalismRequest(
    val engineMode: EngineMode,
    val talibName: String,
    val talibMother: String,
    val isLoveMode: Boolean,
    val matloobName: String = "",
    val matloobMother: String = "",
    val predefinedPurpose: String = "",
    val customPurpose: String = "",
    
    // Knowledge Base Data
    val databaseKnowledge: ClassicalTalismKnowledgeEntity? = null,
    
    // Manual overrides
    val manualTawkeel: String = "",
    val manualIsm: String = "",
    val manualMuwakkil: String = "",
    val manualAbjadTotal: Int? = null,
    val manualShape: String = "",
    val manualInk: String = "",
    val manualPaper: String = "",
    val manualPlanet: String = "",
    val manualDay: String = "",
    val manualHour: String = ""
)

data class TalismCalculationData(
    val totalAbjad: Int,
    val lettersCount: Int,
    val reducedNumber: Int,
    val spiritualNumber: Int,
    val dominantLetter: String,
    val dominantElement: String,
    val planet: String,
    val day: String,
    val hour: String,
    val recommendedInk: String,
    val recommendedPaper: String,
    val recommendedDirection: String,
    val recommendedShape: String,
    val ismIlahi: String,
    val muwakkil: String,
    val tawkeel: String,
    val purposeCategory: String
)

object JafariCalculationEngine {
    
    // Knowledge Base Cache
    var cachedKnowledge: List<com.zakootaapps.ilmjafar.data.local.JafariKnowledgeRecordEntity> = emptyList()

    fun calculate(request: TalismRequest): TalismCalculationData {
        val purpose = if (request.customPurpose.isNotBlank()) request.customPurpose else request.predefinedPurpose
        val purposeCategory = request.databaseKnowledge?.category ?: PurposeRules.classifyPurpose(purpose, "عام مقصد")
        
        var totalAbjad = 0
        var lettersCount = 0
        
        val tName = request.talibName.replace(" ", "")
        val tMother = request.talibMother.replace(" ", "")
        val mName = request.matloobName.replace(" ", "")
        val mMother = request.matloobMother.replace(" ", "")
        
        val allText = if (request.isLoveMode) {
            tName + tMother + mName + mMother
        } else {
            tName + tMother
        }
        
        totalAbjad += TalismEngine.calculateAbjad(allText)
        lettersCount += allText.length
        
        val purposeText = purpose.replace(" ", "")
        val maqsadAbjad = TalismEngine.calculateAbjad(purposeText)
        totalAbjad += maqsadAbjad
        lettersCount += purposeText.length
        
        var verseAbjad = 0
        if (request.engineMode == EngineMode.AUTOMATIC) {
            val verse = request.databaseKnowledge?.recommendedQuranicVerses ?: TalismEngine.getVerse(purposeCategory)
            verseAbjad = TalismEngine.calculateAbjad(verse.replace(" ", ""))
            totalAbjad += verseAbjad
            lettersCount += verse.replace(" ", "").length
        }
        
        if (request.engineMode == EngineMode.MANUAL && request.manualAbjadTotal != null) {
            totalAbjad = request.manualAbjadTotal
        }
        
        val ruleContext = RuleContext(
            totalAbjad = totalAbjad,
            lettersCount = lettersCount,
            purpose = purposeCategory,
            isLoveMode = request.isLoveMode,
            databaseKnowledge = request.databaseKnowledge,
            cachedKnowledge = cachedKnowledge
        )
        
        val reducedNumber = AbjadRules.reduceToSingleDigit(totalAbjad)
        val spiritualNumber = AbjadRules.calculateSpiritualNumber(totalAbjad, lettersCount)
        
        val element = JafariRuleEngine.evaluateElement(ruleContext)
        val planet = JafariRuleEngine.evaluatePlanet(ruleContext)
        val day = JafariRuleEngine.evaluateDay(ruleContext)
        val hour = JafariRuleEngine.evaluateHour(ruleContext)
        val ink = JafariRuleEngine.evaluateInk(ruleContext)
        val paper = JafariRuleEngine.evaluatePaper(ruleContext)
        val direction = JafariRuleEngine.evaluateDirection(ruleContext)
        val shape = JafariRuleEngine.evaluateShape(ruleContext)
        val dominantLetter = TalismRules.getDominantLetter(ruleContext)
        
        var ism = JafariRuleEngine.evaluateIsmIlahi(ruleContext)
        if (request.engineMode == EngineMode.MANUAL && request.manualIsm.isNotBlank()) {
            ism = request.manualIsm
        }

        val muwakkil = if (request.engineMode == EngineMode.MANUAL && request.manualMuwakkil.isNotBlank()) {
            request.manualMuwakkil
        } else {
            JafariRuleEngine.evaluateMuwakkil(ruleContext)
        }
        
        val tawkeel = if (request.engineMode == EngineMode.MANUAL && request.manualTawkeel.isNotBlank()) {
            request.manualTawkeel
        } else if (request.engineMode == EngineMode.AUTOMATIC && request.databaseKnowledge?.classicalTawkeel?.isNotBlank() == true) {
            request.databaseKnowledge.classicalTawkeel
        } else {
            TalismEngine.generateTawkeel(purpose, request.talibName, request.talibMother, request.matloobName, request.matloobMother)
        }
        
        return TalismCalculationData(
            totalAbjad = totalAbjad,
            lettersCount = lettersCount,
            reducedNumber = reducedNumber,
            spiritualNumber = spiritualNumber,
            dominantLetter = dominantLetter,
            dominantElement = element,
            planet = if (request.engineMode == EngineMode.MANUAL && request.manualPlanet.isNotBlank()) request.manualPlanet else planet,
            day = if (request.engineMode == EngineMode.MANUAL && request.manualDay.isNotBlank()) request.manualDay else day,
            hour = if (request.engineMode == EngineMode.MANUAL && request.manualHour.isNotBlank()) request.manualHour else hour,
            recommendedInk = if (request.engineMode == EngineMode.MANUAL && request.manualInk.isNotBlank()) request.manualInk else ink,
            recommendedPaper = if (request.engineMode == EngineMode.MANUAL && request.manualPaper.isNotBlank()) request.manualPaper else paper,
            recommendedDirection = direction,
            recommendedShape = if (request.engineMode == EngineMode.MANUAL && request.manualShape.isNotBlank()) request.manualShape else shape,
            ismIlahi = ism,
            muwakkil = muwakkil,
            tawkeel = tawkeel,
            purposeCategory = purposeCategory
        )
    }
}
