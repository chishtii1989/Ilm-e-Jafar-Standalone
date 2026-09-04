package com.zakootaapps.ilmjafar.data.local.jafari_db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import androidx.room.Delete
import kotlinx.coroutines.flow.Flow

@Dao
interface JafariKnowledgeDatabaseDao {
    @Query("SELECT * FROM ism_ilahi_rules")
    fun getAllIsmIlahiEntity(): Flow<List<IsmIlahiEntity>>
    
    @Query("SELECT * FROM ism_ilahi_rules WHERE urduName LIKE '%' || :query || '%' OR arabicName LIKE '%' || :query || '%' OR englishName LIKE '%' || :query || '%'")
    fun searchIsmIlahiEntity(query: String): Flow<List<IsmIlahiEntity>>
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertIsmIlahiEntity(items: List<IsmIlahiEntity>)

    @Query("SELECT * FROM muwakkil_rules")
    fun getAllMuwakkilEntity(): Flow<List<MuwakkilEntity>>
    
    @Query("SELECT * FROM muwakkil_rules WHERE urduName LIKE '%' || :query || '%' OR arabicName LIKE '%' || :query || '%' OR englishName LIKE '%' || :query || '%'")
    fun searchMuwakkilEntity(query: String): Flow<List<MuwakkilEntity>>
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMuwakkilEntity(items: List<MuwakkilEntity>)

    @Query("SELECT * FROM abjad_rules")
    fun getAllAbjadRuleEntity(): Flow<List<AbjadRuleEntity>>
    
    @Query("SELECT * FROM abjad_rules WHERE urduName LIKE '%' || :query || '%' OR arabicName LIKE '%' || :query || '%' OR englishName LIKE '%' || :query || '%'")
    fun searchAbjadRuleEntity(query: String): Flow<List<AbjadRuleEntity>>
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAbjadRuleEntity(items: List<AbjadRuleEntity>)

    @Query("SELECT * FROM noorani_letters")
    fun getAllNooraniLetterEntity(): Flow<List<NooraniLetterEntity>>
    
    @Query("SELECT * FROM noorani_letters WHERE urduName LIKE '%' || :query || '%' OR arabicName LIKE '%' || :query || '%' OR englishName LIKE '%' || :query || '%'")
    fun searchNooraniLetterEntity(query: String): Flow<List<NooraniLetterEntity>>
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNooraniLetterEntity(items: List<NooraniLetterEntity>)

    @Query("SELECT * FROM zulmani_letters")
    fun getAllZulmaniLetterEntity(): Flow<List<ZulmaniLetterEntity>>
    
    @Query("SELECT * FROM zulmani_letters WHERE urduName LIKE '%' || :query || '%' OR arabicName LIKE '%' || :query || '%' OR englishName LIKE '%' || :query || '%'")
    fun searchZulmaniLetterEntity(query: String): Flow<List<ZulmaniLetterEntity>>
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertZulmaniLetterEntity(items: List<ZulmaniLetterEntity>)

    @Query("SELECT * FROM fire_letters")
    fun getAllFireLetterEntity(): Flow<List<FireLetterEntity>>
    
    @Query("SELECT * FROM fire_letters WHERE urduName LIKE '%' || :query || '%' OR arabicName LIKE '%' || :query || '%' OR englishName LIKE '%' || :query || '%'")
    fun searchFireLetterEntity(query: String): Flow<List<FireLetterEntity>>
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFireLetterEntity(items: List<FireLetterEntity>)

    @Query("SELECT * FROM water_letters")
    fun getAllWaterLetterEntity(): Flow<List<WaterLetterEntity>>
    
    @Query("SELECT * FROM water_letters WHERE urduName LIKE '%' || :query || '%' OR arabicName LIKE '%' || :query || '%' OR englishName LIKE '%' || :query || '%'")
    fun searchWaterLetterEntity(query: String): Flow<List<WaterLetterEntity>>
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWaterLetterEntity(items: List<WaterLetterEntity>)

    @Query("SELECT * FROM air_letters")
    fun getAllAirLetterEntity(): Flow<List<AirLetterEntity>>
    
