package com.zakootaapps.ilmjafar.data.local.naqoosh_knowledge

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import androidx.room.ForeignKey

@Entity(
    tableName = "naqsh_categories",
    indices = [
        Index(value = ["categoryCode"], unique = true),
        Index(value = ["urduName"]),
        Index(value = ["englishName"])
    ]
)
data class NaqshCategoryEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val categoryCode: String,
    val urduName: String,
    val englishName: String,
    val description: String,
    val isActive: Boolean,
    val createdAt: Long = System.currentTimeMillis(),
    val updatedAt: Long = System.currentTimeMillis()
)

@Entity(
    tableName = "naqsh_templates",
    foreignKeys = [
        ForeignKey(
            entity = NaqshCategoryEntity::class,
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
data class NaqshTemplateEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val categoryId: Int,
    val templateCode: String,
    val urduTitle: String,
    val englishTitle: String,
    val purpose: String,
    val shape: String,
    val rows: Int,
    val columns: Int,
    val instructions: String,
    val notes: String,
    val createdAt: Long = System.currentTimeMillis(),
    val updatedAt: Long = System.currentTimeMillis()
)

@Entity(
    tableName = "naqsh_cells",
    foreignKeys = [
        ForeignKey(
            entity = NaqshTemplateEntity::class,
            parentColumns = ["id"],
            childColumns = ["templateId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [
        Index(value = ["templateId"]),
        Index(value = ["cellOrder"])
    ]
)
data class NaqshCellEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val templateId: Int,
    val rowNumber: Int,
    val columnNumber: Int,
    val cellOrder: Int,
    val defaultValue: String,
    val valueType: String
)

@Entity(
    tableName = "naqsh_conditions",
    foreignKeys = [
        ForeignKey(
            entity = NaqshTemplateEntity::class,
            parentColumns = ["id"],
            childColumns = ["templateId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [
        Index(value = ["templateId"])
    ]
)
data class NaqshConditionEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val templateId: Int,
    val conditionType: String,
    val conditionValue: String,
    val description: String
)

@Entity(tableName = "naqsh_metadata")
data class NaqshMetadataEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val databaseVersion: Int,
    val knowledgeVersion: String,
    val totalCategories: Int,
    val totalTemplates: Int,
    val totalCells: Int,
    val lastUpdated: Long = System.currentTimeMillis()
)
