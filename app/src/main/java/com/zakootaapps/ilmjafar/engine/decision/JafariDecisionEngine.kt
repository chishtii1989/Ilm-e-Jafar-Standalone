package com.zakootaapps.ilmjafar.engine.decision

import com.zakootaapps.ilmjafar.engine.rules.*
import com.zakootaapps.ilmjafar.util.TalismEngine

object JafariDecisionEngine {
    fun decideBestFormula(request: DecisionRequest): FormulaCandidate {
        val candidates = mutableListOf<FormulaCandidate>()
        
        candidates.add(evaluateFormula("Standard Complete", request, includeNames = true, includePurpose = true))
        
        if (request.isLoveMode || request.matloobName.isNotBlank()) {
            candidates.add(evaluateFormula("Love Formula", request, includeNames = true, includePurpose = true, forceLove = true))
        }
        
        candidates.add(evaluateFormula("Purpose Focus", request, includeNames = false, includePurpose = true))
        candidates.add(evaluateFormula("Name Focus", request, includeNames = true, includePurpose = false))

        val best = FormulaRankingSystem.rank(candidates) ?: candidates.first()
        return best
    }
    
    private fun evaluateFormula(
        formulaName: String, 
        request: DecisionRequest, 
        includeNames: Boolean, 
        includePurpose: Boolean,
        forceLove: Boolean = false
    ): FormulaCandidate {
        
        var totalAbjad = 0
        var allText = ""
        
        if (includeNames) {
            val tName = request.talibName.replace(" ", "")
            val tMother = request.talibMother.replace(" ", "")
            allText += tName + tMother
            
            if (forceLove || request.isLoveMode) {
                val mName = request.matloobName.replace(" ", "")
                val mMother = request.matloobMother.replace(" ", "")
                allText += mName + mMother
            }
        }
        
        val actualPurpose = if (request.customPurpose.isNotBlank()) request.customPurpose else request.purpose
        
        if (includePurpose) {
            val purposeText = actualPurpose.replace(" ", "")
            allText += purposeText
        }
        
        totalAbjad = TalismEngine.calculateAbjad(allText)
        val lettersCount = allText.length
        
        if (totalAbjad == 0) totalAbjad = 786 
        
        val purposeCategory = PurposeRules.classifyPurpose(actualPurpose, "عام مقصد")
        val classicalMatch = request.classicalKnowledge.find { it.category == purposeCategory }
        
        val ruleContext = RuleContext(
            totalAbjad = totalAbjad,
            lettersCount = lettersCount,
            purpose = purposeCategory,
            isLoveMode = forceLove || request.isLoveMode,
            databaseKnowledge = classicalMatch,
            cachedKnowledge = request.cachedKnowledge
        )
        
        val element = JafariRuleEngine.evaluateElement(ruleContext)
        val planet = JafariRuleEngine.evaluatePlanet(ruleContext)
        val day = JafariRuleEngine.evaluateDay(ruleContext)
        val hour = JafariRuleEngine.evaluateHour(ruleContext)
        val ink = JafariRuleEngine.evaluateInk(ruleContext)
        val paper = JafariRuleEngine.evaluatePaper(ruleContext)
        val direction = JafariRuleEngine.evaluateDirection(ruleContext)
        val shape = JafariRuleEngine.evaluateShape(ruleContext)
        val dominantLetter = TalismRules.getDominantLetter(ruleContext)
        val ismIlahi = JafariRuleEngine.evaluateIsmIlahi(ruleContext)
        val muwakkil = JafariRuleEngine.evaluateMuwakkil(ruleContext)
        
        val bukhoor = classicalMatch?.recommendedIncense ?: "لوبان اور صندل"
        
        val writingOrientation = when {
            element.contains("آتش") -> "اوپر کی طرف"
            element.contains("باد") -> "دائیں سے بائیں"
            element.contains("آب") -> "بائیں سے دائیں"
            else -> "نیچے کی طرف"
        }
        
        var harmonyScore = 50
        var conflictScore = 0
        
        val nameAbjad = TalismEngine.calculateAbjad(request.talibName.replace(" ", ""))
        val nameElement = ElementRules.evaluate(RuleContext(totalAbjad = nameAbjad))
        val purposeAbjad = TalismEngine.calculateAbjad(actualPurpose.replace(" ", ""))
        val purposeElement = ElementRules.evaluate(RuleContext(totalAbjad = purposeAbjad))
        
        val elementScore = RulePrioritySystem.calculateElementHarmony(nameElement, purposeElement)
        if (elementScore > 0) harmonyScore += elementScore else conflictScore += Math.abs(elementScore)
        
        val planetIdx = PlanetRules.getPlanetIndex(totalAbjad)
        val expectedDay = listOf("ہفتہ", "جمعرات", "منگل", "اتوار", "جمعہ", "بدھ", "پیر")[planetIdx]
        val planetStrength = if (day.contains(expectedDay)) "شرف (Exalted)" else "عروج (Normal)"
        
        if (day.contains(expectedDay)) {
            harmonyScore += 10
        } else {
            conflictScore += 5
        }
        
        var confidenceScore = harmonyScore - conflictScore
        if (confidenceScore > 100) confidenceScore = 100
        if (confidenceScore < 0) confidenceScore = 0
        
        val talismPriority = if (confidenceScore > 80) 1 else if (confidenceScore > 50) 2 else 3
        val naqshPriority = if (totalAbjad % 4 == 0) 1 else 2 
        
        return FormulaCandidate(
            formulaName = formulaName,
            totalAbjad = totalAbjad,
            lettersCount = lettersCount,
            dominantLetter = dominantLetter,
            dominantElement = element,
            planet = planet,
            planetStrength = planetStrength,
            day = day,
            hour = hour,
            direction = direction,
            shape = shape,
            ink = ink,
            paper = paper,
            bukhoor = bukhoor,
            ismIlahi = ismIlahi,
            muwakkil = muwakkil,
            harmonyScore = harmonyScore,
            conflictScore = conflictScore,
            confidenceScore = confidenceScore,
            talismPriority = talismPriority,
            naqshPriority = naqshPriority,
            writingOrientation = writingOrientation
        )
    }
}
