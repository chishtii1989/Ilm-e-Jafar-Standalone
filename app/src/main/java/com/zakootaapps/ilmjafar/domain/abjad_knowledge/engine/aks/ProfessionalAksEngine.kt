package com.zakootaapps.ilmjafar.domain.abjad_knowledge.engine.aks

import com.zakootaapps.ilmjafar.domain.abjad_knowledge.engine.AbjadEngine
import com.zakootaapps.ilmjafar.domain.abjad_knowledge.engine.AbjadResult
import com.zakootaapps.ilmjafar.data.repository.jafr_knowledge.JafrKnowledgeRepository

data class AksResult(
    val originalText: String,
    val normalizedText: String,
    val systemUrduName: String,
    val aksMethodUrduName: String,
    val originalSequence: List<Char>,
    val transformedSequence: List<Char>,
    val letterValues: List<Int>,
    val runningTotal: List<Int>,
    val finalTotal: Int
)

interface AksStrategy {
    val methodCode: String
    val methodUrduName: String
    fun apply(input: List<Char>, abjadEngine: AbjadEngine, systemId: Int): List<Char>
}

class HarfiAksStrategy : AksStrategy {
    override val methodCode = "HARFI"
    override val methodUrduName = "عکس حرفی"
    override fun apply(input: List<Char>, abjadEngine: AbjadEngine, systemId: Int): List<Char> {
        return input.reversed() // Completed logic
    }
}

class AdadiAksStrategy : AksStrategy {
    override val methodCode = "ADADI"
    override val methodUrduName = "عکس عددی"
    override fun apply(input: List<Char>, abjadEngine: AbjadEngine, systemId: Int): List<Char> {
        return input
    }
}

class KamilAksStrategy : AksStrategy {
    override val methodCode = "KAMIL"
    override val methodUrduName = "عکس کامل"
    override fun apply(input: List<Char>, abjadEngine: AbjadEngine, systemId: Int): List<Char> {
        return input
    }
}

class JuzviAksStrategy : AksStrategy {
    override val methodCode = "JUZVI"
    override val methodUrduName = "عکس جزوی"
    override fun apply(input: List<Char>, abjadEngine: AbjadEngine, systemId: Int): List<Char> {
        return input
    }
}

class MurakkabAksStrategy : AksStrategy {
    override val methodCode = "MURAKKAB"
    override val methodUrduName = "عکس مرکب"
    override fun apply(input: List<Char>, abjadEngine: AbjadEngine, systemId: Int): List<Char> {
        return input
    }
}

class AksFactory {
    private val strategies = mapOf(
        "HARFI" to HarfiAksStrategy(),
        "ADADI" to AdadiAksStrategy(),
        "KAMIL" to KamilAksStrategy(),
        "JUZVI" to JuzviAksStrategy(),
        "MURAKKAB" to MurakkabAksStrategy()
    )
    
    fun getStrategy(methodCode: String): AksStrategy {
        return strategies[methodCode] ?: throw IllegalArgumentException("Unsupported Aks method: $methodCode")
    }
    
    fun getAllStrategies(): List<AksStrategy> {
        return strategies.values.toList()
    }
}

class AksProcessor(
    private val abjadEngine: AbjadEngine,
    private val jafrRepository: JafrKnowledgeRepository,
    private val factory: AksFactory
) {
    suspend fun process(text: String, systemId: Int, aksMethodCode: String): AksResult {
        val abjadResult = abjadEngine.calculationService.performCalculation(text, systemId)
        val originalSequence = abjadResult.letterBreakdown.map { it.normalizedChar }
        
        val strategy = factory.getStrategy(aksMethodCode)
        val transformedSequence = strategy.apply(originalSequence, abjadEngine, systemId)
        
        val transformedText = transformedSequence.joinToString("")
        val transformedAbjadResult = abjadEngine.calculationService.performCalculation(transformedText, systemId)
        
        val letterValues = transformedAbjadResult.letterBreakdown.map { it.value }
        val runningTotals = transformedAbjadResult.letterBreakdown.map { it.runningTotal }
        
        return AksResult(
            originalText = text,
            normalizedText = abjadResult.normalizedText,
            systemUrduName = abjadResult.system.urduName,
            aksMethodUrduName = strategy.methodUrduName,
            originalSequence = originalSequence,
            transformedSequence = transformedSequence,
            letterValues = letterValues,
            runningTotal = runningTotals,
            finalTotal = transformedAbjadResult.finalTotal
        )
    }
}

class AksNormalizationService(private val abjadEngine: AbjadEngine) {
    fun normalize(text: String): String = abjadEngine.normalizationService.normalize(text)
}

class AksValidationService(private val abjadEngine: AbjadEngine) {
    fun isValid(char: Char): Boolean = abjadEngine.validationService.isValid(char)
}

class AksFormatter {
    fun format(result: AksResult): String {
        val sb = StringBuilder()
        sb.append("System: ${result.systemUrduName}\n")
        sb.append("Aks Method: ${result.aksMethodUrduName}\n")
        sb.append("Original: ${result.originalSequence.joinToString(" ")}\n")
        sb.append("Transformed: ${result.transformedSequence.joinToString(" ")}\n")
        sb.append("Final Total: ${result.finalTotal}\n")
        return sb.toString()
    }
}

class AksCalculationService(
    private val processor: AksProcessor
) {
    suspend fun calculate(text: String, systemId: Int, aksMethodCode: String): AksResult {
        return processor.process(text, systemId, aksMethodCode)
    }
}

class AksEngine(
    private val abjadEngine: AbjadEngine,
    private val jafrRepository: JafrKnowledgeRepository
) {
    private val factory = AksFactory()
    private val processor = AksProcessor(abjadEngine, jafrRepository, factory)
    
    val calculationService = AksCalculationService(processor)
    val normalizationService = AksNormalizationService(abjadEngine)
    val validationService = AksValidationService(abjadEngine)
    val formatter = AksFormatter()
}
