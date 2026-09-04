package com.zakootaapps.ilmjafar.data.local.jafr_knowledge

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface JafrKnowledgeDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCategory(category: JafrCategoryEntity): Long

    @Query("SELECT * FROM jafr_categories ORDER BY displayOrder ASC")
    fun getAllCategories(): Flow<List<JafrCategoryEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRule(rule: JafrRuleEntity): Long

    @Query("SELECT * FROM jafr_rules")
    fun getAllRules(): Flow<List<JafrRuleEntity>>

    @Query("SELECT * FROM jafr_rules WHERE id = :id LIMIT 1")
    suspend fun getRuleById(id: Int): JafrRuleEntity?
    
    @Query("SELECT * FROM jafr_rules WHERE categoryId = :categoryId")
    fun getRulesByCategory(categoryId: Int): Flow<List<JafrRuleEntity>>

    @Query("SELECT * FROM jafr_rules WHERE urduTitle LIKE '%' || :query || '%' OR englishTitle LIKE '%' || :query || '%' OR ruleCode LIKE '%' || :query || '%'")
    fun searchRules(query: String): Flow<List<JafrRuleEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFormula(formula: JafrFormulaEntity): Long
    
    @Query("SELECT * FROM jafr_formulas WHERE ruleId = :ruleId")
    fun getFormulasForRule(ruleId: Int): Flow<List<JafrFormulaEntity>>
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertReference(reference: JafrReferenceEntity): Long
    
    @Query("SELECT * FROM jafr_references")
    fun getAllReferences(): Flow<List<JafrReferenceEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMetadata(metadata: JafrMetadataEntity): Long

    @Query("SELECT * FROM jafr_metadata LIMIT 1")
    suspend fun getMetadata(): JafrMetadataEntity?
}
