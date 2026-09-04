package com.zakootaapps.ilmjafar.domain.abjad_knowledge.engine.bast

import com.zakootaapps.ilmjafar.domain.abjad_knowledge.engine.AbjadEngine
import com.zakootaapps.ilmjafar.domain.abjad_knowledge.engine.AbjadResult
import com.zakootaapps.ilmjafar.data.repository.jafr_knowledge.JafrKnowledgeRepository

data class BastResult(
    val originalText: String,
    val normalizedText: String,
    val systemUrduName: String,
    val bastMethodUrduName: String,
    val originalSequence: List<Char>,
    val transformedSequence: List<Char>,
    val letterValues: List<Int>,
    val runningTotal: List<Int>,
    val finalTotal: Int
)

interface BastStrategy {
    val methodCode: String
    val methodUrduName: String
    fun apply(input: List<Char>, abjadEngine: AbjadEngine, systemId: Int): List<Char>
}

class HarfiBastStrategy : BastStrategy {
    override val methodCode = "HARFI"
    override val methodUrduName = "بسط حرفی"
    override fun apply(input: List<Char>, abjadEngine: AbjadEngine, systemId: Int): List<Char> {
        return input
    }
}

class AdadiBastStrategy : BastStrategy {
    override val methodCode = "ADADI"
    override val methodUrduName = "بسط عددی"
    override fun apply(input: List<Char>, abjadEngine: AbjadEngine, systemId: Int): List<Char> {
        return input
    }
}

class BastFactory {
    private val strategies = mapOf(
        "HARFI" to HarfiBastStrategy(),
        "ADADI" to AdadiBastStrategy()
    )
    
    fun getStrategy(methodCode: String): BastStrategy {
        return strategies[methodCode] ?: throw IllegalArgumentException("Unsupported Bast method: \$methodCode")
    }
    
    fun getAllStrategies(): List<BastStrategy> {
        return strategies.values.toList()
    }
}

class BastProcessor(
    private val abjadEngine: AbjadEngine,
    private val jafrRepository: JafrKnowledgeRepository,
    private val factory: BastFactory
) {
    suspend fun process(text: String, systemId: Int, bastMethodCode: String): BastResult {
        val abjadResult = abjadEngine.calculationService.performCalculation(text, systemId)
        val originalSequence = abjadResult.letterBreakdown.map { it.normalizedChar }
        
        val strategy = factory.getStrategy(bastMethodCode)
        val transformedSequence = strategy.apply(originalSequence, abjadEngine, systemId)
        
        val transformedText = transformedSequence.joinToString("")
        val transformedAbjadResult = abjadEngine.calculationService.performCalculation(transformedText, systemId)
        
        val letterValues = transformedAbjadResult.letterBreakdown.map { it.value }
        val runningTotals = transformedAbjadResult.letterBreakdown.map { it.runningTotal }
        
        return BastResult(
            originalText = text,
            normalizedText = abjadResult.normalizedText,
            systemUrduName = abjadResult.system.urduName,
            bastMethodUrduName = strategy.methodUrduName,
            originalSequence = originalSequence,
            transformedSequence = transformedSequence,
            letterValues = letterValues,
            runningTotal = runningTotals,
            finalTotal = transformedAbjadResult.finalTotal
        )
    }
}

class BastNormalizationService(private val abjadEngine: AbjadEngine) {
    fun normalize(text: String): String = abjadEngine.normalizationService.normalize(text)
}

class BastValidationService(private val abjadEngine: AbjadEngine) {
    fun isValid(char: Char): Boolean = abjadEngine.validationService.isValid(char)
}

class BastFormatter {
    fun format(result: BastResult): String {
        val sb = StringBuilder()
        sb.append("System: ${result.systemUrduName}\n")
        sb.append("Bast Method: ${result.bastMethodUrduName}\n")
        sb.append("Original: ${result.originalSequence.joinToString(" ")}\n")
        sb.append("Transformed: ${result.transformedSequence.joinToString(" ")}\n")
        sb.append("Final Total: ${result.finalTotal}\n")
        return sb.toString()
    }
}

class BastCalculationService(
    private val processor: BastProcessor
) {
    suspend fun calculate(text: String, systemId: Int, bastMethodCode: String): BastResult {
        return processor.process(text, systemId, bastMethodCode)
    }
}

class BastEngine(
    private val abjadEngine: AbjadEngine,
    private val jafrRepository: JafrKnowledgeRepository
) {
    private val factory = BastFactory()
    private val processor = BastProcessor(abjadEngine, jafrRepository, factory)
    
    val calculationService = BastCalculationService(processor)
    val normalizationService = BastNormalizationService(abjadEngine)
    val validationService = BastValidationService(abjadEngine)
    val formatter = BastFormatter()
}
