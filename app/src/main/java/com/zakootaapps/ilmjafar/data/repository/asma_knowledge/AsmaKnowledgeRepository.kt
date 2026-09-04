package com.zakootaapps.ilmjafar.data.repository.asma_knowledge

import com.zakootaapps.ilmjafar.data.local.asma_knowledge.AsmaKnowledgeDao
import com.zakootaapps.ilmjafar.data.local.asma_knowledge.AsmaModelEntity
import com.zakootaapps.ilmjafar.data.local.asma_knowledge.AsmaAttributeEntity
import com.zakootaapps.ilmjafar.data.local.asma_knowledge.AsmaAliasEntity
import com.zakootaapps.ilmjafar.data.local.asma_knowledge.AsmaMetadataEntity
import kotlinx.coroutines.flow.Flow

class AsmaKnowledgeRepository(
    private val dao: AsmaKnowledgeDao
) {
    suspend fun insertAsma(asma: AsmaModelEntity): Long = dao.insertAsma(asma)
    
    fun getAllNames(): Flow<List<AsmaModelEntity>> = dao.getAllNames()
    
    suspend fun getNameById(id: Int): AsmaModelEntity? = dao.getNameById(id)
    
    fun searchByArabic(query: String): Flow<List<AsmaModelEntity>> = dao.searchByArabic(query)
    
    fun searchByUrdu(query: String): Flow<List<AsmaModelEntity>> = dao.searchByUrdu(query)
    
    fun searchByEnglish(query: String): Flow<List<AsmaModelEntity>> = dao.searchByEnglish(query)
    
    fun searchByAbjad(value: Int): Flow<List<AsmaModelEntity>> = dao.searchByAbjad(value)
    
    suspend fun insertAttribute(attribute: AsmaAttributeEntity): Long = dao.insertAttribute(attribute)
    
    suspend fun getAttributeByAsmaId(asmaId: Int): AsmaAttributeEntity? = dao.getAttributeByAsmaId(asmaId)
    
    suspend fun insertAlias(alias: AsmaAliasEntity): Long = dao.insertAlias(alias)
    
    suspend fun insertMetadata(metadata: AsmaMetadataEntity): Long = dao.insertMetadata(metadata)
    
    suspend fun getMetadata(): AsmaMetadataEntity? = dao.getMetadata()
}
