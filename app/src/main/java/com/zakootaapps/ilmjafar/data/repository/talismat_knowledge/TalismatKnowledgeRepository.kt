package com.zakootaapps.ilmjafar.data.repository.talismat_knowledge

import com.zakootaapps.ilmjafar.data.local.talismat_knowledge.TalismatKnowledgeDao
import com.zakootaapps.ilmjafar.data.local.talismat_knowledge.TalismCategoryEntity
import com.zakootaapps.ilmjafar.data.local.talismat_knowledge.TalismTemplateEntity
import com.zakootaapps.ilmjafar.data.local.talismat_knowledge.TalismSymbolEntity
import com.zakootaapps.ilmjafar.data.local.talismat_knowledge.TalismConditionEntity
import com.zakootaapps.ilmjafar.data.local.talismat_knowledge.TalismMetadataEntity
import kotlinx.coroutines.flow.Flow

class TalismatKnowledgeRepository(
    private val dao: TalismatKnowledgeDao
) {
    suspend fun insertCategory(category: TalismCategoryEntity): Long = dao.insertCategory(category)
    
    fun getCategories(): Flow<List<TalismCategoryEntity>> = dao.getAllCategories()
    
    suspend fun insertTemplate(template: TalismTemplateEntity): Long = dao.insertTemplate(template)
    
    fun getTemplates(): Flow<List<TalismTemplateEntity>> = dao.getAllTemplates()
    
    suspend fun getTemplateById(id: Int): TalismTemplateEntity? = dao.getTemplateById(id)
    
    fun getTemplatesByCategory(categoryId: Int): Flow<List<TalismTemplateEntity>> = dao.getTemplatesByCategory(categoryId)
    
    fun searchTemplates(query: String): Flow<List<TalismTemplateEntity>> = dao.searchTemplates(query)
    
    suspend fun insertSymbol(symbol: TalismSymbolEntity): Long = dao.insertSymbol(symbol)
    
    fun getSymbols(templateId: Int): Flow<List<TalismSymbolEntity>> = dao.getSymbolsForTemplate(templateId)
    
    suspend fun insertCondition(condition: TalismConditionEntity): Long = dao.insertCondition(condition)
    
    suspend fun insertMetadata(metadata: TalismMetadataEntity): Long = dao.insertMetadata(metadata)
    
    suspend fun getMetadata(): TalismMetadataEntity? = dao.getMetadata()
}
