package com.zakootaapps.ilmjafar.data.local.jafr_knowledge

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import androidx.room.ForeignKey

@Entity(
    tableName = "jafr_categories",
    indices = [
        Index(value = ["categoryCode"], unique = true),
        Index(value = ["urduName"]),
        Index(value = ["englishName"])
    ]
)
data class JafrCategoryEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val categoryCode: String,
    val urduName: String,
    val englishName: String,
    val description: String,
    val displayOrder: Int,
    val isActive: Boolean
)

@Entity(
    tableName = "jafr_rules",
    foreignKeys = [
        ForeignKey(
            entity = JafrCategoryEntity::class,
            parentColumns = ["id"],
            childColumns = ["categoryId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [
        Index(value = ["categoryId"]),
        Index(value = ["ruleCode"], unique = true),
        Index(value = ["urduTitle"]),
        Index(value = ["englishTitle"])
    ]
)
data class JafrRuleEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val categoryId: Int,
    val ruleCode: String,
    val urduTitle: String,
    val englishTitle: String,
    val ruleText: String,
    val notes: String,
    val references: String
)

@Entity(
    tableName = "jafr_formulas",
    foreignKeys = [
        ForeignKey(
            entity = JafrRuleEntity::class,
            parentColumns = ["id"],
            childColumns = ["ruleId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [
        Index(value = ["ruleId"]),
        Index(value = ["formulaCode"], unique = true)
    ]
)
data class JafrFormulaEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val ruleId: Int,
    val formulaCode: String,
    val formulaName: String,
    val formulaDefinition: String,
    val formulaType: String
)

@Entity(
    tableName = "jafr_references",
    indices = [
        Index(value = ["referenceTitle"])
    ]
)
data class JafrReferenceEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val referenceTitle: String,
    val author: String,
    val bookName: String,
    val chapter: String,
    val page: String,
    val remarks: String
)

@Entity(tableName = "jafr_metadata")
data class JafrMetadataEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val databaseVersion: Int,
    val knowledgeVersion: String,
    val totalCategories: Int,
    val totalRules: Int,
    val totalFormulas: Int,
    val lastUpdated: Long = System.currentTimeMillis()
)
