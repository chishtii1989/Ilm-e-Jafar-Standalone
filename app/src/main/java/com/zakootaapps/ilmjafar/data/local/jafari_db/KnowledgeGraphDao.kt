package com.zakootaapps.ilmjafar.data.local.jafari_db

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface KnowledgeGraphDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLink(link: KnowledgeGraphLinkEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLinks(links: List<KnowledgeGraphLinkEntity>)

    @Query("SELECT * FROM knowledge_graph_links WHERE sourceId = :sourceId AND sourceType = :sourceType")
    suspend fun getLinksForSource(sourceId: String, sourceType: String): List<KnowledgeGraphLinkEntity>
    
    @Query("SELECT * FROM knowledge_graph_links WHERE targetId = :targetId AND targetType = :targetType")
    suspend fun getLinksForTarget(targetId: String, targetType: String): List<KnowledgeGraphLinkEntity>

    @Query("SELECT * FROM knowledge_graph_links WHERE (sourceId = :entityId AND sourceType = :entityType) OR (targetId = :entityId AND targetType = :entityType)")
    suspend fun getAllLinksForEntity(entityId: String, entityType: String): List<KnowledgeGraphLinkEntity>

    @Query("SELECT * FROM knowledge_graph_links")
    suspend fun getAllLinks(): List<KnowledgeGraphLinkEntity>

    @Delete
    suspend fun deleteLink(link: KnowledgeGraphLinkEntity)
}
