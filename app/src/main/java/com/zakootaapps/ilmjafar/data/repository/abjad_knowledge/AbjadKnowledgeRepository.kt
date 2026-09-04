package com.zakootaapps.ilmjafar.data.repository.abjad_knowledge

import com.zakootaapps.ilmjafar.data.local.abjad_knowledge.AbjadKnowledgeDao
import com.zakootaapps.ilmjafar.data.local.abjad_knowledge.AbjadSystemEntity
import com.zakootaapps.ilmjafar.data.local.abjad_knowledge.AbjadLetterEntity
import com.zakootaapps.ilmjafar.data.local.abjad_knowledge.AbjadAliasEntity
import com.zakootaapps.ilmjafar.data.local.abjad_knowledge.AbjadMetadataEntity
import kotlinx.coroutines.flow.Flow

class AbjadKnowledgeRepository(
    private val dao: AbjadKnowledgeDao
) {
    suspend fun insertSystem(system: AbjadSystemEntity): Long = dao.insertSystem(system)
    
    fun getAllSystems(): Flow<List<AbjadSystemEntity>> = dao.getAllSystems()
    
    suspend fun getSystemById(id: Int): AbjadSystemEntity? = dao.getSystemById(id)
    
    suspend fun getSystemByName(name: String): AbjadSystemEntity? = dao.getSystemByName(name)
    
    fun searchSystems(query: String): Flow<List<AbjadSystemEntity>> = dao.searchSystems(query)
    
    suspend fun insertLetter(letter: AbjadLetterEntity): Long = dao.insertLetter(letter)
    
    suspend fun insertLetters(letters: List<AbjadLetterEntity>) = dao.insertLetters(letters)
    
    fun getLetters(systemId: Int): Flow<List<AbjadLetterEntity>> = dao.getLettersForSystem(systemId)
    
    fun getLetterByChar(letter: String): Flow<List<AbjadLetterEntity>> = dao.getLetterByChar(letter)
    
    suspend fun insertAlias(alias: AbjadAliasEntity): Long = dao.insertAlias(alias)
    
    suspend fun insertMetadata(metadata: AbjadMetadataEntity): Long = dao.insertMetadata(metadata)
    
    suspend fun getMetadata(): AbjadMetadataEntity? = dao.getMetadata()
}
