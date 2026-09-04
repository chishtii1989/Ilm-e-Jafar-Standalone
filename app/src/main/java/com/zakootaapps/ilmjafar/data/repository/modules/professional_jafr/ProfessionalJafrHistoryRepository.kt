package com.zakootaapps.ilmjafar.data.repository.modules.professional_jafr

import com.zakootaapps.ilmjafar.data.local.modules.professional_jafr.ProfessionalJafrHistoryDao
import com.zakootaapps.ilmjafar.data.local.modules.professional_jafr.ProfessionalJafrHistoryEntity
import kotlinx.coroutines.flow.Flow

class ProfessionalJafrHistoryRepository(private val dao: ProfessionalJafrHistoryDao) {
    fun getAllHistory(): Flow<List<ProfessionalJafrHistoryEntity>> = dao.getAllHistory()
    suspend fun insert(history: ProfessionalJafrHistoryEntity) = dao.insert(history)
    suspend fun delete(history: ProfessionalJafrHistoryEntity) = dao.delete(history)
}
