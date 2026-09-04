package com.zakootaapps.ilmjafar.domain.ismazam.engine

import com.zakootaapps.ilmjafar.data.repository.asma_knowledge.AsmaKnowledgeRepository
import com.zakootaapps.ilmjafar.data.repository.jafr_knowledge.JafrKnowledgeRepository
import com.zakootaapps.ilmjafar.domain.abjad_knowledge.engine.AbjadEngine
import com.zakootaapps.ilmjafar.domain.abjad_knowledge.engine.takseer.TakseerEngine
import com.zakootaapps.ilmjafar.domain.abjad_knowledge.engine.bast.BastEngine
import com.zakootaapps.ilmjafar.domain.abjad_knowledge.engine.qabz.QabzEngine
import com.zakootaapps.ilmjafar.domain.abjad_knowledge.engine.qalb.QalbEngine
import com.zakootaapps.ilmjafar.domain.abjad_knowledge.engine.aks.AksEngine
import com.zakootaapps.ilmjafar.data.local.asma_knowledge.AsmaModelEntity
import com.zakootaapps.ilmjafar.util.AbjadCalculator
import kotlinx.coroutines.flow.firstOrNull
import kotlin.math.abs

data class IsmAzamMatch(
    val asma: AsmaModelEntity,
    val matchPercentage: Double,
    val confidenceScore: Double,
    val ruleReferences: List<String>,
    val element: String = "نامعلوم",
    val count: Int = 0
)

data class IsmAzamResult(
    val originalName: String,
    val normalizedName: String,
    val systemUrduName: String,
    val abjadTotal: Int,
    val matches: List<IsmAzamMatch>
)

class IsmAzamEngine(
    private val asmaRepository: AsmaKnowledgeRepository,
    private val jafrRepository: JafrKnowledgeRepository,
    private val abjadEngine: AbjadEngine,
    private val takseerEngine: TakseerEngine,
    private val bastEngine: BastEngine,
    private val qabzEngine: QabzEngine,
    private val qalbEngine: QalbEngine,
    private val aksEngine: AksEngine
) {
    suspend fun calculateIsmAzam(personName: String, mothersName: String, systemId: Int): IsmAzamResult {
        val isQamari = systemId != 3
        val isSaghir = systemId == 2
        val abjadType = when (systemId) {
            1 -> "ابجد کبیر"
            2 -> "ابجد صغیر"
            3 -> "ابجد شمسی"
            4 -> "ابجد قمری"
            else -> "ابجد کبیر"
        }

        val personAbjadTotal = AbjadCalculator.calculateAbjad(personName, isQamari, isSaghir).sumOf { it.second }
        val targetCount = if (mothersName.isNotBlank()) {
            val combinedAbjadTotal = AbjadCalculator.calculateAbjad(personName + mothersName, isQamari, isSaghir).sumOf { it.second }
            combinedAbjadTotal
        } else {
            personAbjadTotal
        }

        val allAsma = asmaRepository.getAllNames().firstOrNull() ?: emptyList()
        val bestMatch = allAsma.minByOrNull { abs((it.abjadValue) - targetCount) }
        
        val matches = if (bestMatch != null) {
            val attr = asmaRepository.getAttributeByAsmaId(bestMatch.id)
            listOf(
                IsmAzamMatch(
                    asma = bestMatch,
                    matchPercentage = if (bestMatch.abjadValue == targetCount) 100.0 else 90.0,
                    confidenceScore = 95.0,
                    ruleReferences = listOf(if (bestMatch.abjadValue == targetCount) "Exact Match" else "Closest Match"),
                    element = attr?.element ?: "نامعلوم",
                    count = targetCount
                )
            )
        } else emptyList()

        return IsmAzamResult(
            originalName = if (mothersName.isNotBlank()) "$personName + $mothersName" else personName,
            normalizedName = "", 
            systemUrduName = abjadType,
            abjadTotal = targetCount,
            matches = matches
        )
    }
}
