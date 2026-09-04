package com.zakootaapps.ilmjafar.domain.jafr_knowledge.services

import com.zakootaapps.ilmjafar.data.repository.jafr_knowledge.JafrKnowledgeRepository
import com.zakootaapps.ilmjafar.data.local.jafr_knowledge.JafrCategoryEntity
import com.zakootaapps.ilmjafar.data.local.jafr_knowledge.JafrRuleEntity
import com.zakootaapps.ilmjafar.data.local.jafr_knowledge.JafrFormulaEntity
import com.zakootaapps.ilmjafar.data.local.jafr_knowledge.JafrReferenceEntity
import com.zakootaapps.ilmjafar.data.local.jafr_knowledge.JafrMetadataEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.concurrent.ConcurrentHashMap

class JafrNormalizer {
    fun normalize(text: String): String {
        return text.trim().replace("\\s+".toRegex(), " ")
    }
}

class JafrValidator {
    fun validateCategory(categoryCode: String, urduName: String): List<String> {
        val errors = mutableListOf<String>()
        if (categoryCode.isBlank()) errors.add("Category Code cannot be empty")
        if (urduName.isBlank()) errors.add("Urdu Name cannot be empty")
        return errors
    }
    
    fun validateRule(ruleCode: String, urduTitle: String): List<String> {
        val errors = mutableListOf<String>()
        if (ruleCode.isBlank()) errors.add("Rule Code cannot be empty")
        if (urduTitle.isBlank()) errors.add("Urdu Title cannot be empty")
        return errors
    }
}

class JafrCache {
    private val rulesCache = ConcurrentHashMap<Int, JafrRuleEntity>()
    private val formulasCache = ConcurrentHashMap<Int, List<JafrFormulaEntity>>()
    
    fun putRule(rule: JafrRuleEntity) {
        rulesCache[rule.id] = rule
    }
    
    fun getRule(id: Int): JafrRuleEntity? = rulesCache[id]
    
    fun putFormulas(ruleId: Int, formulas: List<JafrFormulaEntity>) {
        formulasCache[ruleId] = formulas
    }
    
    fun getFormulas(ruleId: Int): List<JafrFormulaEntity>? = formulasCache[ruleId]
    
    fun clear() {
        rulesCache.clear()
        formulasCache.clear()
    }
}

class JafrLoader(
    private val repository: JafrKnowledgeRepository,
    private val validator: JafrValidator,
    private val normalizer: JafrNormalizer,
    private val cache: JafrCache
) {
    suspend fun loadCategory(
        categoryCode: String,
        urduName: String,
        englishName: String,
        description: String,
        displayOrder: Int,
        isActive: Boolean
    ): Long {
        val errors = validator.validateCategory(categoryCode, urduName)
        if (errors.isNotEmpty()) {
            throw IllegalArgumentException("Validation failed: ${errors.joinToString()}")
        }
        
        val category = JafrCategoryEntity(
            categoryCode = normalizer.normalize(categoryCode),
            urduName = normalizer.normalize(urduName),
            englishName = normalizer.normalize(englishName),
            description = normalizer.normalize(description),
            displayOrder = displayOrder,
            isActive = isActive
        )
        return repository.insertCategory(category)
    }
    
    suspend fun loadRule(
        categoryId: Int,
        ruleCode: String,
        urduTitle: String,
        englishTitle: String,
        ruleText: String,
        notes: String,
        references: String
    ): Long {
        val errors = validator.validateRule(ruleCode, urduTitle)
        if (errors.isNotEmpty()) {
            throw IllegalArgumentException("Validation failed: ${errors.joinToString()}")
        }
        
        val rule = JafrRuleEntity(
            categoryId = categoryId,
            ruleCode = normalizer.normalize(ruleCode),
            urduTitle = normalizer.normalize(urduTitle),
            englishTitle = normalizer.normalize(englishTitle),
            ruleText = normalizer.normalize(ruleText),
            notes = normalizer.normalize(notes),
            references = normalizer.normalize(references)
        )
        val id = repository.insertRule(rule)
        cache.putRule(rule.copy(id = id.toInt()))
        return id
    }
    
    suspend fun loadFormula(
        ruleId: Int,
        formulaCode: String,
        formulaName: String,
        formulaDefinition: String,
        formulaType: String
    ): Long {
        val formula = JafrFormulaEntity(
            ruleId = ruleId,
            formulaCode = normalizer.normalize(formulaCode),
            formulaName = normalizer.normalize(formulaName),
            formulaDefinition = normalizer.normalize(formulaDefinition),
            formulaType = normalizer.normalize(formulaType)
        )
        return repository.insertFormula(formula)
    }
    
    suspend fun loadReference(
        referenceTitle: String,
        author: String,
        bookName: String,
        chapter: String,
        page: String,
        remarks: String
    ): Long {
        val reference = JafrReferenceEntity(
            referenceTitle = normalizer.normalize(referenceTitle),
            author = normalizer.normalize(author),
            bookName = normalizer.normalize(bookName),
            chapter = normalizer.normalize(chapter),
            page = normalizer.normalize(page),
            remarks = normalizer.normalize(remarks)
        )
        return repository.insertReference(reference)
    }
    
    suspend fun loadMetadata(databaseVersion: Int, knowledgeVersion: String, totalCategories: Int, totalRules: Int, totalFormulas: Int): Long {
        val metadata = JafrMetadataEntity(
            databaseVersion = databaseVersion,
            knowledgeVersion = knowledgeVersion,
            totalCategories = totalCategories,
            totalRules = totalRules,
            totalFormulas = totalFormulas
        )
        return repository.insertMetadata(metadata)
    }
}

class JafrIndexer(
    private val repository: JafrKnowledgeRepository
) {
    suspend fun buildMemoryIndex() = withContext(Dispatchers.IO) {
        // Implementation for building fast in-memory search structures
    }
}
