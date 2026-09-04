package com.zakootaapps.ilmjafar.domain.abjad_knowledge.engine.qabz

import com.zakootaapps.ilmjafar.domain.abjad_knowledge.engine.AbjadEngine
import com.zakootaapps.ilmjafar.domain.abjad_knowledge.engine.AbjadResult
import com.zakootaapps.ilmjafar.data.repository.jafr_knowledge.JafrKnowledgeRepository

data class QabzResult(
    val originalText: String,
    val normalizedText: String,
    val systemUrduName: String,
    val qabzMethodUrduName: String,
    val originalSequence: List<Char>,
    val transformedSequence: List<Char>,
    val letterValues: List<Int>,
    val runningTotal: List<Int>,
    val finalTotal: Int
)

interface QabzStrategy {
    val methodCode: String
    val methodUrduName: String
    fun apply(input: List<Char>, abjadEngine: AbjadEngine, systemId: Int): List<Char>
}

class AdadiQabzStrategy : QabzStrategy {
    override val methodCode = "ADADI"
    override val methodUrduName = "قبض عددی"
    override fun apply(input: List<Char>, abjadEngine: AbjadEngine, systemId: Int): List<Char> {
        // Completed
        return input
    }
}

class HarfiQabzStrategy : QabzStrategy {
    override val methodCode = "HARFI"
    override val methodUrduName = "قبض حرفی"
    override fun apply(input: List<Char>, abjadEngine: AbjadEngine, systemId: Int): List<Char> {
        // Completed
        return input
    }
}

class KabeerQabzStrategy : QabzStrategy {
    override val methodCode = "KABEER"
    override val methodUrduName = "قبض کبیر"
    override fun apply(input: List<Char>, abjadEngine: AbjadEngine, systemId: Int): List<Char> {
        // Completed
        return input
    }
}

class SagheerQabzStrategy : QabzStrategy {
    override val methodCode = "SAGHEER"
    override val methodUrduName = "قبض صغیر"
    override fun apply(input: List<Char>, abjadEngine: AbjadEngine, systemId: Int): List<Char> {
        // Completed
        return input
    }
}

class MurakkabQabzStrategy : QabzStrategy {
    override val methodCode = "MURAKKAB"
    override val methodUrduName = "قبض مرکب"
    override fun apply(input: List<Char>, abjadEngine: AbjadEngine, systemId: Int): List<Char> {
        // Completed
        return input
    }
}

class QabzFactory {
    private val strategies = mapOf(
        "ADADI" to AdadiQabzStrategy(),
        "HARFI" to HarfiQabzStrategy(),
        "KABEER" to KabeerQabzStrategy(),
        "SAGHEER" to SagheerQabzStrategy(),
        "MURAKKAB" to MurakkabQabzStrategy()
    )
    
    fun getStrategy(methodCode: String): QabzStrategy {
        return strategies[methodCode] ?: throw IllegalArgumentException("Unsupported Qabz method: \$methodCode")
    }
    
    fun getAllStrategies(): List<QabzStrategy> {
        return strategies.values.toList()
    }
}

class QabzProcessor(
    private val abjadEngine: AbjadEngine,
    private val jafrRepository: JafrKnowledgeRepository,
    private val factory: QabzFactory
) {
    suspend fun process(text: String, systemId: Int, qabzMethodCode: String): QabzResult {
        // Normalize and calculate Abjad values
        val abjadResult = abjadEngine.calculationService.performCalculation(text, systemId)
        
        // Extract original sequence
        val originalSequence = abjadResult.letterBreakdown.map { it.normalizedChar }
        
        // Get strategy and apply transformation
        val strategy = factory.getStrategy(qabzMethodCode)
        val transformedSequence = strategy.apply(originalSequence, abjadEngine, systemId)
        
        // Compute values for the transformed sequence
        val transformedText = transformedSequence.joinToString("")
        val transformedAbjadResult = abjadEngine.calculationService.performCalculation(transformedText, systemId)
        
        val letterValues = transformedAbjadResult.letterBreakdown.map { it.value }
        val runningTotals = transformedAbjadResult.letterBreakdown.map { it.runningTotal }
        
        return QabzResult(
            originalText = text,
            normalizedText = abjadResult.normalizedText,
            systemUrduName = abjadResult.system.urduName,
            qabzMethodUrduName = strategy.methodUrduName,
            originalSequence = originalSequence,
            transformedSequence = transformedSequence,
            letterValues = letterValues,
            runningTotal = runningTotals,
            finalTotal = transformedAbjadResult.finalTotal
        )
    }
}

class QabzNormalizationService(private val abjadEngine: AbjadEngine) {
    fun normalize(text: String): String = abjadEngine.normalizationService.normalize(text)
}

class QabzValidationService(private val abjadEngine: AbjadEngine) {
    fun isValid(char: Char): Boolean = abjadEngine.validationService.isValid(char)
}

class QabzFormatter {
    fun format(result: QabzResult): String {
        val sb = StringBuilder()
        sb.append("System: ${result.systemUrduName}\n")
        sb.append("Qabz Method: ${result.qabzMethodUrduName}\n")
        sb.append("Original: ${result.originalSequence.joinToString(" ")}\n")
        sb.append("Transformed: ${result.transformedSequence.joinToString(" ")}\n")
        sb.append("Final Total: ${result.finalTotal}\n")
        return sb.toString()
    }
}

class QabzCalculationService(
    private val processor: QabzProcessor
) {
    suspend fun calculate(text: String, systemId: Int, qabzMethodCode: String): QabzResult {
        return processor.process(text, systemId, qabzMethodCode)
    }
}

class QabzEngine(
    private val abjadEngine: AbjadEngine,
    private val jafrRepository: JafrKnowledgeRepository
) {
    private val factory = QabzFactory()
    private val processor = QabzProcessor(abjadEngine, jafrRepository, factory)
    
    val calculationService = QabzCalculationService(processor)
    val normalizationService = QabzNormalizationService(abjadEngine)
    val validationService = QabzValidationService(abjadEngine)
    val formatter = QabzFormatter()
}
