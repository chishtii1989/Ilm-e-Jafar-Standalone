package com.zakootaapps.ilmjafar.data.local.modules.asmaulhusna

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface AsmaUlHusnaDao {
    @Query("SELECT * FROM asmaulhusna_table")
    fun getAll(): Flow<List<AsmaUlHusnaEntity>>

    @Query("SELECT * FROM asmaulhusna_table WHERE id = :id")
    suspend fun getById(id: Int): AsmaUlHusnaEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: AsmaUlHusnaEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(entities: List<AsmaUlHusnaEntity>)

    @Query("DELETE FROM asmaulhusna_table")
    suspend fun deleteAll()
}
