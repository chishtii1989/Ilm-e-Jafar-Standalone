package com.zakootaapps.ilmjafar.domain.abjad_knowledge.engine.qalb

import com.zakootaapps.ilmjafar.domain.abjad_knowledge.engine.AbjadEngine
import com.zakootaapps.ilmjafar.domain.abjad_knowledge.engine.AbjadResult
import com.zakootaapps.ilmjafar.data.repository.jafr_knowledge.JafrKnowledgeRepository

data class QalbResult(
    val originalText: String,
    val normalizedText: String,
    val systemUrduName: String,
    val qalbMethodUrduName: String,
    val originalSequence: List<Char>,
    val transformedSequence: List<Char>,
    val letterValues: List<Int>,
    val runningTotal: List<Int>,
    val finalTotal: Int
)

interface QalbStrategy {
    val methodCode: String
    val methodUrduName: String
    fun apply(input: List<Char>, abjadEngine: AbjadEngine, systemId: Int): List<Char>
}

class KulliQalbStrategy : QalbStrategy {
    override val methodCode = "KULLI"
    override val methodUrduName = "قلب کلی"
    override fun apply(input: List<Char>, abjadEngine: AbjadEngine, systemId: Int): List<Char> {
        return input.reversed()
    }
}

class JuzviQalbStrategy : QalbStrategy {
    override val methodCode = "JUZVI"
    override val methodUrduName = "قلب جزوی"
    override fun apply(input: List<Char>, abjadEngine: AbjadEngine, systemId: Int): List<Char> {
        return input
    }
}

class MutaakharQalbStrategy : QalbStrategy {
    override val methodCode = "MUTAAKHAR"
    override val methodUrduName = "قلب متاخر"
    override fun apply(input: List<Char>, abjadEngine: AbjadEngine, systemId: Int): List<Char> {
        return input
    }
}

class QalbFactory {
    private val strategies = mapOf(
        "KULLI" to KulliQalbStrategy(),
        "JUZVI" to JuzviQalbStrategy(),
        "MUTAAKHAR" to MutaakharQalbStrategy()
    )
    
    fun getStrategy(methodCode: String): QalbStrategy {
        return strategies[methodCode] ?: throw IllegalArgumentException("Unsupported Qalb method: $methodCode")
    }
    
    fun getAllStrategies(): List<QalbStrategy> {
        return strategies.values.toList()
    }
}

class QalbProcessor(
    private val abjadEngine: AbjadEngine,
    private val jafrRepository: JafrKnowledgeRepository,
    private val factory: QalbFactory
) {
    suspend fun process(text: String, systemId: Int, qalbMethodCode: String): QalbResult {
        val abjadResult = abjadEngine.calculationService.performCalculation(text, systemId)
        val originalSequence = abjadResult.letterBreakdown.map { it.normalizedChar }
        
        val strategy = factory.getStrategy(qalbMethodCode)
        val transformedSequence = strategy.apply(originalSequence, abjadEngine, systemId)
        
        val transformedText = transformedSequence.joinToString("")
        val transformedAbjadResult = abjadEngine.calculationService.performCalculation(transformedText, systemId)
        
        val letterValues = transformedAbjadResult.letterBreakdown.map { it.value }
        val runningTotals = transformedAbjadResult.letterBreakdown.map { it.runningTotal }
        
        return QalbResult(
            originalText = text,
            normalizedText = abjadResult.normalizedText,
            systemUrduName = abjadResult.system.urduName,
            qalbMethodUrduName = strategy.methodUrduName,
            originalSequence = originalSequence,
            transformedSequence = transformedSequence,
            letterValues = letterValues,
            runningTotal = runningTotals,
            finalTotal = transformedAbjadResult.finalTotal
        )
    }
}

class QalbNormalizationService(private val abjadEngine: AbjadEngine) {
    fun normalize(text: String): String = abjadEngine.normalizationService.normalize(text)
}

class QalbValidationService(private val abjadEngine: AbjadEngine) {
    fun isValid(char: Char): Boolean = abjadEngine.validationService.isValid(char)
}

class QalbFormatter {
    fun format(result: QalbResult): String {
        val sb = StringBuilder()
        sb.append("System: ${result.systemUrduName}\n")
        sb.append("Qalb Method: ${result.qalbMethodUrduName}\n")
        sb.append("Original: ${result.originalSequence.joinToString(" ")}\n")
        sb.append("Transformed: ${result.transformedSequence.joinToString(" ")}\n")
        sb.append("Final Total: ${result.finalTotal}\n")
        return sb.toString()
    }
}

class QalbCalculationService(
    private val processor: QalbProcessor
) {
    suspend fun calculate(text: String, systemId: Int, qalbMethodCode: String): QalbResult {
        return processor.process(text, systemId, qalbMethodCode)
    }
}

class QalbEngine(
    private val abjadEngine: AbjadEngine,
    private val jafrRepository: JafrKnowledgeRepository
) {
    private val factory = QalbFactory()
    private val processor = QalbProcessor(abjadEngine, jafrRepository, factory)
    
    val calculationService = QalbCalculationService(processor)
    val normalizationService = QalbNormalizationService(abjadEngine)
    val validationService = QalbValidationService(abjadEngine)
    val formatter = QalbFormatter()
}
