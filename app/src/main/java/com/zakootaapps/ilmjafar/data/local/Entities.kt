package com.zakootaapps.ilmjafar.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "books")
data class BookEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val coverUrl: String? = null,
    val name: String = "",
    val alternativeName: String? = null,
    val author: String = "",
    val translator: String? = null,
    val publisher: String = "",
    val edition: String = "",
    val language: String = "",
    val totalPages: Int = 0,
    val volume: String? = null,
    val isbn: String? = null,
    val category: String = "",
    val subCategory: String? = null,
    val keywords: String = "",
    val description: String = "",
    val source: String? = null,
    val reference: String? = null,
    val importDate: Long = 0L,
    val lastUpdated: Long = 0L,
    val status: String = "Pending",
    val pdfUrl: String? = null,
    val fileType: String = "PDF",
    val ocrProgress: Float = 0f,
    val aiParsingProgress: Float = 0f,
    val extractedRecords: Int = 0,
    val pendingRecords: Int = 0,
    val approvedRecords: Int = 0,
    val rejectedRecords: Int = 0,
    val duplicateRecords: Int = 0,
    val isFavorite: Boolean = false,
    val isBookmarked: Boolean = false,
    val lastReadTimestamp: Long? = null,
    val lastReadPage: Int = 0
)

@Entity(tableName = "hazaarat_items")
data class HazaaratEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val title: String,
    val shortDescription: String,
    val bookName: String,
    val author: String,
    val pageNumber: String,
    val category: String,
    val difficultyLevel: String,
    val purpose: String,
    val requirements: String,
    val benefits: String,
    val method: String,
    val duration: String,
    val precautions: String,
    val notes: String,
    val references: String,
    val isFavorite: Boolean = false,
    val isBookmarked: Boolean = false
)

@Entity(tableName = "library_items")
data class LibraryItemEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val title: String,
    val description: String,
    val isFavorite: Boolean = false
)

@Entity(tableName = "history_items")
data class HistoryItemEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val query: String,
    val source: String,
    val timestamp: Long
)

@Entity(tableName = "knowledge_records")
data class KnowledgeRecordEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val title: String,
    val category: String,
    val subCategory: String,
    val bookName: String,
    val author: String,
    val publisher: String,
    val edition: String,
    val language: String,
    val pageNumber: String,
    val arabicText: String? = null,
    val urduText: String? = null,
    val unicodeText: String? = null,
    val ocrText: String? = null,
    val purpose: String? = null,
    val benefits: String? = null,
    val method: String? = null,
    val requirements: String? = null,
    val preparation: String? = null,
    val duration: String? = null,
    val bestTime: String? = null,
    val precautions: String? = null,
    val keywords: String? = null,
    val tags: String? = null,
    val relatedRecords: String? = null,
    val references: String? = null,
    val aiSummary: String? = null,
    val createdDate: Long,
    val updatedDate: Long,
    val isFavorite: Boolean = false,
    val isBookmarked: Boolean = false,
    val lastReadTimestamp: Long? = null,
    val needsReview: Boolean = false,
    val referenceCount: Int = 1,
    val volume: String? = null,
    val source: String? = null,
    val aiConfidence: Float = 1.0f,
    val isRejected: Boolean = false
)

@Entity(tableName = "astronomy_events")
data class AstronomyEventEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val moduleName: String,
    val gregorianDate: String,
    val hijriDate: String,
    val weekDay: String,
    val startTime: String,
    val endTime: String,
    val localTime: String,
    val eventStatus: String,
    val detailedDescription: String,
    val importance: String,
    val scientificInfo: String,
    val islamicNotes: String,
    val source: String,
    val timestamp: Long
)

@Entity(tableName = "ocr_pages")
data class OcrPageEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val bookId: Int,
    val pageNumber: Int,
    val originalImagePath: String,
    val ocrText: String,
    val ocrConfidence: Float,
    val ocrDate: Long,
    val language: String
)
