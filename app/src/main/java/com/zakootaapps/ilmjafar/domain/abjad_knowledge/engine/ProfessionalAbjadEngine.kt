package com.zakootaapps.ilmjafar.domain.abjad_knowledge.engine

import com.zakootaapps.ilmjafar.data.repository.abjad_knowledge.AbjadKnowledgeRepository
import com.zakootaapps.ilmjafar.data.local.abjad_knowledge.AbjadSystemEntity
import kotlinx.coroutines.flow.firstOrNull
import java.util.concurrent.ConcurrentHashMap

data class LetterDetail(
    val originalChar: Char,
    val normalizedChar: Char,
    val value: Int,
    val runningTotal: Int,
    val isSupported: Boolean
)

data class AbjadResult(
    val originalText: String,
    val normalizedText: String,
    val system: AbjadSystemEntity,
    val letterBreakdown: List<LetterDetail>,
    val finalTotal: Int,
    val characterCount: Int,
    val letterCount: Int
)

class AbjadNormalizer {
    fun normalize(text: String): String {
        var normalized = text.trim()
        normalized = normalized.replace("\\s+".toRegex(), " ")
        
        val charMap = mapOf(
            'أ' to 'ا',
            'إ' to 'ا',
            'آ' to 'ا',
            'ٱ' to 'ا',
            'ى' to 'ی',
            'ي' to 'ی',
            'ك' to 'ک',
            'ة' to 'ہ',
            'ۀ' to 'ہ'
        )
        
        val sb = StringBuilder()
        for (char in normalized) {
            sb.append(charMap[char] ?: char)
        }
        return sb.toString()
    }
}

class AbjadValidator {
    fun isValidChar(char: Char): Boolean {
        // Emojis and punctuation will be ignored if we only consider letters
        return char.isLetter() || char.isWhitespace()
    }
}

class AbjadParser {
    fun parse(text: String): List<Char> {
        return text.filter { !it.isWhitespace() }.toList()
    }
}

class AbjadCalculator(
    private val repository: AbjadKnowledgeRepository,
    private val cache: ConcurrentHashMap<Int, Map<Char, Int>>
) {
    suspend fun calculate(text: String, systemId: Int): Pair<List<LetterDetail>, Int> {
        val letterMap = getSystemLettersMap(systemId)
        var total = 0
        val breakdown = mutableListOf<LetterDetail>()
        
        for (char in text) {
            if (char.isWhitespace()) {
                continue
            }
            
            // Using position field as value as per KB-01 schema definition
            val value = letterMap[char] ?: 0
            val isSupported = letterMap.containsKey(char)
            if (isSupported) {
                total += value
            }
            
            breakdown.add(
                LetterDetail(
                    originalChar = char,
                    normalizedChar = char,
                    value = value,
                    runningTotal = total,
                    isSupported = isSupported
                )
            )
        }
        
        return Pair(breakdown, total)
    }
    
    private suspend fun getSystemLettersMap(systemId: Int): Map<Char, Int> {
        if (cache.containsKey(systemId)) {
            return cache[systemId] ?: emptyMap()
        }
        
        val letters = repository.getLetters(systemId).firstOrNull() ?: emptyList()
        val map = letters.associate { 
            val ch = if (it.normalizedLetter.isNotEmpty()) it.normalizedLetter.first() else ' '
            ch to it.position 
        }.filterKeys { it != ' ' }
        
        cache[systemId] = map
        return map
    }
}

class AbjadProcessor(
    private val repository: AbjadKnowledgeRepository,
    private val normalizer: AbjadNormalizer,
    private val validator: AbjadValidator,
    private val parser: AbjadParser,
    private val calculator: AbjadCalculator
) {
    suspend fun process(text: String, systemId: Int): AbjadResult {
        val system = repository.getSystemById(systemId) 
            ?: throw IllegalArgumentException("System not found")
            
        val normalizedText = normalizer.normalize(text)
        val validText = normalizedText.filter { validator.isValidChar(it) || it.isWhitespace() }
        val chars = parser.parse(validText)
        
        val (breakdown, total) = calculator.calculate(validText, systemId)
        
        return AbjadResult(
            originalText = text,
            normalizedText = validText,
            system = system,
            letterBreakdown = breakdown,
            finalTotal = total,
            characterCount = text.length,
            letterCount = chars.size
        )
    }
}

class NormalizationService(private val normalizer: AbjadNormalizer) {
    fun normalize(text: String) = normalizer.normalize(text)
}

class ValidationService(private val validator: AbjadValidator) {
    fun isValid(char: Char) = validator.isValidChar(char)
}

class ResultFormatter {
    fun format(result: AbjadResult): String {
        val sb = StringBuilder()
        sb.append("System: ${result.system.urduName}\n")
        sb.append("Total: ${result.finalTotal}\n")
        sb.append("Breakdown:\n")
        result.letterBreakdown.forEach {
            sb.append("${it.normalizedChar} -> ${it.value}\n")
        }
        return sb.toString()
    }
}

class CalculationService(
    private val processor: AbjadProcessor
) {
    suspend fun performCalculation(text: String, systemId: Int): AbjadResult {
        return processor.process(text, systemId)
    }
}

class AbjadEngine(
    private val repository: AbjadKnowledgeRepository
) {
    private val cache = ConcurrentHashMap<Int, Map<Char, Int>>()
    private val normalizer = AbjadNormalizer()
    private val validator = AbjadValidator()
    private val parser = AbjadParser()
    private val calculator = AbjadCalculator(repository, cache)
    private val processor = AbjadProcessor(repository, normalizer, validator, parser, calculator)
    
    val calculationService = CalculationService(processor)
    val normalizationService = NormalizationService(normalizer)
    val validationService = ValidationService(validator)
    val resultFormatter = ResultFormatter()
    
    fun clearCache() {
        cache.clear()
    }
}
