package com.zakootaapps.ilmjafar.data.repository.ilmuladad_knowledge

import com.zakootaapps.ilmjafar.data.local.ilmuladad_knowledge.IlmUlAdadKnowledgeDao
import com.zakootaapps.ilmjafar.data.local.ilmuladad_knowledge.IlmUlAdadNumberEntity
import com.zakootaapps.ilmjafar.data.local.ilmuladad_knowledge.IlmUlAdadMetadataEntity
import kotlinx.coroutines.flow.Flow

class IlmUlAdadKnowledgeRepository(private val dao: IlmUlAdadKnowledgeDao) {
    
    suspend fun insertNumber(number: IlmUlAdadNumberEntity): Long = dao.insertNumber(number)
    
    suspend fun insertNumbers(numbers: List<IlmUlAdadNumberEntity>) = dao.insertNumbers(numbers)
    
    fun getNumbersByType(type: String): Flow<List<IlmUlAdadNumberEntity>> = dao.getNumbersByType(type)
    
    suspend fun getNumberDetails(number: Int, type: String): IlmUlAdadNumberEntity? = dao.getNumberDetails(number, type)
    
    fun getAllNumbers(): Flow<List<IlmUlAdadNumberEntity>> = dao.getAllNumbers()
    
    suspend fun deleteAllNumbers() = dao.deleteAllNumbers()
    
    suspend fun insertMetadata(metadata: IlmUlAdadMetadataEntity): Long = dao.insertMetadata(metadata)
    
    suspend fun getLatestMetadata(): IlmUlAdadMetadataEntity? = dao.getLatestMetadata()
}
