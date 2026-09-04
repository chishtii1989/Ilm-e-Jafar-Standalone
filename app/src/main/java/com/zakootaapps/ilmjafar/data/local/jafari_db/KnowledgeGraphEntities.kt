package com.zakootaapps.ilmjafar.data.local.jafari_db

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Entity(tableName = "knowledge_graph_links")
@Serializable
data class KnowledgeGraphLinkEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val sourceId: String = "",
    val sourceType: String = "",
    val targetId: String = "",
    val targetType: String = "",
    val relationType: String = "", // e.g., "COMPATIBLE_PLANET", "RECOMMENDED_BAKHOOR", "DIRECT", "INDIRECT"
    val confidenceScore: Float = 0f,
    val needsReview: Boolean = true,
    val isSystemGenerated: Boolean = true,
    val metadata: String = "",
    val timestamp: Long = 0L
)