    @Query("SELECT * FROM air_letters WHERE urduName LIKE '%' || :query || '%' OR arabicName LIKE '%' || :query || '%' OR englishName LIKE '%' || :query || '%'")
    fun searchAirLetterEntity(query: String): Flow<List<AirLetterEntity>>
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAirLetterEntity(items: List<AirLetterEntity>)

    @Query("SELECT * FROM earth_letters")
    fun getAllEarthLetterEntity(): Flow<List<EarthLetterEntity>>
    
    @Query("SELECT * FROM earth_letters WHERE urduName LIKE '%' || :query || '%' OR arabicName LIKE '%' || :query || '%' OR englishName LIKE '%' || :query || '%'")
    fun searchEarthLetterEntity(query: String): Flow<List<EarthLetterEntity>>
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertEarthLetterEntity(items: List<EarthLetterEntity>)

    @Query("SELECT * FROM planet_rules")
    fun getAllPlanetRuleEntity(): Flow<List<PlanetRuleEntity>>
    
    @Query("SELECT * FROM planet_rules WHERE urduName LIKE '%' || :query || '%' OR arabicName LIKE '%' || :query || '%' OR englishName LIKE '%' || :query || '%'")
    fun searchPlanetRuleEntity(query: String): Flow<List<PlanetRuleEntity>>
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPlanetRuleEntity(items: List<PlanetRuleEntity>)

    @Query("SELECT * FROM day_rules")
    fun getAllDayRuleEntity(): Flow<List<DayRuleEntity>>
    
    @Query("SELECT * FROM day_rules WHERE urduName LIKE '%' || :query || '%' OR arabicName LIKE '%' || :query || '%' OR englishName LIKE '%' || :query || '%'")
    fun searchDayRuleEntity(query: String): Flow<List<DayRuleEntity>>
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDayRuleEntity(items: List<DayRuleEntity>)

    @Query("SELECT * FROM hour_rules")
    fun getAllHourRuleEntity(): Flow<List<HourRuleEntity>>
    
    @Query("SELECT * FROM hour_rules WHERE urduName LIKE '%' || :query || '%' OR arabicName LIKE '%' || :query || '%' OR englishName LIKE '%' || :query || '%'")
    fun searchHourRuleEntity(query: String): Flow<List<HourRuleEntity>>
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertHourRuleEntity(items: List<HourRuleEntity>)

    @Query("SELECT * FROM shape_rules")
    fun getAllShapeRuleEntity(): Flow<List<ShapeRuleEntity>>
    
    @Query("SELECT * FROM shape_rules WHERE urduName LIKE '%' || :query || '%' OR arabicName LIKE '%' || :query || '%' OR englishName LIKE '%' || :query || '%'")
    fun searchShapeRuleEntity(query: String): Flow<List<ShapeRuleEntity>>
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertShapeRuleEntity(items: List<ShapeRuleEntity>)

    @Query("SELECT * FROM writing_direction_rules")
    fun getAllWritingDirectionRuleEntity(): Flow<List<WritingDirectionRuleEntity>>
    
    @Query("SELECT * FROM writing_direction_rules WHERE urduName LIKE '%' || :query || '%' OR arabicName LIKE '%' || :query || '%' OR englishName LIKE '%' || :query || '%'")
    fun searchWritingDirectionRuleEntity(query: String): Flow<List<WritingDirectionRuleEntity>>
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWritingDirectionRuleEntity(items: List<WritingDirectionRuleEntity>)

    @Query("SELECT * FROM ink_rules")
    fun getAllInkRuleEntity(): Flow<List<InkRuleEntity>>
    
    @Query("SELECT * FROM ink_rules WHERE urduName LIKE '%' || :query || '%' OR arabicName LIKE '%' || :query || '%' OR englishName LIKE '%' || :query || '%'")
    fun searchInkRuleEntity(query: String): Flow<List<InkRuleEntity>>
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertInkRuleEntity(items: List<InkRuleEntity>)

    @Query("SELECT * FROM paper_rules")
    fun getAllPaperRuleEntity(): Flow<List<PaperRuleEntity>>
    
    @Query("SELECT * FROM paper_rules WHERE urduName LIKE '%' || :query || '%' OR arabicName LIKE '%' || :query || '%' OR englishName LIKE '%' || :query || '%'")
    fun searchPaperRuleEntity(query: String): Flow<List<PaperRuleEntity>>
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPaperRuleEntity(items: List<PaperRuleEntity>)

