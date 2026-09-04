package com.zakootaapps.ilmjafar.data.local.modules.professional_ilmuladad

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface ProfessionalIlmUlAdadHistoryDao {
    @Query("SELECT * FROM professional_ilmuladad_history ORDER BY timestamp DESC")
    fun getAllHistory(): Flow<List<ProfessionalIlmUlAdadHistoryEntity>>

    @Query("SELECT * FROM professional_ilmuladad_history WHERE isFavorite = 1 ORDER BY timestamp DESC")
    fun getFavorites(): Flow<List<ProfessionalIlmUlAdadHistoryEntity>>

    @Query("SELECT * FROM professional_ilmuladad_history WHERE name LIKE '%' || :query || '%' OR mothersName LIKE '%' || :query || '%' ORDER BY timestamp DESC")
    fun searchHistory(query: String): Flow<List<ProfessionalIlmUlAdadHistoryEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(history: ProfessionalIlmUlAdadHistoryEntity)

    @Delete
    suspend fun delete(history: ProfessionalIlmUlAdadHistoryEntity)

    @Query("DELETE FROM professional_ilmuladad_history")
    suspend fun deleteAll()

    @Update
    suspend fun update(history: ProfessionalIlmUlAdadHistoryEntity)
}
