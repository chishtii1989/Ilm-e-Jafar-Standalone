package com.zakootaapps.ilmjafar.util

import com.zakootaapps.ilmjafar.data.local.jafari_db.*
import com.zakootaapps.ilmjafar.data.repository.UniversalJafariKnowledgeRepository
import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

@Serializable
data class JafariKnowledgeExportData(
    val ism_ilahi_rules: List<IsmIlahiEntity> = emptyList(),
    val muwakkil_rules: List<MuwakkilEntity> = emptyList(),
    val abjad_rules: List<AbjadRuleEntity> = emptyList(),
    val noorani_letters: List<NooraniLetterEntity> = emptyList(),
    val zulmani_letters: List<ZulmaniLetterEntity> = emptyList(),
    val fire_letters: List<FireLetterEntity> = emptyList(),
    val water_letters: List<WaterLetterEntity> = emptyList(),
    val air_letters: List<AirLetterEntity> = emptyList(),
    val earth_letters: List<EarthLetterEntity> = emptyList(),
    val planet_rules: List<PlanetRuleEntity> = emptyList(),
    val day_rules: List<DayRuleEntity> = emptyList(),
    val hour_rules: List<HourRuleEntity> = emptyList(),
    val shape_rules: List<ShapeRuleEntity> = emptyList(),
    val writing_direction_rules: List<WritingDirectionRuleEntity> = emptyList(),
    val ink_rules: List<InkRuleEntity> = emptyList(),
    val paper_rules: List<PaperRuleEntity> = emptyList(),
    val purpose_rules: List<PurposeRuleEntity> = emptyList(),
    val classical_talism_templates_v2: List<ClassicalTalismTemplateEntity2> = emptyList(),
    val classical_naqoosh_templates_v2: List<ClassicalNaqooshTemplateEntity2> = emptyList(),
    val element_compatibility: List<ElementCompatibilityEntity> = emptyList(),
    val planet_compatibility: List<PlanetCompatibilityEntity> = emptyList(),
    val letter_harmony_rules: List<LetterHarmonyRuleEntity> = emptyList(),
    val letter_conflict_rules: List<LetterConflictRuleEntity> = emptyList(),
    val numerical_harmony_rules: List<NumericalHarmonyRuleEntity> = emptyList(),
    val numerical_conflict_rules: List<NumericalConflictRuleEntity> = emptyList()
)

object JafariJsonEngine {
    private val json = Json { ignoreUnknownKeys = true; prettyPrint = true }

    fun exportData(data: JafariKnowledgeExportData): String {
        return json.encodeToString(data)
    }

    fun importData(jsonString: String): JafariKnowledgeExportData? {
        return try {
            json.decodeFromString<JafariKnowledgeExportData>(jsonString)
        } catch (e: Exception) {
            null
        }
    }
}