    @Query("SELECT * FROM purpose_rules")
    fun getAllPurposeRuleEntity(): Flow<List<PurposeRuleEntity>>
    
    @Query("SELECT * FROM purpose_rules WHERE urduName LIKE '%' || :query || '%' OR arabicName LIKE '%' || :query || '%' OR englishName LIKE '%' || :query || '%'")
    fun searchPurposeRuleEntity(query: String): Flow<List<PurposeRuleEntity>>
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPurposeRuleEntity(items: List<PurposeRuleEntity>)

    @Query("SELECT * FROM classical_talism_templates_v2")
    fun getAllClassicalTalismTemplateEntity2(): Flow<List<ClassicalTalismTemplateEntity2>>
    
    @Query("SELECT * FROM classical_talism_templates_v2 WHERE urduName LIKE '%' || :query || '%' OR arabicName LIKE '%' || :query || '%' OR englishName LIKE '%' || :query || '%'")
    fun searchClassicalTalismTemplateEntity2(query: String): Flow<List<ClassicalTalismTemplateEntity2>>
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertClassicalTalismTemplateEntity2(items: List<ClassicalTalismTemplateEntity2>)

    @Query("SELECT * FROM classical_naqoosh_templates_v2")
    fun getAllClassicalNaqooshTemplateEntity2(): Flow<List<ClassicalNaqooshTemplateEntity2>>
    
    @Query("SELECT * FROM classical_naqoosh_templates_v2 WHERE urduName LIKE '%' || :query || '%' OR arabicName LIKE '%' || :query || '%' OR englishName LIKE '%' || :query || '%'")
    fun searchClassicalNaqooshTemplateEntity2(query: String): Flow<List<ClassicalNaqooshTemplateEntity2>>
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertClassicalNaqooshTemplateEntity2(items: List<ClassicalNaqooshTemplateEntity2>)

    @Query("SELECT * FROM element_compatibility")
    fun getAllElementCompatibilityEntity(): Flow<List<ElementCompatibilityEntity>>
    
    @Query("SELECT * FROM element_compatibility WHERE urduName LIKE '%' || :query || '%' OR arabicName LIKE '%' || :query || '%' OR englishName LIKE '%' || :query || '%'")
    fun searchElementCompatibilityEntity(query: String): Flow<List<ElementCompatibilityEntity>>
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertElementCompatibilityEntity(items: List<ElementCompatibilityEntity>)

    @Query("SELECT * FROM planet_compatibility")
    fun getAllPlanetCompatibilityEntity(): Flow<List<PlanetCompatibilityEntity>>
    
    @Query("SELECT * FROM planet_compatibility WHERE urduName LIKE '%' || :query || '%' OR arabicName LIKE '%' || :query || '%' OR englishName LIKE '%' || :query || '%'")
    fun searchPlanetCompatibilityEntity(query: String): Flow<List<PlanetCompatibilityEntity>>
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPlanetCompatibilityEntity(items: List<PlanetCompatibilityEntity>)

    @Query("SELECT * FROM letter_harmony_rules")
    fun getAllLetterHarmonyRuleEntity(): Flow<List<LetterHarmonyRuleEntity>>
    
    @Query("SELECT * FROM letter_harmony_rules WHERE urduName LIKE '%' || :query || '%' OR arabicName LIKE '%' || :query || '%' OR englishName LIKE '%' || :query || '%'")
    fun searchLetterHarmonyRuleEntity(query: String): Flow<List<LetterHarmonyRuleEntity>>
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLetterHarmonyRuleEntity(items: List<LetterHarmonyRuleEntity>)

    @Query("SELECT * FROM letter_conflict_rules")
    fun getAllLetterConflictRuleEntity(): Flow<List<LetterConflictRuleEntity>>
    
    @Query("SELECT * FROM letter_conflict_rules WHERE urduName LIKE '%' || :query || '%' OR arabicName LIKE '%' || :query || '%' OR englishName LIKE '%' || :query || '%'")
    fun searchLetterConflictRuleEntity(query: String): Flow<List<LetterConflictRuleEntity>>
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLetterConflictRuleEntity(items: List<LetterConflictRuleEntity>)

