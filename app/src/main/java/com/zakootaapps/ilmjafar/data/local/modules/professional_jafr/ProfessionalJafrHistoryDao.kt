package com.zakootaapps.ilmjafar.data.local.modules.professional_jafr

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ProfessionalJafrHistoryDao {
    @Query("SELECT * FROM professional_jafr_history ORDER BY timestamp DESC")
    fun getAllHistory(): Flow<List<ProfessionalJafrHistoryEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(history: ProfessionalJafrHistoryEntity)

    @Delete
    suspend fun delete(history: ProfessionalJafrHistoryEntity)
}
