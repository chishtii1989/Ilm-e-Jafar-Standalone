package com.zakootaapps.ilmjafar.data.local.abjad_knowledge

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import androidx.room.ForeignKey

@Entity(
    tableName = "abjad_systems",
    indices = [
        Index(value = ["systemCode"], unique = true),
        Index(value = ["urduName"]),
        Index(value = ["englishName"])
    ]
)
data class AbjadSystemEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val systemCode: String,
    val urduName: String,
    val englishName: String,
    val description: String,
    val category: String,
    val isDefault: Boolean,
    val isActive: Boolean,
    val createdAt: Long = System.currentTimeMillis(),
    val updatedAt: Long = System.currentTimeMillis()
)

@Entity(
    tableName = "abjad_letters",
    foreignKeys = [
        ForeignKey(
            entity = AbjadSystemEntity::class,
            parentColumns = ["id"],
            childColumns = ["systemId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [
        Index(value = ["systemId"]),
        Index(value = ["letter"]),
        Index(value = ["normalizedLetter"]),
        Index(value = ["position"])
    ]
)
data class AbjadLetterEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val systemId: Int,
    val letter: String,
    val normalizedLetter: String,
    val unicode: String,
    val position: Int
)

@Entity(
    tableName = "abjad_aliases",
    indices = [
        Index(value = ["originalLetter"]),
        Index(value = ["normalizedLetter"])
    ]
)
data class AbjadAliasEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val originalLetter: String,
    val normalizedLetter: String,
    val language: String
)

@Entity(tableName = "abjad_metadata")
data class AbjadMetadataEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val databaseVersion: Int,
    val knowledgeVersion: String,
    val totalSystems: Int,
    val totalLetters: Int,
    val lastUpdated: Long = System.currentTimeMillis()
)