    @Query("SELECT * FROM numerical_harmony_rules")
    fun getAllNumericalHarmonyRuleEntity(): Flow<List<NumericalHarmonyRuleEntity>>
    
    @Query("SELECT * FROM numerical_harmony_rules WHERE urduName LIKE '%' || :query || '%' OR arabicName LIKE '%' || :query || '%' OR englishName LIKE '%' || :query || '%'")
    fun searchNumericalHarmonyRuleEntity(query: String): Flow<List<NumericalHarmonyRuleEntity>>
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNumericalHarmonyRuleEntity(items: List<NumericalHarmonyRuleEntity>)

    @Query("SELECT * FROM numerical_conflict_rules")
    fun getAllNumericalConflictRuleEntity(): Flow<List<NumericalConflictRuleEntity>>
    
    @Query("SELECT * FROM numerical_conflict_rules WHERE urduName LIKE '%' || :query || '%' OR arabicName LIKE '%' || :query || '%' OR englishName LIKE '%' || :query || '%'")
    fun searchNumericalConflictRuleEntity(query: String): Flow<List<NumericalConflictRuleEntity>>
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNumericalConflictRuleEntity(items: List<NumericalConflictRuleEntity>)

}

@Dao
interface ArabicLetterMasterDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLetter(letter: ArabicLetterMasterEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLetters(letters: List<ArabicLetterMasterEntity>)

    @Update
    suspend fun updateLetter(letter: ArabicLetterMasterEntity)

    @Delete
    suspend fun deleteLetter(letter: ArabicLetterMasterEntity)

    @Query("SELECT * FROM arabic_letters_master WHERE id = :id")
    suspend fun getLetterById(id: Int): ArabicLetterMasterEntity?

    @Query("SELECT * FROM arabic_letters_master WHERE arabicLetter = :letter LIMIT 1")
    suspend fun getLetterByArabicChar(letter: String): ArabicLetterMasterEntity?

    @Query("SELECT * FROM arabic_letters_master ORDER BY letterOrder ASC")
    fun getAllLettersFlow(): kotlinx.coroutines.flow.Flow<List<ArabicLetterMasterEntity>>

    @Query("SELECT * FROM arabic_letters_master ORDER BY letterOrder ASC")
    suspend fun getAllLetters(): List<ArabicLetterMasterEntity>
}

@Dao
interface MuwakkilMasterDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMuwakkil(item: MuwakkilMasterEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMuwakkils(items: List<MuwakkilMasterEntity>)

    @Update
    suspend fun updateMuwakkil(item: MuwakkilMasterEntity)

    @Delete
    suspend fun deleteMuwakkil(item: MuwakkilMasterEntity)

    @Query("SELECT * FROM muwakkil_master WHERE id = :id")
    suspend fun getMuwakkilById(id: Int): MuwakkilMasterEntity?

    @Query("SELECT * FROM muwakkil_master WHERE arabicName = :name LIMIT 1")
    suspend fun getMuwakkilByArabicName(name: String): MuwakkilMasterEntity?

    @Query("SELECT * FROM muwakkil_master")
    fun getAllMuwakkilsFlow(): kotlinx.coroutines.flow.Flow<List<MuwakkilMasterEntity>>

    @Query("SELECT * FROM muwakkil_master")
    suspend fun getAllMuwakkils(): List<MuwakkilMasterEntity>
}

@Dao
interface PlanetMasterDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPlanet(item: PlanetMasterEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPlanets(items: List<PlanetMasterEntity>)

    @Update
    suspend fun updatePlanet(item: PlanetMasterEntity)

    @Delete
    suspend fun deletePlanet(item: PlanetMasterEntity)

    @Query("SELECT * FROM planet_master WHERE id = :id")
    suspend fun getPlanetById(id: Int): PlanetMasterEntity?

    @Query("SELECT * FROM planet_master WHERE arabicName = :name LIMIT 1")
    suspend fun getPlanetByArabicName(name: String): PlanetMasterEntity?

    @Query("SELECT * FROM planet_master ORDER BY planetNumber ASC")
    fun getAllPlanetsFlow(): kotlinx.coroutines.flow.Flow<List<PlanetMasterEntity>>

    @Query("SELECT * FROM planet_master ORDER BY planetNumber ASC")
    suspend fun getAllPlanets(): List<PlanetMasterEntity>
}

