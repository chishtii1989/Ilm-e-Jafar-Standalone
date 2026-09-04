package com.zakootaapps.ilmjafar.data.repository.naqoosh_knowledge

import com.zakootaapps.ilmjafar.data.local.naqoosh_knowledge.NaqooshKnowledgeDao
import com.zakootaapps.ilmjafar.data.local.naqoosh_knowledge.NaqshCategoryEntity
import com.zakootaapps.ilmjafar.data.local.naqoosh_knowledge.NaqshTemplateEntity
import com.zakootaapps.ilmjafar.data.local.naqoosh_knowledge.NaqshCellEntity
import com.zakootaapps.ilmjafar.data.local.naqoosh_knowledge.NaqshConditionEntity
import com.zakootaapps.ilmjafar.data.local.naqoosh_knowledge.NaqshMetadataEntity
import kotlinx.coroutines.flow.Flow

class NaqooshKnowledgeRepository(
    private val dao: NaqooshKnowledgeDao
) {
    suspend fun insertCategory(category: NaqshCategoryEntity): Long = dao.insertCategory(category)
    
    fun getCategories(): Flow<List<NaqshCategoryEntity>> = dao.getAllCategories()
    
    suspend fun insertTemplate(template: NaqshTemplateEntity): Long = dao.insertTemplate(template)
    
    fun getTemplates(): Flow<List<NaqshTemplateEntity>> = dao.getAllTemplates()
    
    suspend fun getTemplateById(id: Int): NaqshTemplateEntity? = dao.getTemplateById(id)
    
    fun getTemplatesByCategory(categoryId: Int): Flow<List<NaqshTemplateEntity>> = dao.getTemplatesByCategory(categoryId)
    
    fun searchTemplates(query: String): Flow<List<NaqshTemplateEntity>> = dao.searchTemplates(query)
    
    suspend fun insertCell(cell: NaqshCellEntity): Long = dao.insertCell(cell)
    
    suspend fun insertCells(cells: List<NaqshCellEntity>) = dao.insertCells(cells)
    
    fun getCells(templateId: Int): Flow<List<NaqshCellEntity>> = dao.getCellsForTemplate(templateId)
    
    suspend fun insertCondition(condition: NaqshConditionEntity): Long = dao.insertCondition(condition)
    
    suspend fun insertMetadata(metadata: NaqshMetadataEntity): Long = dao.insertMetadata(metadata)
    
    suspend fun getMetadata(): NaqshMetadataEntity? = dao.getMetadata()
}
