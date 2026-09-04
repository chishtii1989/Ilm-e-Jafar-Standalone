package com.zakootaapps.ilmjafar.data.local.asma_knowledge

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import androidx.room.ForeignKey

@Entity(
    tableName = "asma_ul_husna",
    indices = [
        Index(value = ["arabicName"]),
        Index(value = ["urduName"]),
        Index(value = ["englishName"]),
        Index(value = ["abjadValue"])
    ]
)
data class AsmaModelEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val number: Int,
    val arabicName: String,
    val urduName: String,
    val englishName: String,
    val transliteration: String,
    val meaning: String,
    val shortDescription: String,
    val longDescription: String,
    val abjadValue: Int,
    val category: String,
    val references: String,
    val createdAt: Long = System.currentTimeMillis(),
    val updatedAt: Long = System.currentTimeMillis()
)

@Entity(
    tableName = "asma_attributes",
    foreignKeys = [
        ForeignKey(
            entity = AsmaModelEntity::class,
            parentColumns = ["id"],
            childColumns = ["asmaId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [
        Index(value = ["asmaId"])
    ]
)
data class AsmaAttributeEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val asmaId: Int,
    val planet: String,
    val element: String,
    val colour: String,
    val incense: String,
    val bestDay: String,
    val bestHour: String,
    val recommendedCount: Int,
    val recommendedTime: String
)

@Entity(
    tableName = "asma_aliases",
    foreignKeys = [
        ForeignKey(
            entity = AsmaModelEntity::class,
            parentColumns = ["id"],
            childColumns = ["asmaId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [
        Index(value = ["asmaId"])
    ]
)
data class AsmaAliasEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val asmaId: Int,
    val alias: String,
    val language: String
)

@Entity(tableName = "asma_metadata")
data class AsmaMetadataEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val databaseVersion: Int,
    val knowledgeVersion: String,
    val totalRecords: Int,
    val lastUpdated: Long = System.currentTimeMillis()
)
