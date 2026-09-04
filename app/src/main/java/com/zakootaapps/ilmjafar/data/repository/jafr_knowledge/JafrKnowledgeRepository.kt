package com.zakootaapps.ilmjafar.data.repository.jafr_knowledge

import com.zakootaapps.ilmjafar.data.local.jafr_knowledge.JafrKnowledgeDao
import com.zakootaapps.ilmjafar.data.local.jafr_knowledge.JafrCategoryEntity
import com.zakootaapps.ilmjafar.data.local.jafr_knowledge.JafrRuleEntity
import com.zakootaapps.ilmjafar.data.local.jafr_knowledge.JafrFormulaEntity
import com.zakootaapps.ilmjafar.data.local.jafr_knowledge.JafrReferenceEntity
import com.zakootaapps.ilmjafar.data.local.jafr_knowledge.JafrMetadataEntity
import kotlinx.coroutines.flow.Flow

class JafrKnowledgeRepository(
    private val dao: JafrKnowledgeDao
) {
    suspend fun insertCategory(category: JafrCategoryEntity): Long = dao.insertCategory(category)
    
    fun getCategories(): Flow<List<JafrCategoryEntity>> = dao.getAllCategories()
    
    suspend fun insertRule(rule: JafrRuleEntity): Long = dao.insertRule(rule)
    
    fun getRules(): Flow<List<JafrRuleEntity>> = dao.getAllRules()
    
    suspend fun getRuleById(id: Int): JafrRuleEntity? = dao.getRuleById(id)
    
    fun getRulesByCategory(categoryId: Int): Flow<List<JafrRuleEntity>> = dao.getRulesByCategory(categoryId)
    
    fun searchRules(query: String): Flow<List<JafrRuleEntity>> = dao.searchRules(query)
    
    suspend fun insertFormula(formula: JafrFormulaEntity): Long = dao.insertFormula(formula)
    
    fun getFormulasForRule(ruleId: Int): Flow<List<JafrFormulaEntity>> = dao.getFormulasForRule(ruleId)
    
    suspend fun insertReference(reference: JafrReferenceEntity): Long = dao.insertReference(reference)
    
    fun getAllReferences(): Flow<List<JafrReferenceEntity>> = dao.getAllReferences()
    
    suspend fun insertMetadata(metadata: JafrMetadataEntity): Long = dao.insertMetadata(metadata)
    
    suspend fun getMetadata(): JafrMetadataEntity? = dao.getMetadata()
}