@Dao
interface ElementMasterDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertElement(item: ElementMasterEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertElements(items: List<ElementMasterEntity>)

    @Update
    suspend fun updateElement(item: ElementMasterEntity)

    @Delete
    suspend fun deleteElement(item: ElementMasterEntity)

    @Query("SELECT * FROM element_master WHERE id = :id")
    suspend fun getElementById(id: Int): ElementMasterEntity?

    @Query("SELECT * FROM element_master WHERE arabicName = :name LIMIT 1")
    suspend fun getElementByArabicName(name: String): ElementMasterEntity?

    @Query("SELECT * FROM element_master")
    fun getAllElementsFlow(): kotlinx.coroutines.flow.Flow<List<ElementMasterEntity>>

    @Query("SELECT * FROM element_master")
    suspend fun getAllElements(): List<ElementMasterEntity>
}

@Dao
interface MoonMansionMasterDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMoonMansion(item: MoonMansionMasterEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMoonMansions(items: List<MoonMansionMasterEntity>)

    @Update
    suspend fun updateMoonMansion(item: MoonMansionMasterEntity)

    @Delete
    suspend fun deleteMoonMansion(item: MoonMansionMasterEntity)

    @Query("SELECT * FROM moon_mansion_master WHERE id = :id")
    suspend fun getMoonMansionById(id: Int): MoonMansionMasterEntity?

    @Query("SELECT * FROM moon_mansion_master WHERE mansionNumber = :number LIMIT 1")
    suspend fun getMoonMansionByNumber(number: Int): MoonMansionMasterEntity?

    @Query("SELECT * FROM moon_mansion_master ORDER BY mansionNumber ASC")
    fun getAllMoonMansionsFlow(): kotlinx.coroutines.flow.Flow<List<MoonMansionMasterEntity>>

    @Query("SELECT * FROM moon_mansion_master ORDER BY mansionNumber ASC")
    suspend fun getAllMoonMansions(): List<MoonMansionMasterEntity>
}

@Dao
interface PlanetaryHourMasterDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPlanetaryHour(item: PlanetaryHourMasterEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPlanetaryHours(items: List<PlanetaryHourMasterEntity>)

    @Update
    suspend fun updatePlanetaryHour(item: PlanetaryHourMasterEntity)

    @Delete
    suspend fun deletePlanetaryHour(item: PlanetaryHourMasterEntity)

    @Query("SELECT * FROM planetary_hour_master WHERE id = :id")
    suspend fun getPlanetaryHourById(id: Int): PlanetaryHourMasterEntity?

    @Query("SELECT * FROM planetary_hour_master WHERE arabicName = :name LIMIT 1")
    suspend fun getPlanetaryHourByArabicName(name: String): PlanetaryHourMasterEntity?

    @Query("SELECT * FROM planetary_hour_master")
    fun getAllPlanetaryHoursFlow(): kotlinx.coroutines.flow.Flow<List<PlanetaryHourMasterEntity>>

    @Query("SELECT * FROM planetary_hour_master")
    suspend fun getAllPlanetaryHours(): List<PlanetaryHourMasterEntity>
}

@Dao
interface AbjadMasterDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAbjadRecord(item: AbjadMasterEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAbjadRecords(items: List<AbjadMasterEntity>)

    @Update
    suspend fun updateAbjadRecord(item: AbjadMasterEntity)

    @Delete
    suspend fun deleteAbjadRecord(item: AbjadMasterEntity)

    @Query("SELECT * FROM abjad_master WHERE id = :id")
    suspend fun getAbjadRecordById(id: Int): AbjadMasterEntity?

    @Query("SELECT * FROM abjad_master WHERE arabicLetter = :letter LIMIT 1")
    suspend fun getAbjadRecordByArabicLetter(letter: String): AbjadMasterEntity?

    @Query("SELECT * FROM abjad_master ORDER BY letterPosition ASC")
    fun getAllAbjadRecordsFlow(): kotlinx.coroutines.flow.Flow<List<AbjadMasterEntity>>

    @Query("SELECT * FROM abjad_master ORDER BY letterPosition ASC")
    suspend fun getAllAbjadRecords(): List<AbjadMasterEntity>
}

