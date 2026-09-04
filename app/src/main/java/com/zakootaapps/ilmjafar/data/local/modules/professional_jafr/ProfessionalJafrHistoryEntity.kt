package com.zakootaapps.ilmjafar.data.local.modules.professional_jafr

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "professional_jafr_history")
data class ProfessionalJafrHistoryEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val inputPhrase: String,
    val jafrType: String,
    val finalResult: String,
    val additionalInfo: String,
    val timestamp: Long = System.currentTimeMillis()
)
