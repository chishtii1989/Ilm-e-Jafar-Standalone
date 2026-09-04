package com.zakootaapps.ilmjafar.data.local.talismat_knowledge

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import androidx.room.ForeignKey

@Entity(
    tableName = "talismat_categories",
    indices = [
        Index(value = ["categoryCode"], unique = true),
        Index(value = ["urduName"]),
        Index(value = ["englishName"])
    ]
)
data class TalismCategoryEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val categoryCode: String,
    val urduName: String,
    val englishName: String,
    val description: String,
    val displayOrder: Int,
    val isActive: Boolean
)

@Entity(
    tableName = "talismat_templates",
    foreignKeys = [
        ForeignKey(
            entity = TalismCategoryEntity::class,
            parentColumns = ["id"],
            childColumns = ["categoryId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [
        Index(value = ["categoryId"]),
        Index(value = ["templateCode"], unique = true),
        Index(value = ["urduTitle"]),
        Index(value = ["englishTitle"]),
        Index(value = ["purpose"])
    ]
)
data class TalismTemplateEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val categoryId: Int,
    val templateCode: String,
    val urduTitle: String,
    val englishTitle: String,
    val purpose: String,
    val shape: String,
    val preparationMethod: String,
    val usageMethod: String,
    val conditions: String,
    val notes: String
)

@Entity(
    tableName = "talismat_symbols",
    foreignKeys = [
        ForeignKey(
            entity = TalismTemplateEntity::class,
            parentColumns = ["id"],
            childColumns = ["templateId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [
        Index(value = ["templateId"]),
        Index(value = ["sequenceOrder"])
    ]
)
data class TalismSymbolEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val templateId: Int,
    val symbol: String,
    val symbolType: String,
    val sequenceOrder: Int,
    val meaning: String
)

@Entity(
    tableName = "talismat_conditions",
    foreignKeys = [
        ForeignKey(
            entity = TalismTemplateEntity::class,
            parentColumns = ["id"],
            childColumns = ["templateId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [
        Index(value = ["templateId"])
    ]
)
data class TalismConditionEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val templateId: Int,
    val conditionName: String,
    val conditionValue: String,
    val description: String
)

@Entity(tableName = "talismat_metadata")
data class TalismMetadataEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val databaseVersion: Int,
    val knowledgeVersion: String,
    val totalCategories: Int,
    val totalTemplates: Int,
    val totalSymbols: Int,
    val lastUpdated: Long = System.currentTimeMillis()
)