@Dao
interface JafrRuleMasterDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRule(item: JafrRuleMasterEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRules(items: List<JafrRuleMasterEntity>)

    @Update
    suspend fun updateRule(item: JafrRuleMasterEntity)

    @Delete
    suspend fun deleteRule(item: JafrRuleMasterEntity)

    @Query("SELECT * FROM jafr_rule_master WHERE id = :id")
    suspend fun getRuleById(id: Int): JafrRuleMasterEntity?

    @Query("SELECT * FROM jafr_rule_master WHERE ruleId = :ruleId LIMIT 1")
    suspend fun getRuleByRuleId(ruleId: String): JafrRuleMasterEntity?

    @Query("SELECT * FROM jafr_rule_master ORDER BY id ASC")
    fun getAllRulesFlow(): kotlinx.coroutines.flow.Flow<List<JafrRuleMasterEntity>>

    @Query("SELECT * FROM jafr_rule_master ORDER BY id ASC")
    suspend fun getAllRules(): List<JafrRuleMasterEntity>
}

@Dao
interface GemstoneMasterDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGemstone(item: GemstoneMasterEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGemstones(items: List<GemstoneMasterEntity>)

    @Update
    suspend fun updateGemstone(item: GemstoneMasterEntity)

    @Delete
    suspend fun deleteGemstone(item: GemstoneMasterEntity)

    @Query("SELECT * FROM gemstone_master WHERE id = :id")
    suspend fun getGemstoneById(id: Int): GemstoneMasterEntity?

    @Query("SELECT * FROM gemstone_master")
    fun getAllGemstonesFlow(): kotlinx.coroutines.flow.Flow<List<GemstoneMasterEntity>>

    @Query("SELECT * FROM gemstone_master")
    suspend fun getAllGemstones(): List<GemstoneMasterEntity>
}

@Dao
interface MetalMasterDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMetal(item: MetalMasterEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMetals(items: List<MetalMasterEntity>)

    @Update
    suspend fun updateMetal(item: MetalMasterEntity)

    @Delete
    suspend fun deleteMetal(item: MetalMasterEntity)

    @Query("SELECT * FROM metal_master WHERE id = :id")
    suspend fun getMetalById(id: Int): MetalMasterEntity?

    @Query("SELECT * FROM metal_master")
    fun getAllMetalsFlow(): kotlinx.coroutines.flow.Flow<List<MetalMasterEntity>>

    @Query("SELECT * FROM metal_master")
    suspend fun getAllMetals(): List<MetalMasterEntity>
}

@Dao
interface BakhoorMasterDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBakhoor(item: BakhoorMasterEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBakhoors(items: List<BakhoorMasterEntity>)

    @Update
    suspend fun updateBakhoor(item: BakhoorMasterEntity)

    @Delete
    suspend fun deleteBakhoor(item: BakhoorMasterEntity)

    @Query("SELECT * FROM bakhoor_master WHERE id = :id")
    suspend fun getBakhoorById(id: Int): BakhoorMasterEntity?

    @Query("SELECT * FROM bakhoor_master")
    fun getAllBakhoorsFlow(): kotlinx.coroutines.flow.Flow<List<BakhoorMasterEntity>>

    @Query("SELECT * FROM bakhoor_master")
    suspend fun getAllBakhoors(): List<BakhoorMasterEntity>
}

@Dao
interface PerfumeMasterDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPerfume(item: PerfumeMasterEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPerfumes(items: List<PerfumeMasterEntity>)

    @Update
    suspend fun updatePerfume(item: PerfumeMasterEntity)

    @Delete
    suspend fun deletePerfume(item: PerfumeMasterEntity)

    @Query("SELECT * FROM perfume_master WHERE id = :id")
    suspend fun getPerfumeById(id: Int): PerfumeMasterEntity?

    @Query("SELECT * FROM perfume_master")
    fun getAllPerfumesFlow(): kotlinx.coroutines.flow.Flow<List<PerfumeMasterEntity>>

    @Query("SELECT * FROM perfume_master")
    suspend fun getAllPerfumes(): List<PerfumeMasterEntity>
}
