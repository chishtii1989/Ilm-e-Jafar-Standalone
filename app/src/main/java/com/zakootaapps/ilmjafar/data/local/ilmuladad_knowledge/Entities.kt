package com.zakootaapps.ilmjafar.data.local.ilmuladad_knowledge

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "ilmuladad_numbers",
    indices = [
        Index(value = ["number", "numberType"], unique = true)
    ]
)
data class IlmUlAdadNumberEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val number: Int,
    val numberType: String, // "ROOT", "DESTINY", "COMPOUND"
    val planet: String,
    val element: String,
    val luckyColors: String,
    val luckyGemstone: String,
    val friendlyNumbers: String,
    val enemyNumbers: String,
    val traits: String,
    val careers: String,
    val spirituals: String,
    val metadata: String = "",
    val createdAt: Long = System.currentTimeMillis()
)

@Entity(tableName = "ilmuladad_metadata")
data class IlmUlAdadMetadataEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val databaseVersion: Int,
    val knowledgeVersion: String,
    val totalNumbers: Int,
    val lastUpdated: Long = System.currentTimeMillis()
)
