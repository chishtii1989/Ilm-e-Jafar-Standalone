package com.zakootaapps.ilmjafar.data.repository.modules.professional_ilmuladad

import com.zakootaapps.ilmjafar.data.local.modules.professional_ilmuladad.ProfessionalIlmUlAdadHistoryDao
import com.zakootaapps.ilmjafar.data.local.modules.professional_ilmuladad.ProfessionalIlmUlAdadHistoryEntity
import kotlinx.coroutines.flow.Flow

class ProfessionalIlmUlAdadHistoryRepository(private val dao: ProfessionalIlmUlAdadHistoryDao) {
    fun getAllHistory(): Flow<List<ProfessionalIlmUlAdadHistoryEntity>> = dao.getAllHistory()
    fun getFavorites(): Flow<List<ProfessionalIlmUlAdadHistoryEntity>> = dao.getFavorites()
    fun searchHistory(query: String): Flow<List<ProfessionalIlmUlAdadHistoryEntity>> = dao.searchHistory(query)
    suspend fun insert(history: ProfessionalIlmUlAdadHistoryEntity) = dao.insert(history)
    suspend fun delete(history: ProfessionalIlmUlAdadHistoryEntity) = dao.delete(history)
    suspend fun update(history: ProfessionalIlmUlAdadHistoryEntity) = dao.update(history)
    suspend fun deleteAll() = dao.deleteAll()
}
